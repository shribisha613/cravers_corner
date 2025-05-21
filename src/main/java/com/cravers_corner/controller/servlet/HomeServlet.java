package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.CategoryDAO;
import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDAO categoryDAO;
	private FoodDAO foodDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("userWithSession") == null) {
	            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
	            return;
	        }
	        
	        String role = (String) session.getAttribute("role");
	        if ("admin".equals(role)) {
	            response.sendRedirect(request.getContextPath() + "/pages/AccessDenied.jsp");
	            return;
	        }
		
		try {
			categoryDAO = new CategoryDAO();
			foodDAO = new FoodDAO();

			List<Category> categoryList = categoryDAO. getAllCategoriesForHome();
			List<List<Food>> allCategoryFoods = new ArrayList<>();
			
			for (Category category : categoryList) {
				List<Food> foods = categoryDAO.getTopFoodsByCategory(category.getCategory_id(), 4);
				allCategoryFoods.add(foods);
			}
			List<Food> topOrderedFoods = foodDAO.getTop3MostOrderedFoods();

			request.setAttribute("categoryList", categoryList);
			request.setAttribute("allCategoryFoods", allCategoryFoods);
			request.setAttribute("topOrderedFoods", topOrderedFoods);
			request.getRequestDispatcher("/pages/Home.jsp").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ServletException("Database error.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

