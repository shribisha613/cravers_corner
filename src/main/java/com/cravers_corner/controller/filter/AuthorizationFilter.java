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

@WebFilter(urlPatterns = {"/pages/admin/*", "/pages/customer/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String role = (session != null && session.getAttribute("role") != null)
            ? session.getAttribute("role").toString().toLowerCase()
            : null;

        String uri = req.getRequestURI();

        System.out.println("Request URI: " + uri);
        System.out.println("Role: " + role);

        if (uri.contains("/admin/") && !"admin".equals(role)) {
            res.sendRedirect(req.getContextPath() + "/pages/AccessDenied.jsp");
            return;
        }

        if (uri.contains("/customer/") && !"customer".equals(role)) {
            res.sendRedirect(req.getContextPath() + "/pages/AccessDenied.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {}
    public void destroy() {}
}
