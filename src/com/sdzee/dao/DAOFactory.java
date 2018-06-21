package com.sdzee.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.sdzee.daoImplementations.NewsDAOImplementation;
import com.sdzee.daoImplementations.TournamentDAOImplementation;
import com.sdzee.daoImplementations.UserDAOImplementation;

public class DAOFactory {
	
    private static final String PROPERTIES_FILE       = "/com/sdzee/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD    = "password";

    private String              url;
    private String              username;
    private String              password;
    
    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( PROPERTIES_FILE );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + PROPERTIES_FILE + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            username = properties.getProperty( PROPERTY_USERNAME );
            password = properties.getProperty( PROPERTY_PASSWORD );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + PROPERTIES_FILE, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        DAOFactory instance = new DAOFactory( url, username, password );
        return instance;
    }

    public Connection getConnexion() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }
    
    public UserDAOImplementation getUserDao() {
        return new UserDAOImplementation( this );
    }
    
    public TournamentDAOImplementation getTournamentDao() {
        return new TournamentDAOImplementation( this );
    }
    
    public NewsDAOImplementation getNewsDao() {
    	return new NewsDAOImplementation (this);
    }

}
