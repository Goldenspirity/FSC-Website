package com.sdzee.data;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.User;
import com.sdzee.daoInterfaces.UserDAOInterface;

public class Configuration {

	private static final String ROLE_FIELD = "updateRole";
	
    private UserDAOInterface      userDao;

    public Configuration( UserDAOInterface userDao ) {
        this.userDao = userDao;
    }
    
    public void update(HttpServletRequest request) {
    	String role = getFieldValue(request, ROLE_FIELD);
    	
    	if (role != null) {
    		String[] roleAndName = role.split("_");
    		role = roleAndName[0];
    		String name = roleAndName[1];
    		User user = userDao.findByName(name);
    		updateRole(user, role);
    	}
    }
    
    private void updateRole(User user, String role) {
    	user.setRole(role);
    	userDao.updateRole(user);
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
