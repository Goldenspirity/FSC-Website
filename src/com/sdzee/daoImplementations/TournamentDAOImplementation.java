package com.sdzee.daoImplementations;

import static com.sdzee.dao.DAOUtilitarian.initialisationPreparedRequest;
import static com.sdzee.dao.DAOUtilitarian.silentClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sdzee.beans.Tournament;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.TournamentDAOInterface;

public class TournamentDAOImplementation implements TournamentDAOInterface {
	
	private DAOFactory daoFactory;
	
	// private static final String DATE_FORMAT = "%d %m %y";
	private static final String SQL_GET_TOURNAMENT_SPOTS = "SELECT id, title, DATE_FORMAT(dateTournament,'%d/%m/%Y') AS dateTournament, spot, team FROM teamsinternaltournaments WHERE id = ?";
	private static final String SQL_GET_TOURNAMENT_SCORES = "SELECT id, matchNumber, score, topOrBottom FROM scoreinternaltournaments WHERE id = ?";
	private static final String SQL_CREATE_TEAMS_TABLE = "INSERT INTO teamsinternaltournaments (id, title, dateTournament, spot, team) VALUES (?,?,?,?,?)";
	private static final String SQL_CREATE_SCORES_TABLE = "INSERT INTO scoreinternaltournaments (id, dateTournament, matchNumber, score, topOrBottom) VALUES (?,?, ?, -1, ?)";
	private static final String SQL_LAST_ID = "SELECT id FROM teamsinternaltournaments";
	private static final String SQL_DELETE_TEAMS = "DELETE FROM teamsinternaltournaments WHERE id = ?";
	private static final String SQL_DELETE_SCORES = "DELETE FROM scoreinternaltournaments WHERE id = ?";
	private static final String SQL_IN_PROGRESS = "SELECT spot FROM teamsinternaltournaments WHERE team IS NULL AND id = ?";
	private static final String SQL_UPDATE_TEAMS = "UPDATE teamsinternaltournaments SET team = ? WHERE id = ? AND spot = ?";
	private static final String SQL_UPDATE_SCORES = "UPDATE scoreinternaltournaments SET score = ? WHERE id = ? AND matchNumber = ? AND topOrBottom = ?";
	
	private static final int SMALL_BRACKET_SIZE = 8;
	private static final String TEAM_FIELD = "team";
	private static final String SPOT_FIELD = "spot";
	private static final String MATCH_NUMBER_FIELD = "matchNumber";
	private static final String SCORE_FIELD = "score";
	private static final String TOP_OR_BOTTOM_FIELD = "topOrBottom";
	private static final String DATE_FIELD = "dateTournament";
	private static final String ID_FIELD = "id";
	private static final String TITLE_FIELD = "title";
	
	
	private int tournamentSize = 0;
	
    public TournamentDAOImplementation( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public void createTournament(Tournament tournament) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnexion();
            
            String[] teams = tournament.getTeams();
            int numberOfTeams = teams.length;
            int numberOfSpots = 0;
            int numberOfMatch = 0;
            
            int teamsStatus = 0;
            int scoresStatus = 0;
            
            int id = lastId() + 1;
            String title = tournament.getTitle();
            String date = tournament.getDateTournament();
            
            switch(numberOfTeams) {
            	case 8:
                	numberOfSpots = 32;
                	numberOfMatch = 12;
                	break;
            	case 16:
            		break;
            }
            
            for (int i = 0; i < numberOfSpots; i++) {
            	if (i > 7 && i < 16) {
            		preparedStatement = initialisationPreparedRequest( connexion, SQL_CREATE_TEAMS_TABLE, false, id, title, date, i, teams[i-8] );
                    teamsStatus = teamsStatus + preparedStatement.executeUpdate();
                    silentClose(preparedStatement);
            	} else {
            		preparedStatement = initialisationPreparedRequest( connexion, SQL_CREATE_TEAMS_TABLE, false, id, title, date, i, null );
                    teamsStatus = teamsStatus + preparedStatement.executeUpdate();
                    silentClose(preparedStatement);
            	}
                
            }
            
            for (int i = 0; i < numberOfMatch; i++) {
            	// First team
                preparedStatement = initialisationPreparedRequest( connexion, SQL_CREATE_SCORES_TABLE, false, id, date, i , 0);
                scoresStatus = scoresStatus + preparedStatement.executeUpdate();
                silentClose(preparedStatement);
                
                // Second team
                preparedStatement = initialisationPreparedRequest( connexion, SQL_CREATE_SCORES_TABLE, false, id, date, i, 1);
                scoresStatus = scoresStatus + preparedStatement.executeUpdate();
                silentClose(preparedStatement);
            }
            
            if ( teamsStatus != numberOfSpots ) {
                throw new DAOException( "Problème lors de la création de la table Teams." );
            }
            
            if (scoresStatus != 2*numberOfMatch) {
            	throw new DAOException( "Problème lors de la création de la table Scores." );
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose(connexion);
        }
		
	}

