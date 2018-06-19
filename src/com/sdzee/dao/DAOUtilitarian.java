package com.sdzee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtilitarian {
	
	/* Initialisation of prepared statement */
    public static PreparedStatement initialisationPreparedRequest( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objects ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objects.length; i++ ) {
            preparedStatement.setObject( i + 1, objects[i] );
        }
        return preparedStatement;
    }
	
	/* Silent close of RelsutSet */
    public static void silentClose( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    /* Silent close of statement */
    public static void silentClose( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    /* Silent close of connexion */
    public static void silentClose( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    /* Silent close of both statement and connexion */
    public static void silentClose( Statement statement, Connection connexion ) {
    	silentClose( statement );
        silentClose( connexion );
    }
    
    /* Silent close of both statement and resultSet */
    public static void silentClose( ResultSet resultSet, Statement statement ) {
    	silentClose( statement );
        silentClose( resultSet );
    }

    /* Silent close of resultset, statement and connexion */
    public static void silentClose( ResultSet resultSet, Statement statement, Connection connexion ) {
    	silentClose( resultSet );
    	silentClose( statement );
    	silentClose( connexion );
    }

}
