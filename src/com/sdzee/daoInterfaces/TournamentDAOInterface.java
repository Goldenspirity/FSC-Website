package com.sdzee.daoInterfaces;

import java.util.ArrayList;

import com.sdzee.beans.Tournament;
import com.sdzee.dao.DAOException;

public interface TournamentDAOInterface {
	
	void createTournament(Tournament tournament) throws DAOException;
	
	boolean inProgress(int id) throws DAOException;
	
	void updateTournament(Tournament tournament) throws DAOException;
	
	Tournament getTournament(int id) throws DAOException;
	
	void deleteTournament(int id) throws DAOException;
	
	ArrayList<Tournament> getAllTournaments() throws DAOException;
	
	boolean exists(int id) throws DAOException;

	int lastId() throws DAOException;

}
