package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.News;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoInterfaces.NewsDAOInterface;
import com.sdzee.data.NewsManagement;

@SuppressWarnings("serial")
public class NewsServlet extends HttpServlet {
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	private final static String VIEW = "/WEB-INF/jsp/news/fullNews.jsp";
	private final static String NEWS_LIST_VIEW = "/WEB-INF/jsp/news/allNews.jsp";
	private final static String ERROR_VIEW = "/error";
	private final static String ID_FIELD = "id";
	private final static String NEWS_FIELD = "news";
	private final static String NEWS_LIST_FIELD = "newsList";
	
	NewsDAOInterface newsDaoInterface;
	
    public void init() throws ServletException {
        this.newsDaoInterface = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getNewsDao();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NewsManagement nm = new NewsManagement(newsDaoInterface);
		String isIdInDb = nm.isIdInDb(request);
		if (isIdInDb != null && isIdInDb.equals("in")) {
			News news = nm.getNews(Integer.parseInt(request.getParameter(ID_FIELD)));
			
			request.setAttribute(NEWS_FIELD, news);

			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else if (isIdInDb != null && isIdInDb.equals("out")) {
			response.sendRedirect( request.getContextPath() + ERROR_VIEW );
		} else {
			ArrayList<News> newsList = nm.getAllNews();
			request.setAttribute(NEWS_LIST_FIELD, newsList);
			this.getServletContext().getRequestDispatcher(NEWS_LIST_VIEW).forward(request, response);
		}


		
	}

}
