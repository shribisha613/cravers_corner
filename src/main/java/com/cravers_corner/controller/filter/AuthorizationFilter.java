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

@WebFilter("/pages/*")  // This will intercept all requests to pages inside the 'pages' folder
public class AuthorizationFilter implements Filter {

    private static final String[] ADMIN_PAGES = { "AdminDashboard.jsp", "ManageFood.jsp", "AdminUserProfile.jsp" };
    private static final String[] CUSTOMER_PAGES = { "Home.jsp", "UserProfile.jsp", "Menu.jsp" };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional: Filter initialization logic here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Get session, if it exists

        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String uri = req.getRequestURI();  // Get the URI of the requested page (e.g., /pages/manageFood.jsp)

        // Skip the filter if the user is trying to access the login page
        if (uri.contains("Login.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        // Check if the role is null (i.e., user not logged in)
        if (role == null) {
            res.sendRedirect(req.getContextPath() + "/pages/Login.jsp"); 
            System.out.println("User is not logged in"); // Redirect to login page
            return;
        }

        // Check if the page is for admin or customer and validate the role
        if (isAdminPage(uri) && !"admin".equals(role)) {
            // If it's an admin page and the user is not an admin, redirect to unauthorized page
            System.out.println("User is logged In but not authorized to access admin page");
            res.sendRedirect(req.getContextPath() + "/pages/AccessDenied.jsp");
            return;
        }

        if (isCustomerPage(uri) && !"customer".equals(role)) {
            // If it's a customer page and the user is not a customer, redirect to unauthorized page
            System.out.println("User is logged In but not authorized to access customer page");
            res.sendRedirect(req.getContextPath() + "/pages/AccessDenied.js");
            return;
        }

        // If the page is accessible by the current role, allow access to the page
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Optional: Filter destruction logic here
    }

    // Helper method to check if the page is an admin page
    private boolean isAdminPage(String uri) {
        for (String page : ADMIN_PAGES) {
            if (uri.contains(page)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if the page is a customer page
    private boolean isCustomerPage(String uri) {
        for (String page : CUSTOMER_PAGES) {
            if (uri.contains(page)) {
                return true;
            }
        }
        return false;
    }
}
