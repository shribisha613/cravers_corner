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
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.UserDAO;
import com.cravers_corner.controller.util.PasswordUtil;
import com.cravers_corner.controller.util.ValidationUtil;
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
		
		
		String validationMessage = validateRegistrationForm(request);
		if (validationMessage != null) {
			handleError(request, response, validationMessage);
			return;
		}
		
		 String username = request.getParameter("username");

		  
		    String usernameAvailabilityMessage;
			try {
				usernameAvailabilityMessage = ValidationUtil.validateUsernameAvailability(username);
				
				 if (usernameAvailabilityMessage != null) {
				        request.setAttribute("errorMessage", usernameAvailabilityMessage);
				        request.setAttribute("first_name", request.getParameter("first_name"));
			            request.setAttribute("last_name", request.getParameter("last_name"));
			            request.setAttribute("username", username);
			            request.setAttribute("phone", request.getParameter("phone"));
			            request.setAttribute("address", request.getParameter("address"));
			            request.setAttribute("email", request.getParameter("email"));
			            request.setAttribute("password", request.getParameter("password"));
			            request.setAttribute("confirm_password", request.getParameter("confirm_password"));
			            // Forward the request to the register page again
			            
				        request.getRequestDispatcher("/pages/Register.jsp").forward(request, response);
				        return;
				    }
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   

		response.setContentType("text/html");
		System.out.println("Received registration form.");
		boolean isRegistered= false;
		PrintWriter out = response.getWriter();
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
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
	        
	            String encrypted_password = PasswordUtil.encrypt(password);

	            User user = new User();
	            user.setFirst_name(first_name);
	            user.setLast_name(last_name);
	            user.setUsername(username);
	            user.setPhone(phone);
	            user.setCurrent_address(address);
	            user.setShipping_address(address); // same for now
	            user.setEmail(email);
	            user.setPassword(encrypted_password); // Note: Add hashing in production
	            user.setRole("customer"); // default role
	            user.setProfile_image_url("profile_photos/default_profile.png"); // default profile image
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
	
	private String validateRegistrationForm(HttpServletRequest request) {
		String first_name = request.getParameter("first_name");
	    String last_name = request.getParameter("last_name");
	    String username = request.getParameter("username");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirm_password");

	    // Null or empty checks
	    if (ValidationUtil.isNullOrEmpty(first_name))
	        return "First name is required.";
	    if (ValidationUtil.isNullOrEmpty(last_name))
	        return "Last name is required.";
	    if (ValidationUtil.isNullOrEmpty(username))
	        return "Username is required.";
	    if (ValidationUtil.isNullOrEmpty(phone))
	        return "Phone number is required.";
	    if (ValidationUtil.isNullOrEmpty(address))
	        return "Address is required.";
	    if (ValidationUtil.isNullOrEmpty(email))
	        return "Email is required.";
	    if (ValidationUtil.isNullOrEmpty(password))
	        return "Password is required.";
	    if (ValidationUtil.isNullOrEmpty(confirmPassword))
	        return "Please confirm your password.";

	    // Field-specific validations
	    
	    if (!ValidationUtil.isValidName(first_name))
	        return "First name must contain only letters and be at least 2 characters long.";
	    if (!ValidationUtil.isValidName(last_name))
	        return "Last name must contain only letters and be at least 2 characters long.";
	    if (!ValidationUtil.isValidUsername(username) || username.length() < 7)
	        return "Username must start with a letter, be alphanumeric, and at least 7 characters long.";
	    if (!ValidationUtil.isValidPhoneNumber(phone))
	        return "Phone number must be exactly 10 digits.";
	    if (!ValidationUtil.isValidEmail(email))
	        return "Invalid email format.";
	    if (!ValidationUtil.isValidPassword(password))
	        return "Password must be at least 8 characters long and include an uppercase letter, a number, and a symbol.";
	    if (!ValidationUtil.doPasswordsMatch(password, confirmPassword))
	        return "Passwords do not match. Please try again. ";

	    return null; // All validations passed
	}

	
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
    // Set the error message in the request attribute
    req.setAttribute("errorMessage", message);

    // Retain the values the user has entered in the form fields
    req.setAttribute("first_name", req.getParameter("first_name"));
    req.setAttribute("last_name", req.getParameter("last_name"));
    req.setAttribute("username", req.getParameter("username"));
    req.setAttribute("phone", req.getParameter("phone"));
    req.setAttribute("address", req.getParameter("address"));
    req.setAttribute("email", req.getParameter("email"));
    req.setAttribute("password", req.getParameter("password"));
    req.setAttribute("confirm_password", req.getParameter("confirm_password"));

    // Forward the request back to the register page with the error message
    req.getRequestDispatcher("/pages/Register.jsp").forward(req, resp);
}
	
	

}
