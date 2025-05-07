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
	        boolean isAdminDashboardPage = uri.endsWith("AdminDashboard.jsp");
	        
	        if (isLoggedIn) { // If the user is logged in
	            String role = (String) session.getAttribute("role");
	            System.out.println(role);

	            // If the user is already logged in and trying to access login or register page
	            if (isLoginPage || isRegisterPage) {
	                if (role != null) {
	                    // Redirect to Admin Dashboard for admin users
	                    if ("admin".equals(role)) {
	                        res.sendRedirect(req.getContextPath() + "/pages/AdminDashboard.jsp");
	                        return;
	                    }
	                    // Redirect to Home page for customer users
	                    else if ("customer".equals(role)) {
	                        res.sendRedirect(req.getContextPath() + "/pages/Home.jsp");
	                        return;
	                    }
	                }
	            } else if (isAdminDashboardPage) {
	                // If the user is an admin but trying to access the AdminDashboard.jsp directly, redirect to the servlet
	                res.sendRedirect(req.getContextPath() + "/AdminDashboardServlet");
	                return;
	            } else {
	                // If the user is logged in and tries to access other pages, allow access
	                chain.doFilter(request, response);
	                return;
	            }
	        } else { // If the user is NOT logged in
	            if (isLoginPage || isRegisterPage) {
	                // If user is not logged in and tries to access the login or register page, allow access
	                chain.doFilter(request, response);
	            } else {
	                // If user is not logged in and tries to access other pages, redirect to login page
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

