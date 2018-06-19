package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Index extends HttpServlet {
	
	private final static String HOME = "/home";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String url = request.getRequestURL().toString();
		System.out.println(url);
		System.out.println(request.getContextPath());
		String[] urlSplit = url.split(request.getContextPath());
		System.out.println(urlSplit[1]);
		
		if(urlSplit[1].equals("/")) {
			response.sendRedirect( request.getContextPath() + HOME );
		}*/
		
		response.sendRedirect( request.getContextPath() + HOME );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect( request.getContextPath() + HOME );
	}
	
}
