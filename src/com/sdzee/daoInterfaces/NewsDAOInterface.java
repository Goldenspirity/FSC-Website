package com.sdzee.daoInterfaces;

import java.util.ArrayList;

import com.sdzee.beans.News;
import com.sdzee.dao.DAOException;

public interface NewsDAOInterface {

	void createNews (News news) throws DAOException;
	
	void updateNews (News news) throws DAOException;
	
	News getNews(int id) throws DAOException;
	
	News[] getThreeLastNews() throws DAOException;
	
	ArrayList<News> getAllNews() throws DAOException;
	
	void deleteNews(int id) throws DAOException;
	
	boolean exists(int id) throws DAOException;
	
	int lastId() throws DAOException;
}
