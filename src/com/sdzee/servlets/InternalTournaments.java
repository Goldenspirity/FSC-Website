package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Tournament;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.TournamentDAOInterface;
import com.sdzee.data.TournamentManagement;

@SuppressWarnings("serial")
public class InternalTournaments extends HttpServlet {
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String TOURNAMENTS_LIST = "tournamentsList";
	private final static String VIEW = "/WEB-INF/jsp/events/internal/internalTournaments.jsp";
	
	TournamentDAOInterface tournamentDaoInterface;

    public void init() throws ServletException {
        this.tournamentDaoInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTournamentDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TournamentManagement tm = new TournamentManagement(tournamentDaoInterface);
		ArrayList<Tournament> tournamentsList = tm.getAllTournaments();
		
		request.setAttribute(TOURNAMENTS_LIST, tournamentsList);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
	
}
