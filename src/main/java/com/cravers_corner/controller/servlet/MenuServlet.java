package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.CategoryDAO;
import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    	// Get existing session without creating new one
    	 HttpSession session = request.getSession(false);
    	 // If session doesn't exist or user is not logged in, redirect to login page
         if (session == null || session.getAttribute("userWithSession") == null) {
             response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
             return;
         }
         
         // Get user role from session
         String role = (String) session.getAttribute("role");
         // If user is admin, redirect to access denied page (admins not allowed here)
         if ("admin".equals(role)) {
             response.sendRedirect(request.getContextPath() + "/pages/AccessDenied.jsp");
             return;
         }
    	try {
            FoodDAO foodDAO = new FoodDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            // Get the category list from the database
            List<Category> categoryList = foodDAO.getAllCategories();
            System.out.println("Categories retrieved: " + categoryList.size());
            request.setAttribute("categoryList", categoryList);

            // Get the selected category type from the request parameter, default to "Nepali"
            String type = request.getParameter("type");
            // If no category is selected, leave 'type' empty to show all food items
            if (type == null || type.isEmpty()) {
                type = ""; // No default category filter, show all
            }
            
            // Store the selected type in request scope for use in JSP (e.g., to highlight active category)
            request.setAttribute("type", type);
            
            // Initialize the list of food items
            List<Food> foodList;
            // If a category type is selected, get food items for that category
            if (!type.isEmpty()) {
                foodList = foodDAO.getFoodByCategory(type);
            } else {
            	// If no category is selected, fetch all available food items
                foodList = foodDAO.getAllFood();
            }


            // Search functionality 
            String keyword = request.getParameter("search");
            if (keyword != null && !keyword.trim().isEmpty()) {
                foodList = foodDAO.searchFoodByName(keyword); // Perform search if keyword exists
                request.setAttribute("searchKeyword", keyword);
            }
            
            // Handle sort order parameter
            String sort = request.getParameter("sort"); // Get sort parameter
            System.out.println("Sort order: " + sort);
            
            // Sort functionality
            if (sort != null && !sort.isEmpty()) {
                if (sort.equals("asc")) {
                    foodList.sort((f1, f2) -> Double.compare(f1.getPrice(), f2.getPrice()));
                } else if (sort.equals("desc")) {
                    foodList.sort((f1, f2) -> Double.compare(f2.getPrice(), f1.getPrice()));
                }
                // Pass sort order to JSP for UI select option
                request.setAttribute("sortOrder", sort);
            }
            
            // Set filtered food list as request attribute for display
            request.setAttribute("foodList", foodList);

            // Forward request and response to the menu JSP page
            request.getRequestDispatcher("/pages/Menu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // If exception occurs, show error message on page
            response.setContentType("text/html");
            response.getWriter().println("<h3>Something went wrong while loading the menu. Please try again later.</h3>");
        }
    }
}

