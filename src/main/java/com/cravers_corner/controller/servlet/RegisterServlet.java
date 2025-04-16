package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cravers_corner.controller.dao.UserDAO;
import com.cravers_corner.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");
		System.out.println("Received registration form.");
		boolean isRegistered= false;
		PrintWriter out = response.getWriter();
		 String full_name = request.getParameter("full_name");
	        String username = request.getParameter("username");
	        String phone = request.getParameter("phone");
	        String address = request.getParameter("address");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String confirm_password = request.getParameter("confirm_password");
	
	        try {
	            // Basic password confirmation check
	            if (!password.equals(confirm_password)) {
	                request.setAttribute("errorMessage", "Passwords do not match. Please try again.");
	                request.getRequestDispatcher("/pages/Register.jsp").forward(request, response);
	                return;
	            }
	        

	            User user = new User();
	            user.setFull_name(full_name);
	            user.setUsername(username);
	            user.setPhone(phone);
	            user.setCurrent_address(address);
	            user.setShipping_address(address); // same for now
	            user.setEmail(email);
	            user.setPassword(password); // Note: Add hashing in production
	            user.setRole("customer"); // default role
	            user.setProfile_image_url(""); // default profile image
	            user.setCreated_at(new Timestamp(System.currentTimeMillis()));
	            user.setUpdated_at(new Timestamp(System.currentTimeMillis()));

	            // DAO operation
	            UserDAO userdao = new UserDAO();
	            isRegistered = userdao.register(user);

	            if (isRegistered == true) {
	                // Redirect to login after successful registration
	            	out.println("User added successfully");
	                response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
	            } else {
	            	
	            	out.println("Failed to add student");
	                // Registration failed
	                request.setAttribute("errorMessage", "Registration failed. Please try again.");
	                request.getRequestDispatcher("/pages/Register.jsp").forward(request, response);
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	        	e.printStackTrace(); 
	            request.setAttribute("errorMessage", "A system error occurred. Please try again later.");
	            request.getRequestDispatcher("/pages/Register.jsp").forward(request, response);
	        } finally {
	            if (out != null) {
	                out.close();
	            }
	        }
	    } 
	

}