	@Override
	public void deleteTournament(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        Tournament tournament = getTournament(id);
        
        int numberOfTeams = tournament.getTeams().length;
        int numberOfSpots = 0;
        int numberOfMatch = 0;
        
        int teamsStatus = 0;
        int scoresStatus = 0;
        
        switch(numberOfTeams) {
    	case 8:
        	numberOfSpots = 32;
        	numberOfMatch = 12;
        	break;
    	case 16:
    		break;
        }
		
		try {
			connexion = daoFactory.getConnexion();
			
			preparedStatement = initialisationPreparedRequest( connexion, SQL_DELETE_TEAMS, false, id);
            teamsStatus = teamsStatus + preparedStatement.executeUpdate();
            silentClose(preparedStatement);
            
			preparedStatement = initialisationPreparedRequest( connexion, SQL_DELETE_SCORES, false, id);
			scoresStatus = scoresStatus + preparedStatement.executeUpdate();
            silentClose(preparedStatement);
            
            if ( teamsStatus != numberOfSpots ) {
                throw new DAOException( "Problème lors de la suppression de la table Teams." );
            }
            
            if (scoresStatus != 2*numberOfMatch) {
            	throw new DAOException( "Problème lors de la suppression de la table Scores." );
            }
		} catch (SQLException e) {
			throw new DAOException("Problème lors de la suppression du tournoi", e);
		} finally {
			silentClose(connexion);
		}
	}
	
	@Override
	public boolean inProgress(int id) throws DAOException {
		boolean inProgress = true;
		
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int numberOfNullSpots = 0;
		
        try {
            int maxId = lastId();
            if (id < 0 || id > maxId) {
            	throw new DAOException("L'id indiqué n'existe pas dans la bdd.");
            }
        	
        	connexion = daoFactory.getConnexion();
        	preparedStatement = initialisationPreparedRequest( connexion, SQL_IN_PROGRESS, false, id);
        	resultSet = preparedStatement.executeQuery();
        	while (resultSet.next()) {
        		numberOfNullSpots ++;
        	}
        } catch (SQLException e) {
        	throw new DAOException (e);
        } finally {
        	silentClose(resultSet, preparedStatement, connexion);
        }
        
        if (numberOfNullSpots == 0) {
        	inProgress = false;
        } else {
        	inProgress = true;
        }
		
		return inProgress;
	}

	@Override
	public void updateTournament(Tournament tournament) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
		int id = tournament.getId();
		Map<Integer, String> spots = tournament.getSpots();
		Map<Integer, Integer[]> scores = tournament.getScores();
		
