package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.UserDAOInterface;
import com.sdzee.data.ConnectUser;

@SuppressWarnings("serial")
public class ConnexionServlet extends HttpServlet {
	
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String ATT_USER         = "user";
    private static final String ATT_FORM         = "form";
	private final static String VIEW = "/WEB-INF/jsp/connexion/connexion.jsp";
	private final static String HOME = "/home";
	
	private UserDAOInterface     userDAOInterface;
	
    public void init() throws ServletException {
        this.userDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
        ConnectUser form = new ConnectUser( userDAOInterface );
        User user = form.connectUser( request );

        request.setAttribute( ATT_FORM, form );
        if (form.getErrors().isEmpty()) {
        	session.setAttribute( ATT_USER, user );
        	response.sendRedirect( request.getContextPath() + HOME );
        } else { 
        	session.setAttribute( ATT_USER, null );
        	this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
        }

        
	}

}
