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

public class EventsFilter implements Filter {
	
	private static final String ATT_USER = "user";
	private final static String HOME = "/home";
	
    public void init( FilterConfig config ) throws ServletException {
    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        
        if (session.getAttribute(ATT_USER) == null) {
            response.sendRedirect( request.getContextPath() + HOME );
        } else {
            chain.doFilter( request, response );
        }

	}
	
    public void destroy() {
    }

}
