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

        HttpSession session = request.getSession();
        OrderDAO dao;
        try {
            dao = new OrderDAO();
            boolean updated = dao.updateOrderStatus(orderId, status);

            if (updated) {
                if ("completed".equalsIgnoreCase(status)) {
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

        response.sendRedirect(request.getContextPath() + "/GetOrderServlet"); // Ensure this servlet loads updated orders
    }
}
