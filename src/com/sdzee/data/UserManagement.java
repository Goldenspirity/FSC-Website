package com.sdzee.data;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOException;
import com.sdzee.daoInterfaces.UserDAOInterface;

public class UserManagement {
	
	private static final String PASSWORD_FIELD = "password";
	private static final String OLD_PASSWORD_FIELD = "oldPassword";
	private static final String NEW_PASSWORD_FIELD = "newPassword";
	private static final String NEW_PASSWORD_CHECK_FIELD = "newPasswordCheck";
	private static final String EMAIL_FIELD = "email";
	private static final String USER_FIELD = "user";
	private static final String ENCRYPTION_ALGORITHM = "SHA-256";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

    private UserDAOInterface      userDao;

    public UserManagement( UserDAOInterface userDao ) {
        this.userDao = userDao;
    }
    
    public ArrayList<User> getAllUsers() {
    	ArrayList<User> usersList = userDao.getAllUsers();
    	return usersList;
    }
	
	public User update(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
	    String email = getFieldValue(request, EMAIL_FIELD );
	    String password = getFieldValue(request, PASSWORD_FIELD );
	    String oldPassword = getFieldValue(request, OLD_PASSWORD_FIELD);
	    String newPassword = getFieldValue(request, NEW_PASSWORD_FIELD);
	    String newPasswordCheck = getFieldValue(request, NEW_PASSWORD_CHECK_FIELD);
	    
		User user = userDao.findByName(((User) session.getAttribute(USER_FIELD)).getName());
		
		if (oldPassword != null && newPassword != null && newPasswordCheck!= null && !oldPassword.isEmpty() && !newPassword.isEmpty() && !newPasswordCheck.isEmpty()) {
			if (newPassword.equals(newPasswordCheck)) {
				try {
					passwordProcess(oldPassword, user, 1);
					newPasswordCheck(newPassword);
					if (errors.isEmpty()) {
						byte [] passwordEncryption = SecurePassword.getSecurePassword(newPassword, ENCRYPTION_ALGORITHM);
						user.setPassword(passwordEncryption);
						updatePassword(user);
						result = "Mot de passe changé avec succès.";
					} else {
						result = "Echec du changement de mot de passe";
					}
				} catch (DAOException e) {
					new FormProcessException("Une erreur imprévue est survenue.");
				}
			} else {
				setErrors("newPasswordCheck", "Les deux mots de passe ne correspondent pas.");
			}
		} else if (email != null && password != null && !email.isEmpty() && !password.isEmpty()) {
			passwordProcess(password, user,0);
			emailCheck(email, user);
			if (errors.isEmpty()) {
				user.setEmail(email);
				updateEmail(user);
				result = "Email changé avec succès.";
			} else {
				result = "Echec du changement d'email.";
			}
		} else {
			result = "Le formulaire n'est pas rempli correctement.";
		}
		
		return user;
	}
	
	private void updatePassword(User user) {
		userDao.updatePassword(user);
	}
	
	private void updateEmail(User user) {
		userDao.updateEmail(user);
	}
	
    private void passwordProcess(String password, User user, int oldOrNot) {
	    if (user != null) {
	    	byte[] passwordHash = user.getPassword();	    	
	    	byte[] passwordEncryption = null;
			passwordEncryption = SecurePassword.getSecurePassword(password, ENCRYPTION_ALGORITHM);
	    	if (!MessageDigest.isEqual(passwordEncryption, passwordHash)) {
	    		if (oldOrNot == 1) {
	    			setErrors(OLD_PASSWORD_FIELD, "Mauvais mot de passe.");
	    		} else if (oldOrNot == 0) {
	    			setErrors(PASSWORD_FIELD, "Mauvais mot de passe.");
	    		}
	    	}	
	    }
    } 
    
    private void newPasswordCheck(String password) {
    	if (password.length() < 8) {
    		setErrors(NEW_PASSWORD_FIELD, "Le mot de passe doit contenir au moins 8 caractères.");
    	}
    }
    
	private void emailCheck( String email, User user ) {
        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            setErrors(EMAIL_FIELD, "Merci de saisir une adresse mail valide." );
        } else if ( userDao.findByEmail( email ) != null ) {
        	if (!email.equals(user.getEmail())) {
        		setErrors(EMAIL_FIELD, "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
        	} else {
        		setErrors(EMAIL_FIELD, "Merci de changer d'adresse mail." );
        	}
        } 
	}
	
	private void setErrors( String field, String message ) {
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