		try {
			connexion = daoFactory.getConnexion();
			for (int i = 0; i < spots.size(); i++) {
				String team = spots.get(i);
				if (team != null) {
					preparedStatement = initialisationPreparedRequest(connexion, SQL_UPDATE_TEAMS, false, team, id, i);
					preparedStatement.executeUpdate();
					silentClose(preparedStatement);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("La table Teams n'a pas pu être mise à jour.", e);
		} finally {
			silentClose(connexion);
		}
		
		try {
			connexion = daoFactory.getConnexion();
			for (int i = 0; i < scores.size(); i++) {
				Integer[] matchScore = scores.get(i);
				if (matchScore != null && matchScore[0] >= 0 & matchScore[1] >= 0) {
					for (int j = 0; j < matchScore.length; j++) {
						preparedStatement = initialisationPreparedRequest(connexion, SQL_UPDATE_SCORES, false, matchScore[j], id, i, j);
						preparedStatement.executeUpdate();
						silentClose(preparedStatement);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("La table Score n'a pas pu être mise à jour.", e);
		} finally {
			silentClose(connexion);
		}
		
		
	}

	@Override
	public Tournament getTournament(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        Tournament tournament = new Tournament();
        
        tournamentSize = 0;
        
        // Try catch to get number of spots from db
        try {
        	
            int maxId = lastId();
            if (id < 0 || id > maxId) {
            	throw new DAOException("L'id indiqué n'existe pas dans la bdd.");
            }
            
            connexion = daoFactory.getConnexion();
            preparedStatement = initialisationPreparedRequest( connexion, SQL_GET_TOURNAMENT_SPOTS, false, id);
            resultSet = preparedStatement.executeQuery();
            
            
            while ( resultSet.next() ) {
            	tournamentSize++;
            }
            
        } catch ( SQLException e ) {
            throw new DAOException("Une erreur imprévue est survenue. Le nombre d'équipes n'a pas pu être récupéré.", e );
        } finally {
            silentClose( resultSet, preparedStatement, connexion );
        }      
        
        // Try catch to get teams and spots from db
        try {
            connexion = daoFactory.getConnexion();
            preparedStatement = initialisationPreparedRequest( connexion, SQL_GET_TOURNAMENT_SPOTS, false, id);
            resultSet = preparedStatement.executeQuery();
            
            boolean constantsMapped = false;
            
            while ( resultSet.next() ) {
            	if (!constantsMapped) {
            		tournament = mapConstants(resultSet);
            		constantsMapped = true;
            	}
            	tournament = mapTeams( resultSet, tournament );
                tournament = mapSpots( resultSet, tournament );
            }
            
        } catch ( SQLException e ) {
            throw new DAOException("Une erreur imprévue est survenue. Les équipes n'ont pas pu être récupérées.", e );
        } finally {
            silentClose( resultSet, preparedStatement, connexion );
        }
        
        // Try catch to get scores from db
        try {
            connexion = daoFactory.getConnexion();
            preparedStatement = initialisationPreparedRequest( connexion, SQL_GET_TOURNAMENT_SCORES, false, id);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
            	tournament = mapScores(resultSet, tournament);
            }
            
        } catch (SQLException e) {
        	throw new DAOException("Une erreur imprévue est survenue. Les scores n'ont pas pu être récupérés.",e);
        } finally {
        	silentClose( resultSet, preparedStatement, connexion);
        }
        
		return tournament;
	}
		
	private Tournament mapScores(ResultSet resultSet, Tournament tournament) throws SQLException {
		int matchNumber = resultSet.getInt(MATCH_NUMBER_FIELD);
		int topOrBottom = resultSet.getInt(TOP_OR_BOTTOM_FIELD);
		int score = resultSet.getInt(SCORE_FIELD);
		
		tournament.setScore(matchNumber, topOrBottom, score);
		
		return tournament;
	}
	
	private Tournament mapSpots(ResultSet resultSet, Tournament tournament) throws SQLException {
		int spot = resultSet.getInt(SPOT_FIELD);
		String team = resultSet.getString(TEAM_FIELD);
		tournament.setSpot(spot, team);

		return tournament;
	}
	
	private Tournament mapTeams(ResultSet resultSet, Tournament tournament) {
		
		String team = null;
		
		try {
			team = resultSet.getString(TEAM_FIELD);
		} catch (SQLException e) {
			throw new DAOException ("Une erreur imprévue est survenue, le tournoi n'a pas pu être récupéré.",e);
		}
		
		if (team != null) {
			int lastIndex = lastIndex(tournament.getTeams());
			if(!contains(tournament.getTeams(),team) && lastIndex < 7) {
				tournament.getTeams()[lastIndex + 1] = team;
			}
		}
		
		return tournament;
	} 
	
	private Tournament mapConstants(ResultSet resultSet) {
		Tournament tournament = new Tournament();
		String date = null;
		int id = 0;
		String title = "";
		
		try {
			date = resultSet.getString(DATE_FIELD);
			id = resultSet.getInt(ID_FIELD);
			title = resultSet.getString(TITLE_FIELD);
		} catch (SQLException e) {
			throw new DAOException ("Une erreur imprévue est survenue, le tournoi n'a pas pu être récupéré.",e);
		}
		
		if ( tournamentSize == 32) {
			tournament.setNumberOfTeams(SMALL_BRACKET_SIZE);
			initTeams(tournament, SMALL_BRACKET_SIZE);
		}
		
		tournament.setId(id);
		tournament.setTitle(title);
		tournament.setDateTournament(date);
		tournament.setScores(new HashMap<Integer,Integer[]>());
		tournament.setSpots(new HashMap<Integer, String>());
		
		return tournament;
	}
	
	private void initTeams(Tournament tournament, int numberOfTeams) {
		String[] str = new String[numberOfTeams];
		for (int i = 0; i < numberOfTeams; i++) {
			str[i] = "";
		}
		tournament.setTeams(str);
	}
	
	// Returns true if strA contains str
	private boolean contains(String[] strA, String str) {
		boolean contains = false;
		if (strA != null) {
			for (int i = 0; i < strA.length; i++) {
				if (strA[i] != null && strA[i] == str) {
					contains = true;
				}
			}
		}
		return contains;
	}
	
	// Returns the index of the last String element in strA where the lasts elements are null
	private int lastIndex(String[] strA) {
		int lastIndex = -1;
		if (strA != null) {
			for (int i = 0; i < strA.length; i++) {
				if (!strA[i].isEmpty()) {
					lastIndex = i;
				}
			}
		} else {
			lastIndex = 0;
		}
		return lastIndex;
	}
	
	public int lastId() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
		int id = -1;
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = initialisationPreparedRequest(connexion,SQL_LAST_ID, false);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int currentId = resultSet.getInt(ID_FIELD);
				if(currentId > id) {
					id = currentId;
				}
			}
			
		} catch (SQLException e) {
			throw new DAOException("Problème lors de la récupération du last ID", e);
		} finally {
			silentClose(resultSet, preparedStatement, connexion);
		}
		if (id < 0) {
			id = 0;
		}
		return id;
	}
	
