package com.cravers_corner.controller.servlet;
// AdminDashboardServlet.java
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.AdminDashboardDAO;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        RequestDispatcher rd = request.getRequestDispatcher("/pages/AdminDashboard.jsp");
        rd.forward(request, response);
    }
}
