package com.sdzee.data;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOException;
import com.sdzee.daoInterfaces.UserDAOInterface;

public class RegisterUser {
	
    private static final String EMAIL_FIELD  = "email";
    private static final String PASSWORD_FIELD   = "password";
    private static final String PASSWORD_CHECK_FIELD   = "passwordCheck";
    private static final String NAME_FIELD    = "username";
    // private static final String ENCRYPTION_FIELD    = "encryption";
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();
	
    private UserDAOInterface      userDao;

    public RegisterUser( UserDAOInterface userDao ) {
        this.userDao = userDao;
    }
    
    public User registerUser( HttpServletRequest request ) {
	    String email = getFieldValue( request, EMAIL_FIELD );
	    String password = getFieldValue( request, PASSWORD_FIELD );
	    String passwordCheck = getFieldValue( request, PASSWORD_CHECK_FIELD );
	    String name = getFieldValue( request, NAME_FIELD );

	    User user = new User();

	    try {
	        emailProcess( email, user );
	        passwordProcess( password, passwordCheck, user );
	        nameProcess( name, user );

	        if ( errors.isEmpty() ) {
	        	userDao.create( user );
	            result = "Succès de l'inscription.";

	        } else {
	            result = "Échec de l'inscription.";
	        }
	    } catch ( DAOException e ) {
	        result = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	    }

	    return user;
	}
	
	public String getResult() {
	    return result;
	}

	public Map<String, String> getErrors() {
	    return errors;
	}
	
	private void nameProcess( String name, User user ) {
	    try {
	    	nameCheck( name );
	    } catch ( FormProcessException e ) {
	        setError( NAME_FIELD, e.getMessage() );
	    }
	    user.setName( name );
	}
	
	private void emailProcess( String email, User user ) {
	    try {
	        emailCheck( email );
	    } catch ( FormProcessException e ) {
	        setError( EMAIL_FIELD, e.getMessage() );
	    }
	    user.setEmail( email );
	}

	private void passwordProcess( String password, String passwordCheck, User user ) {
	    try {
	        passwordCheck( password, passwordCheck );
		    // byte[] salt = null;
		    byte[] passwordEncryption = null;
		    /*if (password != null) {
				try {
					salt = SecurePassword.getSalt();
					passwordEncryption = SecurePassword.getSecurePassword(password, ENCRYPTION_ALGORITHM, salt);
				} catch (NoSuchAlgorithmException e) {
					setError(ENCRYPTION_FIELD,"Une erreur imprévue est survenue, veuillez réessayer dans quelques minutes. Si l'erreur persiste, contactez un administrateur.");
				}
			}*/
		    passwordEncryption = SecurePassword.getSecurePassword(password, ENCRYPTION_ALGORITHM);
		    user.setPassword( passwordEncryption );
	    } catch ( FormProcessException e ) {
	        setError( PASSWORD_FIELD, e.getMessage() );
	        setError( PASSWORD_CHECK_FIELD, null );
	    }
	}
	
	private void emailCheck( String email ) throws FormProcessException {
	    if ( email != null ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new FormProcessException( "Merci de saisir une adresse mail valide." );
	        } else if ( userDao.findByEmail( email ) != null ) {
	            throw new FormProcessException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
	        }
	    } else {
	        throw new FormProcessException( "Merci de saisir une adresse mail." );
	    }
	}
	
	private void passwordCheck( String password, String passwordCheck ) throws FormProcessException{
	    if (password != null && password.trim().length() != 0 && passwordCheck != null && passwordCheck.trim().length() != 0) {
	        if (!password.equals(passwordCheck)) {
	            throw new FormProcessException("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	        } else if (passwordCheck.trim().length() < 8) {
	            throw new FormProcessException("Les mots de passe doivent contenir au moins 8 caractères.");
	        }
	    } else {
	        throw new FormProcessException("Merci de saisir et confirmer votre mot de passe.");
	    }
	}
	
	private void nameCheck( String name ) throws FormProcessException {
	    if ( name != null ) {
	    	if (name.trim().length() < 5) {
	    		throw new FormProcessException( "Le nom d'utilisateur doit contenir au moins 5 caractères." );
	    	} else if (userDao.findByName(name) != null) {
	    		throw new FormProcessException("Ce nom est déjà pris.");
	    	}
	    } else {
	    	throw new FormProcessException("Il faut renseigner un nom.");
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
}
