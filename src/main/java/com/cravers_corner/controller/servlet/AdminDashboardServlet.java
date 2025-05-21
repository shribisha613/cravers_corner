package com.cravers_corner.controller.servlet;
// AdminDashboardServlet.java
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.AdminDashboardDAO;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
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

        AdminDashboardDAO dao = new AdminDashboardDAO();

        int totalCategories = dao.getTotalCategories();
        int totalFoodItems = dao.getTotalFoodItems();
        int totalCustomers = dao.getTotalCustomers();
       
        System.out.println("Total Categories = " + totalCategories);
        System.out.println("Total Food Items = " + totalFoodItems);
        System.out.println("Total Customers = " + totalCustomers);


        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalFoodItems", totalFoodItems);
        request.setAttribute("totalCustomers", totalCustomers);

        request.setAttribute("totalActiveOrders", dao.getTotalActiveOrders());

        request.setAttribute("todaysOrders", dao.getTodaysOrderCount());
        request.setAttribute("mostOrderedCategory", dao.getMostOrderedCategory());
        request.setAttribute("mostOrderedItem", dao.getMostOrderedItem());
        request.setAttribute("usersLastMonth", dao.getUsersRegisteredLastMonth());
        
        request.setAttribute("orders", dao.getRecentOrders(5));

        RequestDispatcher rd = request.getRequestDispatcher("/pages/AdminDashboard.jsp");
        rd.forward(request, response);
    }
}
