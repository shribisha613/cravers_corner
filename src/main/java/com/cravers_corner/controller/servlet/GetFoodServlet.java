package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// TODO Auto-generated method stub
		 try {
	            FoodDAO foodDAO = new FoodDAO();
	           
	            String searchQuery = request.getParameter("searchQuery");
	            String sortBy = request.getParameter("sort");
	            if (sortBy == null) {
	                sortBy = "joined_date_desc"; // default latest added at first huncha
	            }

	            String sortOrder;
	            switch (sortBy) {
	                case "name":
	                    sortOrder = "f.name ASC";
	                    break;
	                case "price_asc":
	                    sortOrder = "f.price ASC";
	                    break;
	                case "price_desc":
	                    sortOrder = "f.price DESC";
	                    break;
	                case "joined_date_desc":
	                    sortOrder = "f.created_at DESC";
	                    break;
	                default:
	                    sortOrder = "f.created_at DESC";
	                    break;
	            }
	            List<Food> foodList = foodDAO.getAllFood(sortOrder);
	            
	            
	            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	                
	                foodList = foodDAO.searchFoodByName(searchQuery);
	                request.setAttribute("isSearchResult", true);
	                request.setAttribute("searchQuery", searchQuery); 
	            } else {
	                
	                foodList = foodDAO.getAllFood(sortOrder);
	                request.setAttribute("isSearchResult", false);
	            }
	            
	           
	            if (foodList != null && !foodList.isEmpty()) {
	                
	                request.setAttribute("foodList", foodList);
	            } else {
	                
	            	 request.setAttribute("errorMessage", 
	                         searchQuery != null ? "No food items found matching your search." : "No food items found.");
	                
	                System.out.println("No food items are found");
	            }
	            
	            
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
	
	
