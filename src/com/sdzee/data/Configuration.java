package com.sdzee.data;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.User;
import com.sdzee.daoInterfaces.UserDAOInterface;

public class Configuration {

	private static final String ROLE_FIELD = "updateRole";
	private static final String TITLE_FIELD = "title";
	private static final String IMAGE_FIELD = "imageUrl";
	private static final String SUMMARY_FIELD = "summary";
	private static final String CONTENT_FIELD = "content";
	
    private UserDAOInterface      userDao;

    public Configuration( UserDAOInterface userDao ) {
        this.userDao = userDao;
    }
    
    public void update(HttpServletRequest request) {
    	String role = getFieldValue(request, ROLE_FIELD);
    	
    	if (role != null) {
    		String[] roleAndName = role.split("/");
    		role = roleAndName[0];
    		String name = roleAndName[1];
    		User user = userDao.findByName(name);
    		updateRole(user, role);
    	}
    }
    
    public String whichForm(HttpServletRequest request) {
    	String formCalled = "";
    	String role = getFieldValue(request, ROLE_FIELD);
    	String title = getFieldValue(request, TITLE_FIELD);
    	String image = getFieldValue(request, IMAGE_FIELD);
    	String summary = getFieldValue(request, SUMMARY_FIELD);
    	String content = getFieldValue(request, CONTENT_FIELD);
    	
    	if (role != null && title == null && image == null && summary == null && content == null) {
    		formCalled = "updateRole";
    	} else if (role == null && title != null && image != null && summary != null && content != null) {
    		formCalled = "addNews";
    	}
    	
    	return formCalled;
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
