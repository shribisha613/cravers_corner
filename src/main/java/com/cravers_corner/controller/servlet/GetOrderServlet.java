package com.cravers_corner.controller.servlet;

import com.cravers_corner.model.Order;
import com.cravers_corner.controller.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/GetOrderServlet")
public class GetOrderServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
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
		
		
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orderList = orderDAO.getAllOrders();

            request.setAttribute("orders", orderList);
            request.getRequestDispatcher("/pages/Order.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to fetch orders.");
            request.getRequestDispatcher("/pages/Order.jsp").forward(request, response);
        }
    }
}
