package com.sdzee.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdzee.dao.DAOFactory;

public class InitialisationDaoFactory implements ServletContextListener {
    private static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory          daoFactory;
    
    /* Obtenir une et une seule instance de DAOFactory, de portée toute l'appli */
    @Override
    public void contextInitialized( ServletContextEvent event ) {
		//String context = (String) request.getServletContext().getAttribute("context");
		/*String context = getApplicationContext();
		if (context == null) {
			context = request.getContextPath();
			request.getServletContext().setAttribute("context", context);
		}*/
    	
        ServletContext servletContext = event.getServletContext();
        this.daoFactory = DAOFactory.getInstance();
        servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
    	
    }
}