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

import com.cravers_corner.controller.dao.CategoryDAO;
import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;

/**
 * Servlet implementation class GetCategory Servlet
 */
@WebServlet("/GetCategoryServlet")
public class GetCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategoryServlet() {
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
	           CategoryDAO categoryDAO = new CategoryDAO();
	           String keyword = request.getParameter("keyword");
	            List<Category> categoryList = categoryDAO.getAllCategories();
	            
	            
	            if (keyword != null && !keyword.trim().isEmpty()) {
	                categoryList = categoryDAO.searchCategoryByName(keyword);
	                request.setAttribute("isSearchResult", true);
	            } else {
	                categoryList = categoryDAO.getAllCategories();
	            }
	            
	            // Check if the list is not empty
	            if (categoryList != null && !categoryList.isEmpty()) {
	                // Set the food list as a request attribute to be accessed in the JSP
	                request.setAttribute("categoryList", categoryList);
	            } else {
	                // Set a message indicating no data was found
	                request.setAttribute("errorMessage", "No category found.");
	                System.out.println("No Category items are found");
	            }
	            
	            // Forward the request to the ManageCategory.jsp page to display the foods' category
	            request.getRequestDispatcher("pages/ManageCategory.jsp").forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // In case of an error, set an error message and forward to an error page or display it in manageFood.jsp
	            request.setAttribute("errorMessage", "Error fetching Category details.");
	            request.getRequestDispatcher("pages/ManageCategory.jsp").forward(request, response);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Database connection error.");
	            request.getRequestDispatcher("pages/ManageCategory.jsp").forward(request, response);
	        }
	}
	}
	
	
