package com.sdzee.data;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.Tournament;
import com.sdzee.daoInterfaces.TournamentDAOInterface;

public class TournamentManagement {
	
	private final static String ID_FIELD = "id";
	private final static String SCORE_0_FIELD = "score0";
	private final static String SCORE_1_FIELD = "score1";
	private final static String MATCH_NUMBER = "matchNumber";
	private final static String TITLE_FIELD = "title";
	private final static String DATE_FIELD = "date";
	private final static String TEAM_FIELD = "team";
	private final static String FREEWIN = "freewin";
	private int BRACKET_SIZE;
	
	private TournamentDAOInterface tournamentDao;
	
	public TournamentManagement (TournamentDAOInterface tdi) {
		this.tournamentDao = tdi;
	}
	
	public Tournament createTournament (HttpServletRequest request) {
		String title = request.getParameter(TITLE_FIELD);
		String dateTournament = request.getParameter(DATE_FIELD);
		BRACKET_SIZE = getBracketSize(request);
		String[] teams = new String[BRACKET_SIZE];
		for (int i = 0; i < BRACKET_SIZE; i++) {
			teams[i] = request.getParameter(TEAM_FIELD + i);
		}
		
		if (!title.isEmpty() && !dateTournament.isEmpty()) {
			Tournament tournament = new Tournament();
	
			tournament.setNumberOfTeams(BRACKET_SIZE);
			tournament.setTitle(title);
			tournament.setDateTournament(dateTournament);
			tournament.setTeams(teams);
	
			tournamentDao.createTournament(tournament);
			int id = tournamentDao.lastId();
			
			tournament.setId(id);
			
			/* Check for any missing team */
			
			tournament = getTournament(id);
			boolean missingTeams = false;
			
			for (int i = 0; i < teams.length; i++) {
				if (teams[i] == null || teams[i].trim().isEmpty()) {
					/* Put "freewin" in empty spots */
					teams[i] = FREEWIN;
					tournament.setSpot(i+8, teams[i]);
					missingTeams = true;
				}
			}
			if (missingTeams) {
				updateFreewins(tournament);
				tournamentDao.updateTournament(tournament);
				tournament = tournamentDao.getTournament(id);		
			}
			
			return tournament;
		}
		
		return null;
	}
	
	public Tournament updateTournament (HttpServletRequest request) { // Update tournament after a result has been posted (DAO call)
		String idStr = request.getParameter(ID_FIELD);
		String score0Str = request.getParameter(SCORE_0_FIELD);
		String score1Str = request.getParameter(SCORE_1_FIELD);
		int matchNumber = Integer.parseInt(request.getParameter(MATCH_NUMBER));
		
		if (!idStr.isEmpty() && tournamentDao.exists(Integer.parseInt(idStr)) && !score0Str.isEmpty() && !score1Str.isEmpty()) {
			int id = Integer.parseInt(idStr);
			Tournament tournament = tournamentDao.getTournament(id);
			String team0 = getTeam0(tournament, matchNumber);
			String team1 = getTeam1(tournament, matchNumber);
			String winner = "";
			String loser = "";
			BRACKET_SIZE = tournament.getNumberOfTeams();
			int winnerSpot = getWinnerSpots(BRACKET_SIZE)[matchNumber];
			int loserSpot = getLoserSpots(BRACKET_SIZE)[matchNumber];
			
			try {
				int score0 = Integer.parseInt(score0Str);
				int score1 = Integer.parseInt(score1Str);
				
				if (score0 > score1) {
					winner = team0;
					loser = team1;
				} else if (score0 < score1) {
					winner = team1;
					loser = team0;
				} else {
					return tournament;
				}
			
				tournament.setSpot(winnerSpot, winner);
				tournament.setSpot(loserSpot, loser);
				
				tournament.setScore(matchNumber,0,score0);
				tournament.setScore(matchNumber,1,score1);

				updateFreewins(tournament);
				tournamentDao.updateTournament(tournament);
			
				return tournament;
			} catch (NumberFormatException e) {
				return tournament;
			}
		} else {
			return null;
		}
	}	
	
