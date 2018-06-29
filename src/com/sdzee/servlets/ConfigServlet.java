package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.NewsDAOInterface;
import com.sdzee.daoInterfaces.UserDAOInterface;
import com.sdzee.data.Configuration;
import com.sdzee.data.NewsManagement;
import com.sdzee.data.UserManagement;

@SuppressWarnings("serial")
public class ConfigServlet extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String VIEW = "/WEB-INF/jsp/configPanel/configPanel.jsp";
	private final static String RELATIVE_VIEW = "/config";
	private static final String USERS_LIST = "usersList";
	
	private UserDAOInterface userDAOInterface;
	private NewsDAOInterface newsDAOInterface;
	
    public void init() throws ServletException {
        this.userDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
        this.newsDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getNewsDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManagement um = new UserManagement(userDAOInterface);
		ArrayList<User> usersList = um.getAllUsers();
		
		request.setAttribute(USERS_LIST, usersList);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Configuration co = new Configuration(userDAOInterface);
		String formCalled = co.whichForm(request);
		if (formCalled.equals("updateRole")) {
			co.update(request);
		} else if (formCalled.equals("addNews")) {
			NewsManagement nm = new NewsManagement(newsDAOInterface);
			nm.addNews(request);
		}
		
		response.sendRedirect( request.getContextPath() + RELATIVE_VIEW );
		
	}
}
