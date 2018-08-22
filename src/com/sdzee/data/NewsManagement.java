package com.sdzee.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.News;
import com.sdzee.daoInterfaces.NewsDAOInterface;

public class NewsManagement {
	
	private final static String ID_FIELD = "id";
	private static final String TITLE_FIELD = "title";
	private static final String IMAGE_FIELD = "imageUrl";
	private static final String SUMMARY_FIELD = "summary";
	private static final String CONTENT_FIELD = "content";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
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
	
	public News getNews(HttpServletRequest request) {
		String idString = getFieldValue(request, ID_FIELD);
		
		if (idString != null) {
			try {
				int id = Integer.parseInt(idString);
				if (newsDao.exists(id)) {
					return newsDao.getNews(id);
				} else {
					return null;
				}
			} catch (NullPointerException e) {
				return null;
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}

	}
	
	public News[] getThreeLastNews() {
		News[] newsList = newsDao.getThreeLastNews();
		if (newsList[2] == null && newsList[1] != null) {
			News[] newsListTwo = {newsList[0], newsList[1]};
			return newsListTwo;
		} else if (newsList[2] == null && newsList[1] == null) {
			News[] newsListOne = {newsList[0]};
			return newsListOne;
		}
		return newsList;
	}
	
	public ArrayList<News> getAllNews() {
		ArrayList<News> newsList = newsDao.getAllNews();
		Collections.reverse(newsList);
		return newsList;
	}
	
	public void addNews(HttpServletRequest request) {
    	String title = getFieldValue(request, TITLE_FIELD);
    	String image = getFieldValue(request, IMAGE_FIELD);
    	String summary = getFieldValue(request, SUMMARY_FIELD);
    	String content = getFieldValue(request, CONTENT_FIELD);
    	
    	News news = new News();
    	
    	if (title.trim().length() <= 60) {
    		news.setTitle(title);
    	} else {
    		setErrors(TITLE_FIELD,"Le titre est trop long !");
    	}
    	
    	/*if (image.matches( "^(https?://)" )) {
    		news.setImage(image);
    	} else {
    		setErrors(IMAGE_FIELD, "Merci de rentrer une url valide");
    	}*/
    	
    	news.setImage(image);
    	
    	if (summary.trim().length() <= 300) {
    		news.setSummary(summary);
    	} else {
    		setErrors(TITLE_FIELD,"Le titre est trop long !");
    	}
    	
    	news.setContent(content);
    	
		Date date = new Date();
		String dateString = "";
		String FORMAT = "yy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		dateString = sdf.format(date);
		news.setDate(dateString);
    	
    	newsDao.createNews(news);
	}
	
	public String updateNews(HttpServletRequest request) {
		String idString = getFieldValue(request, ID_FIELD);

		if (idString != null && isIdInDb(idString).equals("in")) {
			String updatedTitle = getFieldValue(request, TITLE_FIELD);
			String updatedImage = getFieldValue(request, IMAGE_FIELD);
			String updatedSummary = getFieldValue(request, SUMMARY_FIELD);
			String updatedContent = getFieldValue(request, CONTENT_FIELD);

			Date date = new Date();
			String lastEdit = "";
			String FORMAT = "yy/MM/dd";
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
			lastEdit = sdf.format(date);
			
			News updatedNews = newsDao.getNews(Integer.parseInt(idString));
			
			updatedNews.setTitle(updatedTitle);
			updatedNews.setImage(updatedImage);
			updatedNews.setSummary(updatedSummary);
			updatedNews.setContent(updatedContent);
			updatedNews.setLastEdit(lastEdit);
			
			newsDao.updateNews(updatedNews);
			
			return idString;
		} else {
			return null;
		}	
	}
	
	public void deleteNews(int id) {
		if (newsDao.exists(id)) {
			newsDao.deleteNews(id);
		}
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
	
	public String isIdInDb(String idString) {
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
	
	public String getResult() {
	    return result;
	}

	public Map<String, String> getErrors() {
	    return errors;
	}
	
	private void setErrors( String field, String message ) {
	    errors.put( field, message );
	}
}
