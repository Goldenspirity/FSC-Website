package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.NewsDAOInterface;
import com.sdzee.data.NewsManagement;

@SuppressWarnings("serial")
public class RemoveNewsServlet extends HttpServlet {
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String NEWS_LIST_VIEW = "/news";
	private final static String ERROR_VIEW = "/error";
	private final static String ID_FIELD = "newsId";
	
	private NewsDAOInterface newsDAOInterface;
	
    public void init() throws ServletException {
        this.newsDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getNewsDao();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NewsManagement nm = new NewsManagement(newsDAOInterface);
		try {
			int id = Integer.parseInt(request.getParameter(ID_FIELD));
			nm.deleteNews(id);
			response.sendRedirect( request.getContextPath() + NEWS_LIST_VIEW);
		} catch (NullPointerException e) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		} catch (NumberFormatException f) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW);
		}
		
	}

}
