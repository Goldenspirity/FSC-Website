package com.sdzee.data;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.News;
import com.sdzee.daoInterfaces.NewsDAOInterface;

public class NewsManagement {
	
	private final static String ID_FIELD = "id";
	
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
	
	public String isIdInDb(HttpServletRequest request) {
		String idString = getFieldValue(request, ID_FIELD);
		if (idString != null) {
			try {
				int id = Integer.parseInt(idString);
				if (newsDao.exists(id)) {
					return "in";
				} else {
					return "out";
				}
			} catch (NumberFormatException e) {
				return "out";
			} catch (NullPointerException e) {
				return "out";
			}
		} else {
			return null;
		}
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
