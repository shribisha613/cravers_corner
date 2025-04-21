package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }
		 User sessionUser = (User) session.getAttribute("userWithSession");
		 
		 try {
	            UserDAO userDAO = new UserDAO();
	            User user = userDAO.getUserByUsername(sessionUser.getUsername());
	            System.out.println(sessionUser.getUsername());
	            request.setAttribute("userProfile", user);
	            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "A system error occurred. Please try again later.");
	            request.getRequestDispatcher("/pages/Home.jsp").forward(request, response);
	            
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }
        
        User sessionUser = (User) session.getAttribute("userWithSession");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        
        if (ValidationUtil.isNullOrEmpty(firstName) || !ValidationUtil.isValidName(firstName)) {
            request.setAttribute("errorMessage", "First name is invalid. Please enter a valid first name.");
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
            return;
        }
        
        if (ValidationUtil.isNullOrEmpty(lastName) || !ValidationUtil.isValidName(lastName)) {
            request.setAttribute("errorMessage", "Last name is invalid. Please enter a valid last name.");
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(email) || !ValidationUtil.isValidEmail(email)) {
            request.setAttribute("errorMessage", "Email is invalid. Please enter a valid email address.");
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(username) || !ValidationUtil.isValidUsername(username)) {
            request.setAttribute("errorMessage", "Username must be atleast 7 character long. Please enter a valid username.");
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(phone) || !ValidationUtil.isValidPhoneNumber(phone)) {
            request.setAttribute("errorMessage", "Phone number is invalid. Please enter a valid phone number.");
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(address) || !ValidationUtil.isValidAddress(address)) {
            request.setAttribute("errorMessage", "Address is invalid. Please enter a valid address.");
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
            return;
        }

        // Password validation
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            if (!ValidationUtil.doPasswordsMatch(newPassword, confirmPassword)) {
                request.setAttribute("errorMessage", "Passwords do not match.");
                request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
                return;
            }
            
            if (!ValidationUtil.isValidPassword(newPassword)) {
                request.setAttribute("errorMessage", "Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.");
                request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
                return;
            }
        }
        
        
        try {
            UserDAO userDAO = new UserDAO();
            User currentUser = userDAO.getUserByUsername(sessionUser.getUsername());
            System.out.println("Fetched user: " + currentUser.getFirst_name());

            boolean isUpdated = false;

            // Check each field
            if (!firstName.equals(currentUser.getFirst_name())) {
                currentUser.setFirst_name(firstName);
                isUpdated = true;
            }

            if (!lastName.equals(currentUser.getLast_name())) {
                currentUser.setLast_name(lastName);
                isUpdated = true;
            }

            if (!email.equals(currentUser.getEmail())) {
                currentUser.setEmail(email);
                isUpdated = true;
            }

            if (!username.equals(currentUser.getUsername())) {
                currentUser.setUsername(username);
                isUpdated = true;
            }

            if (!phone.equals(currentUser.getPhone())) {
                currentUser.setPhone(phone);
                isUpdated = true;
            }

            if (!address.equals(currentUser.getCurrent_address())) {
                currentUser.setCurrent_address(address);
                currentUser.setShipping_address(address);
                isUpdated = true;
            }

            // Password check
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                if (newPassword.equals(confirmPassword)) {
                    String encryptedPassword = PasswordUtil.encrypt(newPassword);
                    if (!encryptedPassword.equals(currentUser.getPassword())) {
                        currentUser.setPassword(encryptedPassword);
                        isUpdated = true;
                    }
                } else {
                    request.setAttribute("errorMessage", "Passwords do not match.");
                    request.setAttribute("userProfile", currentUser);
                    request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
                    return;
                }
            }

            if (isUpdated) {
                boolean success = userDAO.updateUser(currentUser);
                if (success) {
                    session.setAttribute("userWithSession", currentUser); // Update session
                    request.setAttribute("success", "Profile updated successfully.");
                    System.out.println("Successfully updated profile");
                } else {
                    request.setAttribute("errorMessage", "Update failed. Try again.");
                }
            } else {
                request.setAttribute("info", "No changes made.");
            }

            request.setAttribute("userProfile", currentUser);
            request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pages/UserProfile.jsp");
        }
    }

	}


