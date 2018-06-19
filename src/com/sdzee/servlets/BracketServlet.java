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
public class BracketServlet extends HttpServlet {
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String VIEW = "/WEB-INF/jsp/events/internal/bracketEight.jsp";
	private final static String RELATIVE_VIEW = "/events/tournoisInternes/bracket?id=";
	private final static String ERROR_VIEW = "/error";
	private final static String TOURNAMENT_FIELD = "tournament";
	private final static String TOURNAMENT_MANAGEMENT_FIELD = "tournamentManagement";
	private final static String ID_FIELD = "id";
	
	private TournamentDAOInterface     tournamentDAOInterface;
	
    public void init() throws ServletException {
        this.tournamentDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTournamentDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TournamentManagement tm = new TournamentManagement(tournamentDAOInterface);
		
		try {
			int id = Integer.parseInt(request.getParameter(ID_FIELD));
			Tournament tournament = tm.getTournament(id);
			if (tournament != null) {
				request.setAttribute(TOURNAMENT_FIELD, tournament);
				this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
			} else {
				response.sendRedirect( request.getContextPath() + ERROR_VIEW);
			}
		} catch (NullPointerException e) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		} catch (NumberFormatException f) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		}
	   

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TournamentManagement tm = new TournamentManagement(tournamentDAOInterface);
		Tournament tournament = tm.updateTournament(request);
		
		if (tournament != null) {
			request.setAttribute(TOURNAMENT_FIELD, tournament);
			request.setAttribute(TOURNAMENT_MANAGEMENT_FIELD, tm);
			response.sendRedirect( request.getContextPath() + RELATIVE_VIEW + tournament.getId());
		} else {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		}
		
	}
	
}
