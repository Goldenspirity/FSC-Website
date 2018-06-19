package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.UserDAOInterface;
import com.sdzee.data.RegisterUser;

@SuppressWarnings("serial")
public class InscriptionServlet extends HttpServlet {

    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String ATT_USER         = "user";
    private static final String ATT_FORM         = "form";
	private final static String VIEW = "/WEB-INF/jsp/inscription/inscription.jsp";
	
	private UserDAOInterface     userDAOInterface;
	
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.userDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterUser form = new RegisterUser( userDAOInterface );
        User user = form.registerUser( request );

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );

        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
	}
	
}
