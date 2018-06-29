package com.sdzee.data;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOException;
import com.sdzee.daoInterfaces.UserDAOInterface;

public class ConnectUser {

    private static final String PASSWORD_FIELD   = "password";
    private static final String NAME_FIELD    = "username";
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();
	
    private UserDAOInterface      userDao;

    public ConnectUser( UserDAOInterface userDao ) {
        this.userDao = userDao;
    }
    
    public User connectUser(HttpServletRequest request) {
    	
	    String password = getFieldValue( request, PASSWORD_FIELD );
	    String name = getFieldValue( request, NAME_FIELD );
	    
	    User user = null;
	    
	    try {
	    	user = userDao.findByName(name);
	    	nameProcess(name, user);
		    passwordProcess(password, user);
		    
		    if(errors.isEmpty()) {
		    	result = "Connexion réussie";
		    } else {
		    	result = "Echec de la connexion";
		    }
    	} catch (DAOException e) {
    		result = "Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
    	}
	    
	    return user;
    	
    }
    
    private User nameProcess (String name, User user) {
    	if (user == null) {
    		setError(NAME_FIELD,"Le nom de compte est incorrect.");
    	}
    	return user;
    }
    
    private void passwordProcess(String password, User user) {
	    if (user != null) {
	    	byte[] passwordHash = user.getPassword();	    	
	    	byte[] passwordEncryption = null;
	    	/*try {
				byte[] salt = SecurePassword.getSalt();
				passwordEncryption = SecurePassword.getSecurePassword(password, ENCRYPTION_ALGORITHM, salt);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}*/
	    	passwordEncryption = SecurePassword.getSecurePassword(password, ENCRYPTION_ALGORITHM);
	    	if (!MessageDigest.isEqual(passwordEncryption, passwordHash)) {
	    		setError(PASSWORD_FIELD, "Mauvais mot de passe.");
	    	}	
	    }
    } 
    
	private void setError( String field, String message ) {
	    errors.put( field, message );
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
	
}
