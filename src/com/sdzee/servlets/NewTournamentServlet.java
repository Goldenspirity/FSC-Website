package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Tournament;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.TournamentDAOInterface;
import com.sdzee.data.TournamentManagement;

@SuppressWarnings("serial")
public class NewTournamentServlet extends HttpServlet {

	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String VIEW = "/WEB-INF/jsp/events/internal/newTournament.jsp";
	private final static String RELATIVE_VIEW = "/events/tournoisInternes/bracket?id=";
	
	private TournamentDAOInterface     tournamentDAOInterface;
	
    public void init() throws ServletException {
        this.tournamentDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTournamentDao();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TournamentManagement tm = new TournamentManagement(tournamentDAOInterface);
		Tournament tournament = tm.createTournament(request);
		
		response.sendRedirect( request.getContextPath() + RELATIVE_VIEW + tournament.getId());

	}
	
}
