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
import com.sdzee.data.UserManagement;

@SuppressWarnings("serial")
public class AccountServlet extends HttpServlet {

    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String USER_FIELD         = "user";
    private static final String USER_MANAGEMENT_FIELD         = "form";
	private final static String VIEW = "/WEB-INF/jsp/account/account.jsp";
	private final static String ERROR_VIEW = "/error";
	private final static String HOME = "/home";
	
	private UserDAOInterface     userDAOInterface;
	
    public void init() throws ServletException {
        this.userDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(USER_FIELD) != null) {
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect( request.getContextPath() + HOME );
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserManagement um = new UserManagement(userDAOInterface);
		User user = um.update(request);
		
		if (user != null) {
			session.setAttribute(USER_FIELD, user);
			request.setAttribute(USER_MANAGEMENT_FIELD, um);
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		}
		
	}
	
}