	private void updateFreewins (Tournament tournament) {
		String team0 = "";
		String team1 = "";
		String winner = "";
		String loser = "";
		
		int winnerSpot = 0;
		int loserSpot = 0;

		BRACKET_SIZE = tournament.getNumberOfTeams();
		
		for (int i = 0; i < tournament.getScores().size(); i++) {
			team0 = getTeam0(tournament, i);
			team1 = getTeam1(tournament, i);
			winnerSpot = getWinnerSpots(BRACKET_SIZE)[i];
			loserSpot = getLoserSpots(BRACKET_SIZE)[i];

			if (team0 != null && team1 != null && team0.equals(FREEWIN) && !team1.isEmpty()) {
				winner = team1;
				loser = team0;
				
				tournament.setSpot(winnerSpot, winner);
				tournament.setSpot(loserSpot, loser);
			
			} else if (team0 != null && team1 != null && team1.equals(FREEWIN) && !team0.isEmpty()) {
				winner = team0;
				loser = team1;
				
				tournament.setSpot(winnerSpot, winner);
				tournament.setSpot(loserSpot, loser);
			}
		}
	}
	
	public void deleteTournament(int id) {
		if (tournamentDao.exists(id)) {
			tournamentDao.deleteTournament(id);
		}
	}
	
	public Tournament getTournament(int id) {
		if (tournamentDao.exists(id)) {
			return tournamentDao.getTournament(id);
		} else {
			return null;
		}
	}
	
	// Return true if a tournament is on progress, false otherwise
	public boolean tournamentInProgress( ) { 
		return false;
	}

	public ArrayList<Tournament> getAllTournaments() {
		ArrayList<Tournament> tournamentsList = tournamentDao.getAllTournaments();
		return tournamentsList;
	}
	
	private String getTeam0 (Tournament tournament, int matchNumber) {
		String team0 = "";
		if (matchNumber < 10) {
			team0 = tournament.getSpots().get(matchNumber*2 + 2);
		} else if (matchNumber == 10) {
			team0 = tournament.getSpots().get(matchNumber*2 + 4);
		} else if  (matchNumber == 11) {
			team0 = tournament.getSpots().get(matchNumber*2 + 6);
		}
		return team0;
	}
	
	private String getTeam1 (Tournament tournament, int matchNumber) {
		String team1 = "";
		if (matchNumber < 10) {
			team1 = tournament.getSpots().get(matchNumber*2 + 3);
		} else if (matchNumber == 10) {
			team1 = tournament.getSpots().get(matchNumber*2 + 5);
		} else if  (matchNumber == 11) {
			team1 = tournament.getSpots().get(matchNumber*2 + 7);
		}
		return team1;
	}
	
	private int[] getWinnerSpots(int bracket_size) {
		if (bracket_size == 8) {
			int[] winnerSpotsEight = {0,2,3,16,17,18,19,20,21,22,26,30};
			return winnerSpotsEight;
		} else {
			return null;
		}
	}
	
	private int[] getLoserSpots(int bracket_size) {
		if (bracket_size == 8) {
			int[] loserSpotsEight = {1,28,29,4,5,6,7,24,25,23,27,31};
			return loserSpotsEight;
		} else {
			return null;
		}
	}
	
	private int getBracketSize(HttpServletRequest request) {
		int bracketSize = 0;
		int i = 0;
		while (request.getParameter(TEAM_FIELD + i) != null && !request.getParameter(TEAM_FIELD + i).trim().isEmpty()) {
			i++;
		}
		if (i < 9) {
			bracketSize = 8;
		} else if (i > 8 && i < 17) {
			bracketSize = 16;
		} else if (i > 16 && i < 33) {
			bracketSize = 32;
		} else if (i > 32 && i < 65) {
			bracketSize = 64;
		}
		return bracketSize;
	}
}
