package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Food;

/**
 * Servlet implementation class GetFoodServlet
 */
@WebServlet("/GetFoodServlet")
public class GetFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            FoodDAO foodDAO = new FoodDAO();
	            List<Food> foodList = foodDAO.getAllFood();
	            
	            // Check if the list is not empty
	            if (foodList != null && !foodList.isEmpty()) {
	                // Set the food list as a request attribute to be accessed in the JSP
	                request.setAttribute("foodList", foodList);
	            } else {
	                // Set a message indicating no data was found
	                request.setAttribute("errorMessage", "No food items found.");
	                System.out.println("No food items are found");
	            }
	            
	            // Forward the request to the ManageFood.jsp page to display the food items
	            request.getRequestDispatcher("pages/ManageFood.jsp").forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // In case of an error, set an error message and forward to an error page or display it in manageFood.jsp
	            request.setAttribute("errorMessage", "Error fetching food details.");
	            request.getRequestDispatcher("pages/ManageFood.jsp").forward(request, response);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Database connection error.");
	            request.getRequestDispatcher("pages/ManageFood.jsp").forward(request, response);
	        }
	}
	}
	
	
