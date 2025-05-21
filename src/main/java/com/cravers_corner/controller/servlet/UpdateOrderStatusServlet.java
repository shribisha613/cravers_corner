package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.OrderDAO;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateOrderStatusServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        
        
        String status = request.getParameter("status");

        HttpSession session = request.getSession(false);

	    // Check if session exists and user is logged in
	    if (session == null || session.getAttribute("userWithSession") == null) {
	        response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
	        return;
	    }

	    // Check if the logged-in user has the 'admin' role
	    String role = (String) session.getAttribute("role"); // adjust attribute name as per your login logic
	    if (role == null || !role.equalsIgnoreCase("admin")) {
	        response.sendRedirect(request.getContextPath() + "/pages/AccessDenied.jsp");
	        return;
	    }
        OrderDAO dao;
        boolean isFromAdminDashboard = false;

        String referer = request.getHeader("referer");
        if (referer != null && referer.contains("AdminDashboard")) {
            isFromAdminDashboard = true;
        }

        try {
            dao = new OrderDAO();
            boolean updated = dao.updateOrderStatus(orderId, status);

            if (updated) {
                if ("deleted".equalsIgnoreCase(status)) {
                    session.setAttribute("successMessage", "Order #" + orderId + " has been deleted.");
                } else if ("completed".equalsIgnoreCase(status)) {
                    session.setAttribute("successMessage", "Order #" + orderId + " has been marked as completed.");
                } else {
                    session.setAttribute("successMessage", "Order #" + orderId + " status updated to " + status + ".");
                }
            } else {
                session.setAttribute("errorMessage", "Failed to update status for Order #" + orderId + ".");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "An error occurred while updating order status.");
        }

        if (isFromAdminDashboard) {
            response.sendRedirect(request.getContextPath() + "/AdminDashboardServlet");
        } else {
            response.sendRedirect(request.getContextPath() + "/GetOrderServlet");
        }
    }
}
