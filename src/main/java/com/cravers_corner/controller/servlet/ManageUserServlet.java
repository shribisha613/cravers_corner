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

import com.cravers_corner.controller.dao.UserDAO;
import com.cravers_corner.model.User;

/**
 * Servlet implementation class ManageUserServlet
 */
@WebServlet("/ManageUserServlet")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    HttpSession session = request.getSession(false);
		    
		    if (session == null || session.getAttribute("currentAdmin") == null) {
		        response.sendRedirect("pages/Login.jsp");
		        return;
		    }
		    
	        User currentAdmin = (User) session.getAttribute("currentAdmin");
	        
	        try {
	            UserDAO userDAO = new UserDAO();
	            
	            String searchQuery = request.getParameter("searchQuery");
	            String sortBy = request.getParameter("sort");
	            if (sortBy == null) {
	                sortBy = "userId"; // Default to user_id sorting
	            }
	            String sortOrder;
	            switch (sortBy) {
	                case "userId":
	                    sortOrder = "user_id";
	                    break;
	                case "name":
	                    sortOrder = "first_name";
	                    break;
	                case "joined_date_desc":
	                    sortOrder = "created_at DESC";
	                    break;
	                case "joined_date_asc":
	                    sortOrder = "created_at ASC";
	                    break;
	                default:
	                    sortOrder = "user_id ASC"; 
	            }
	            
//	            List<User> userList = userDAO.getAllUsersExceptAdmin(currentAdmin.getUser_id(), sortOrder);
	            List<User> userList = userDAO.getAllUsers(sortOrder);
	            
	            System.out.println("Current admin user id " + currentAdmin.getUser_id());
	            
	            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	            	 userList = userDAO.searchUsers(searchQuery);
	                request.setAttribute("isSearchResult", true);
	                request.setAttribute("searchQuery", searchQuery);
	            } else {
	                
	                userList = userDAO.getAllUsers(sortOrder);
	                request.setAttribute("isSearchResult", false);
	            }
	            
	            if (userList != null && !userList.isEmpty()) {
	                request.setAttribute("userList", userList);
	                request.setAttribute("currentSort", sortBy);
	            } else {
	                request.setAttribute("errorMessage", 
	                    searchQuery != null ? "No users found matching your search." : "No users found.");
	                System.out.println("No users found in database");
	            }
	            
	            request.getRequestDispatcher("/pages/ManageUser.jsp").forward(request, response);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Database error occurred while fetching users.");
	            request.getRequestDispatcher("/pages/ManageUser.jsp").forward(request, response);
	        } catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
	    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
