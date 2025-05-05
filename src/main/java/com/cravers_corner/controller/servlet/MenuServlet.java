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


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            FoodDAO foodDAO = new FoodDAO();

            List<Food> foodList = foodDAO.getAllFood();
            request.setAttribute("foodList", foodList);
            request.getRequestDispatcher("/pages/Menu.jsp").forward(request, response);
            
            CategoryDAO categoryDAO =new CategoryDAO();
            
            List<Category> categoryList = foodDAO.getAllCategories();
            System.out.println("Categories retrieved: " + categoryList.size());
			request.setAttribute("categoryList", categoryList);
            
        } catch (Exception e) {
        	e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3>Something went wrong while loading the menu. Please try again later.</h3>");
        }
	}
}
