package com.cravers_corner.controller.servlet;

import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.model.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserOrderServlet")
public class UserOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	HttpSession session = request.getSession(false);

    	// Check if session exists and user is logged in
    	if (session == null || session.getAttribute("userWithSession") == null) {
    	    response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
    	    return;
    	}

    	// Check if the logged-in user has the 'customer' role
    	String role = (String) session.getAttribute("role");
    	if (role == null || !role.equalsIgnoreCase("customer")) {
    	    response.sendRedirect(request.getContextPath() + "/pages/AccessDenied.jsp");
    	    return;
    	}
    	
    

    	Integer uidObj = (Integer) session.getAttribute("user_id");
    	if (uidObj == null) {
    	    response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
    	    return;
    	}

    	int userId = uidObj.intValue();

        
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> userOrders = orderDAO.getCompletedOrdersByCustomerId(userId);
            request.setAttribute("orders", userOrders);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to fetch orders. Please try again later.");
        }
        

        request.getRequestDispatcher("/pages/MyOrders.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