	public ArrayList<Integer> allId() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = initialisationPreparedRequest(connexion,SQL_LAST_ID, false);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int currentId = resultSet.getInt(ID_FIELD);
				if(!ids.contains(currentId)) {
					ids.add(currentId);
				}
			}
			
		} catch (SQLException e) {
			throw new DAOException("Problème lors de la récupération des ID", e);
		} finally {
			silentClose(resultSet, preparedStatement, connexion);
		}
		return ids;
	}

	
	@Override
	public ArrayList<Tournament> getAllTournaments() throws DAOException {
		
		ArrayList<Tournament> tournamentsList = new ArrayList<Tournament>();
		ArrayList<Integer> allIds = allId();
		
		for (int i : allIds) {
			Tournament t = getTournament(i);
			if (t != null) {
				tournamentsList.add(t);
			}
		}

		return tournamentsList;
	}

	@Override
	public boolean exists(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
		boolean exists = false;
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = initialisationPreparedRequest(connexion,SQL_LAST_ID, false);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int currentId = resultSet.getInt(ID_FIELD);
				if(currentId == id) {
					exists = true;
				}
			}
			
		} catch (SQLException e) {
			throw new DAOException("Le tournoi n'existe pas.", e);
		} finally {
			silentClose(resultSet, preparedStatement, connexion);
		}
		
		return exists;
	}

}
