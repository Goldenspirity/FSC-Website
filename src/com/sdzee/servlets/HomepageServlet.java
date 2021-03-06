package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.News;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.NewsDAOInterface;
import com.sdzee.data.NewsManagement;

@SuppressWarnings("serial")
public class HomepageServlet extends HttpServlet {
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String VIEW = "/WEB-INF/jsp/index.jsp";
	private final static String NEWS_LIST_FIELD = "newsList";
	
	NewsDAOInterface newsDaoInterface;
	
    public void init() throws ServletException {
        this.newsDaoInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getNewsDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NewsManagement nm = new NewsManagement(newsDaoInterface);
		News[] newsList = nm.getThreeLastNews();
		
		request.setAttribute(NEWS_LIST_FIELD, newsList);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
	
}
