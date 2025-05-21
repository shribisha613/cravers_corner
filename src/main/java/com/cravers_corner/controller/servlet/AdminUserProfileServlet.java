package com.cravers_corner.controller.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cravers_corner.controller.dao.UserDAO;
import com.cravers_corner.controller.util.PasswordUtil;
import com.cravers_corner.controller.util.ValidationUtil;
import com.cravers_corner.model.User;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/AdminUserProfileServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
	    maxFileSize = 1024 * 1024 * 10,       // 10MB
	    maxRequestSize = 1024 * 1024 * 50     // 50MB
	)
public class AdminUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/pages/AccessDenied.jsp");
            return;
        }

        // Get the user from session
        User sessionUser = (User) session.getAttribute("userWithSession");
        
       
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByUsername(sessionUser.getUsername());
            System.out.println(sessionUser.getUsername());
            request.setAttribute("userProfile", user);
            request.setAttribute("userRole", sessionUser.getRole());
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "A system error occurred. Please try again later.");
            request.getRequestDispatcher("/pages/AdminDashboard.jsp").forward(request, response);
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
        String profile_image_url = null;
        String existingImage = request.getParameter("existing_profile_image");
        
        Part image = request.getPart("profile_image");
        
        if (image != null && image.getSize() > 0) {
        	
        
        	
            if (!ValidationUtil.isValidImageExtension(image)) {
                request.setAttribute("errorMessage", "Invalid image format. Only JPG, PNG, and GIF are allowed.");
                request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
                return;
            }
        
        String fileName = image.getSubmittedFileName();

        if (fileName != null && !fileName.isEmpty()) {
            // Get the deployment directory (root of the project)
            String storePath = request.getServletContext().getRealPath("")+ "profile_photos" ;
            
            
            
            System.out.println(storePath);  
            
            
            
            try {
                // Ensure the folder exists in the deployment directory
                File folder = new File(storePath);
                if (!folder.exists()) {
                    folder.mkdirs();  // Create the folder if it doesn't exist
                }
                String filePath = storePath+File.separator +fileName;
                System.out.println("File Path: " + filePath);
                
                image.write(filePath);
                profile_image_url = "profile_photos/" + fileName; // relative path to use in JSP
                System.out.println("File uploaded successfully: " + filePath);
            } catch (Exception e) {
                System.out.println("File upload failed: " + e.getMessage());
            }
            
            String displayPath = request.getContextPath() + "/profile_photos/" + fileName;
            System.out.println(displayPath);// Add context to file path
            request.setAttribute("profileImagePath", displayPath); 
           
        }
        }
        
        if (profile_image_url == null) {
            profile_image_url = existingImage;
        }
        
        if (!username.equals(sessionUser.getUsername())) {
            try {
                String usernameAvailabilityMessage = ValidationUtil.validateUsernameAvailability(username);
                if (usernameAvailabilityMessage != null) {
                    request.setAttribute("errorMessage", usernameAvailabilityMessage);
                    // Optionally preserve form data here for user convenience
                    request.setAttribute("userProfile", sessionUser);
                    request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
                    return;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "System error occurred during username validation.");
                request.getRequestDispatcher("/pages/UserProfile.jsp").forward(request, response);
                return;
            }
        }
        
        
        if (ValidationUtil.isNullOrEmpty(firstName) || !ValidationUtil.isValidName(firstName)) {
            request.setAttribute("errorMessage", "First name is invalid. Please enter a valid first name.");
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
            return;
        }
        
        if (ValidationUtil.isNullOrEmpty(lastName) || !ValidationUtil.isValidName(lastName)) {
            request.setAttribute("errorMessage", "Last name is invalid. Please enter a valid last name.");
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(email) || !ValidationUtil.isValidEmail(email)) {
            request.setAttribute("errorMessage", "Email is invalid. Please enter a valid email address.");
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(username) || !ValidationUtil.isValidUsername(username)) {
            request.setAttribute("errorMessage", "Username must be atleast 7 character long. Please enter a valid username.");
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(phone) || !ValidationUtil.isValidPhoneNumber(phone)) {
            request.setAttribute("errorMessage", "Phone number is invalid. Please enter a valid phone number.");
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(address) || !ValidationUtil.isValidAddress(address)) {
            request.setAttribute("errorMessage", "Address is invalid. Please enter a valid address.");
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
            return;
        }

        // Password validation
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            if (!ValidationUtil.doPasswordsMatch(newPassword, confirmPassword)) {
                request.setAttribute("errorMessage", "Passwords do not match.");
                request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
                return;
            }
            
            if (!ValidationUtil.isValidPassword(newPassword)) {
                request.setAttribute("errorMessage", "Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.");
                request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
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
                    request.setAttribute("errorMessage", "Please retype your Password to make changes.");
                    request.setAttribute("userProfile", currentUser);
                    request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);
                    return;
                }
            }
            
            if (profile_image_url != null) {
                currentUser.setProfile_image_url(profile_image_url);
                isUpdated = true;
                
            }

            if (isUpdated) {
                boolean success = userDAO.updateUser(currentUser);
                if (success) {
                    session.setAttribute("userWithSession", currentUser);
                    request.setAttribute("success", "Your profile has been updated successfully. Please use your updated details to log in.");
                    System.out.println("Successfully updated profile");
                } else {
                    request.setAttribute("errorMessage", "Failed to Update your profile. Please try again.");
                }
            } else {
                request.setAttribute("info", "It looks like nothing was changed in your profile.");
            }

            request.setAttribute("userProfile", currentUser);
            request.getRequestDispatcher("/pages/AdminUserProfile.jsp").forward(request, response);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pages/AdminUserProfile.jsp");
        }
    }

	}

