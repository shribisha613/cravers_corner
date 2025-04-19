package com.cravers_corner.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = { "/pages/*" })
public class AuthenticationFilter implements Filter{

	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization logic, if required
		System.out.println("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        String uri = req.getRequestURI();
	        HttpSession session = req.getSession(false);
	        
	        boolean isLoggedIn = session != null && session.getAttribute("userWithSession") != null;
	        boolean isLoginPage = uri.endsWith("Login.jsp") || uri.endsWith("LoginServlet");
	        boolean isRegisterPage = uri.endsWith("Register.jsp") || uri.endsWith("RegisterServlet");
	        if (isLoggedIn) {
	        	//logged in cha ani login page ma jana khojeko cha bhane home ma pathaune user lai
	        	
	        	if (isLoginPage || isRegisterPage) {// if logged in and login page or register page mai cha bhane, home ma pathaune feri login ma napathaune
	        		res.sendRedirect(req.getContextPath()+ "/pages/Home.jsp");
	        	}else {//logged in cha ani aru page jana khojecha bhane pathaune as required
	        		
	        		chain.doFilter(request, response); //gives acceess to requested page based if not login page
	        		return;
	        	}
	        }else {
	        	//if not logged in 
	        	if (isLoginPage || isRegisterPage) {//if not logged in  and is in the login page then give them access to loginpage
	        		chain.doFilter(request, response);
	        	
	        	}else {
	        		//if logged in chaina ani login page ma ni chaina bhane jaslai ni login page ma pathaune 
	        		res.sendRedirect(req.getContextPath() + "/pages/Login.jsp");
	        	}
	        }
		
	}
	
	
	@Override
	public void destroy() {
		// Cleanup logic, if required
		System.out.println("AuthenticationFilter destroyed");
	}
	
	}

