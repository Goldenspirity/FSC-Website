package com.sdzee.data;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.News;
import com.sdzee.daoInterfaces.NewsDAOInterface;

public class NewsManagement {
	
    private NewsDAOInterface newsDao;

    public NewsManagement( NewsDAOInterface newsDao ) {
        this.newsDao = newsDao;
    }
    
	public News getNews(int id) {
		if (newsDao.exists(id)) {
			return newsDao.getNews(id);
		} else {
			return null;
		}
	}
	
	public News[] getThreeLastNews() {
		News[] newsList = newsDao.getThreeLastNews();
		return newsList;
	}
    
	private static String getFieldValue( HttpServletRequest request, String field ) {
	    String value = request.getParameter( field );
	    if ( value == null || value.trim().length() == 0 ) {
	        return null;
	    } else {
	        return value.trim();
	    }
	}
}
