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
public class EditNewsServlet extends HttpServlet {
	
    private final static String CONF_DAO_FACTORY = "daofactory";
	private final static String VIEW = "/WEB-INF/jsp/configPanel/editNews.jsp";
	private final static String NEWS_VIEW = "/news?id=";
	private final static String NEWS_FIELD = "news";
	private final static String ERROR_VIEW = "/error";
	
	private NewsDAOInterface newsDAOInterface;
	
    public void init() throws ServletException {
        this.newsDAOInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getNewsDao();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsManagement nm = new NewsManagement(newsDAOInterface);
		News news = nm.getNews(request);
		
		if (news != null) {
			request.setAttribute(NEWS_FIELD, news);
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + ERROR_VIEW);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsManagement nm = new NewsManagement(newsDAOInterface);
		String id = nm.updateNews(request);
		response.sendRedirect( request.getContextPath() + NEWS_VIEW + id);
		
	}

}
