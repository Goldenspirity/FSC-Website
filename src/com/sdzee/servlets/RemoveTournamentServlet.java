package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.TournamentDAOInterface;
import com.sdzee.data.TournamentManagement;

@SuppressWarnings("serial")
public class RemoveTournamentServlet extends HttpServlet {

	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String INTERNAL_TOURNAMENTS_VIEX = "/events/tournoisInternes";
	private final static String ERROR_VIEW = "/error";
	private final static String ID_FIELD = "id";
	
	private TournamentDAOInterface     tournamentDAOInterface;
	
    public void init() throws ServletException {
        this.tournamentDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTournamentDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TournamentManagement tm = new TournamentManagement(tournamentDAOInterface);
		try {
			int id = Integer.parseInt(request.getParameter(ID_FIELD));
			tm.deleteTournament(id);
			response.sendRedirect( request.getContextPath() + INTERNAL_TOURNAMENTS_VIEX);
		} catch (NullPointerException e) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		} catch (NumberFormatException f) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		}
		
	}
	
}
