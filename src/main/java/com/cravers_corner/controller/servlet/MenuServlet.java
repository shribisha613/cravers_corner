package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cravers_corner.controller.dao.CategoryDAO;
import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FoodDAO foodDAO = new FoodDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            // Get the category list from the database
            List<Category> categoryList = foodDAO.getAllCategories();
            System.out.println("Categories retrieved: " + categoryList.size());
            request.setAttribute("categoryList", categoryList);

            // Get the selected category type from the request, default to "Nepali"
            String type = request.getParameter("type");
            if (type == null || type.isEmpty()) {
                type = "Nepali"; // Default category type
            }

            // Get food items filtered by the selected category type
            List<Food> foodList;
            if (type != null && !type.isEmpty()) {
                foodList = foodDAO.getFoodByCategory(type); // Fetch food items by category type
            } else {
                foodList = foodDAO.getAllFood(); // Default to all food items if no category selected
            }

            // Search functionality (optional)
            String keyword = request.getParameter("search");
            if (keyword != null && !keyword.trim().isEmpty()) {
                foodList = foodDAO.searchFoodByName(keyword); // Perform search if keyword exists
                request.setAttribute("searchKeyword", keyword);
            }

            // Set the filtered food list to the request attributes
            request.setAttribute("foodList", foodList);

            // Forward to the JSP page
            request.getRequestDispatcher("/pages/Menu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3>Something went wrong while loading the menu. Please try again later.</h3>");
        }
    }
}

