package com.cravers_corner.controller.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.controller.util.ValidationUtil;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;

/**
 * Servlet implementation class AddFoodServlet
 */
@WebServlet("/AddFoodServlet")


@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,       // 1MB threshold for buffering
	    maxFileSize = 1024 * 1024 * 20,        // 20MB max file size
	    maxRequestSize = 1024 * 1024 * 25      // 25MB total request size
	)

public class AddFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFoodServlet() {
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
		
		FoodDAO foodDAO;
		try {
			foodDAO = new FoodDAO();
			List<Category> categoryList = foodDAO.getAllCategories();
			System.out.println("Categories retrieved: " + categoryList.size());
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/pages/AddFood.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
		String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String serving_size = request.getParameter("serving_size");
        String category_id = request.getParameter("category_id");
        Part image = request.getPart("image");
        
        
        
      
        
         
        String fileName = image.getSubmittedFileName();
        String image_url = ""; // Initialize image_url

        if (fileName != null && !fileName.isEmpty()) {
            try {
                
            	String storePath = request.getServletContext().getRealPath("")+ "food_images" ;

                System.out.println("Store Path: " + storePath);

                // ✅ Ensure the folder exists
                File folder = new File(storePath);
                if (!folder.exists()) {
                    folder.mkdirs();  // Create the folder if it doesn't exist
                }

                
                String filePath = storePath + File.separator + fileName;
                System.out.println("File Path: " + filePath);

                image.write(filePath);  // Save the image to real project path

                
                image_url = "food_images/" + fileName;

                System.out.println("Food image File uploaded successfully: " + filePath);

                String externalFolderPath = "C:/Users/DELL/eclipse-workspace/cravers_corner/src/main/webapp/food_images";  // <-- Change this path as needed
                File externalFolder = new File(externalFolderPath);
                if (!externalFolder.exists()) {
                    externalFolder.mkdirs();
                }
                File sourceFile = new File(filePath); // file just saved inside webapp
                File externalFile = new File(externalFolder, fileName);

                try {
                    Files.copy(sourceFile.toPath(), externalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied image to external folder: " + externalFile.getAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Failed to copy image externally: " + e.getMessage());
                }
                String displayPath = request.getContextPath() + "/food_images/" + fileName;
                System.out.println("Display Path (for JSP): " + displayPath);

                
                request.setAttribute("food_image_path", displayPath);

            } catch (Exception e) {
                System.out.println("File upload failed: " + e.getMessage());
                request.setAttribute("errorMessage", "Image upload failed. Please try again.");
            }
        } else {
            request.setAttribute("errorMessage", "No image file selected.");
        }
            
            if (ValidationUtil.isNullOrEmpty(name)) {
                setErrorAndRedirect(session, response, "Food name is required.", name, description, price, category_id, serving_size, image_url);
                return;
            } else if (!ValidationUtil.isValidName(name)) {
                setErrorAndRedirect(session, response, "Food name must be valid", name, description, price, category_id, serving_size, image_url);
                return;
            }
            
            
            if (ValidationUtil.isNullOrEmpty(description)) {
                setErrorAndRedirect(session, response, "Food desciption is required.", name, description, price, category_id, serving_size, image_url);
                return;
            } 



            if (ValidationUtil.isNullOrEmpty(price) || !price.matches("^\\d+(\\.\\d{1,2})?$")) {
                setErrorAndRedirect(session, response, "Please enter a valid price in NPR (e.g., 100 or 99.99).", name, description, price, category_id, serving_size, image_url);
                return;
            }

            if (ValidationUtil.isNullOrEmpty(serving_size)) {
                setErrorAndRedirect(session, response, "Serving size is required.", name, description, price, category_id, serving_size, image_url);
                return;
            }

            if (ValidationUtil.isNullOrEmpty(category_id) || category_id.equals("0")) {
                setErrorAndRedirect(session, response, "Please select a valid food category.", name, description, price, category_id, serving_size, image_url);
                return;
            }
            
            

            if (image == null || ValidationUtil.isNullOrEmpty(image.getSubmittedFileName())) {
                setErrorAndRedirect(session, response, "Please upload a food image.", name, description, price, category_id, serving_size, image_url);
                return;
            }

            if (!ValidationUtil.isValidImageExtension(image)) {
                setErrorAndRedirect(session, response, "Only JPG, JPEG, PNG, or GIF image formats are allowed.", name, description, price, category_id, serving_size, image_url);
                return;
            }
            
            if (!ValidationUtil.isFileSizeWithinLimit(image, 1024 * 1024 * 20)) {
                setErrorAndRedirect(session, response, "Image size must be less than 20MB.",
                                    name, description, price, category_id, serving_size, "");
                return;
            }

            
            try {
                Food food = new Food();
                food.setName(name);
                food.setDescription(description);
                food.setPrice(Double.parseDouble(price));
                food.setServing_size(serving_size);
                food.setCategory_id(Integer.parseInt(category_id));
                food.setImage_url(image_url);
                food.setCreated_at(new Timestamp(System.currentTimeMillis()));
                food.setUpdated_at(new Timestamp(System.currentTimeMillis()));

                FoodDAO foodDAO = new FoodDAO();
                
                boolean isInserted = foodDAO.addFood(food);

                if (isInserted) {
                	
                    session.setAttribute("messageType", "success");
                    session.setAttribute("message", "Food item added successfully!");
                    List<Category> categoryList = foodDAO.getAllCategories();
                    request.setAttribute("categoryList", categoryList);
                    request.getRequestDispatcher("/pages/AddFood.jsp").forward(request, response);
                    System.out.println("Food added successfully");
                } else {
                	setErrorAndRedirect(session, response, "Failed to add category. Please try again.", name, description, price, category_id, serving_size, image_url);
                    
                    List<Category> categoryList = foodDAO.getAllCategories();
                    request.setAttribute("categoryList", categoryList);
                   
                    request.getRequestDispatcher("/pages/AddFood.jsp").forward(request, response);
                    System.out.println("Failed to add food");
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                setErrorAndRedirect(session, response,
                	    "A system error occurred. Please try again later.",
                	    name, description, price, category_id, serving_size, image_url);
                request.getRequestDispatcher("/pages/AddFood.jsp").forward(request, response);
            }
            
            
        }
          
        
        
	private void setErrorAndRedirect(HttpSession session, HttpServletResponse response, String errorMsg, String name, String description,  String price, String category_id, String serving_size, String image_url) throws IOException {
					session.setAttribute("messageType", "error");
					session.setAttribute("message", errorMsg);
					
					// Preserve form values
					session.setAttribute("name", name);
					session.setAttribute("description", description);
					session.setAttribute("price", price);
					session.setAttribute("category_id", category_id);
					session.setAttribute("serving_size", serving_size);
					session.setAttribute("image_url", image_url);
					
					 try {
					        FoodDAO foodDAO = new FoodDAO();
					        List<Category> categoryList = foodDAO.getAllCategories();
					        session.setAttribute("categoryList", categoryList);
					    } catch (Exception e) {
					        e.printStackTrace();
					        session.setAttribute("categoryList", null);
					    }

					 response.sendRedirect("pages/AddFood.jsp");
}
}