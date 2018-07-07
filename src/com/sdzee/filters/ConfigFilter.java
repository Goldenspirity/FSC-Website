package com.sdzee.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.User;

public class ConfigFilter implements Filter {

	private final static String ATT_USER = "user";
	private final static String ADMIN_ROLE = "admin";
	private final static String SUPER_ADMIN_ROLE = "superadmin";
	private final static String HOME = "/home";
	
    public void init( FilterConfig config ) throws ServletException {
    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        
    	boolean adminUser = false;
    	boolean superAdminUser = false;
        
        if ((User) session.getAttribute(ATT_USER) != null) {
        	adminUser = ((User) session.getAttribute(ATT_USER)).getRole().equals(ADMIN_ROLE);
        	superAdminUser = ((User) session.getAttribute(ATT_USER)).getRole().equals(SUPER_ADMIN_ROLE);
        }
        
        if (session.getAttribute(ATT_USER) == null || (!adminUser && !superAdminUser)) {
            response.sendRedirect( request.getContextPath() + HOME );
        } else {
            chain.doFilter( request, response );
        }

	}
	
    public void destroy() {
    }

}
