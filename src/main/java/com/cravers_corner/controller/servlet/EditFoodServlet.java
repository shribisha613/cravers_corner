package com.cravers_corner.controller.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.controller.util.ValidationUtil;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;

/**
 * Servlet implementation class EditFoodServlet
 */

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 1024 * 1024 * 20,
	    maxRequestSize = 1024 * 1024 * 25
	)
@WebServlet("/EditFoodServlet")
public class EditFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		 try {
		        // Get food ID from request parameter
		        int food_id = Integer.parseInt(request.getParameter("id"));
		        
		        // Get food details and categories from FoodDAO
		        FoodDAO foodDao = new FoodDAO();
		        Food food = foodDao.getFoodById(food_id);
		        System.out.println("The food is " + food.getName()+ " and id is"+ food.getFood_id());
		        List<Category> categoryList = foodDao.getAllCategories();
		        
		        // Set attributes for JSP
		        request.setAttribute("food", food);
		        request.setAttribute("categoryList", categoryList);
		        
		        // Forward to edit page
		        request.getRequestDispatcher("pages/EditFood.jsp").forward(request, response);
		        
		    } catch (NumberFormatException e) {
		        // Handle invalid ID
		        response.sendRedirect(request.getContextPath() + "/GetFoodServlet");
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.sendRedirect(request.getContextPath() + "/GetFoodServlet");
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

		    
		    try {
		        int foodId = Integer.parseInt(request.getParameter("food_id"));
		        String name = request.getParameter("name");
		        String description = request.getParameter("description");
		        String price = request.getParameter("price");
		        String servingSize = request.getParameter("serving_size");
		        String categoryId = request.getParameter("category_id");
		        String existingImage = request.getParameter("existing_image");
		        Part image = request.getPart("image");
		        
		        String image_url = existingImage;

		        if (image != null && image.getSize() > 0 && !image.getSubmittedFileName().isEmpty()) {
		            if (!ValidationUtil.isValidImageExtension(image)) {
		                setErrorAndRedirect(request,session, response, "Only JPG, JPEG, PNG, or GIF image formats are allowed.", name, description, price, categoryId, servingSize, image_url);
		                return;
		            }

		            if (!ValidationUtil.isFileSizeWithinLimit(image, 1024 * 1024 * 20)) {
		                setErrorAndRedirect(request,session, response, "Image size must be less than 20MB.", name, description, price, categoryId, servingSize, image_url);
		                return;
		            }

		            try { 
		                String storePath = request.getServletContext().getRealPath("") + "food_images";
		                File folder = new File(storePath);
		                if (!folder.exists()) {
		                    folder.mkdirs();
		                }

		                String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
		                String filePath = storePath + File.separator + fileName;
		                image.write(filePath);

		                image_url = "food_images/" + fileName;
		            } catch (Exception e) {
		                e.printStackTrace();
		                setErrorAndRedirect(request,session, response, "Image upload failed. Please try again.", name, description, price, categoryId, servingSize, image_url);
		                return;
		            }
		        }
		        
		        if (ValidationUtil.isNullOrEmpty(name)) {
		            setErrorAndRedirect(request,session, response, "Food name is required.", name, description, price, categoryId, servingSize, image_url);
		            return;
		        }

		        if (ValidationUtil.isNullOrEmpty(description)) {
		            setErrorAndRedirect(request,session, response, "Food description is required.", name, description, price, categoryId, servingSize, image_url);
		            return;
		        }

		        if (ValidationUtil.isNullOrEmpty(price) || !price.matches("^\\d+(\\.\\d{1,2})?$")) {
		            setErrorAndRedirect(request,session, response, "Please enter a valid price (e.g., 99 or 99.99).", name, description, price, categoryId, servingSize, image_url);
		            return;
		        }

		        if (ValidationUtil.isNullOrEmpty(servingSize)) {
		            setErrorAndRedirect(request,session, response, "Serving size is required.", name, description, price, categoryId, servingSize, image_url);
		            return;
		        }

		        if (ValidationUtil.isNullOrEmpty(categoryId) || categoryId.equals("0")) {
		            setErrorAndRedirect(request,session, response, "Please select a valid food category.", name, description, price, categoryId, servingSize, image_url);
		            return;
		        }

		        // Build Food object
		        Food food = new Food();
		        food.setFood_id(foodId);
		        food.setName(name);
		        food.setDescription(description);
		        food.setPrice(Double.parseDouble(price));
		        food.setServing_size(servingSize);
		        food.setCategory_id(Integer.parseInt(categoryId));
		        food.setImage_url(image_url);
		        food.setUpdated_at(new Timestamp(System.currentTimeMillis()));

		        FoodDAO dao = new FoodDAO();
		        boolean isUpdated = dao.updateFood(food);
		        if (isUpdated) {
		            session.setAttribute("messageType", "success");
		            session.setAttribute("message", "Food updated successfully.");
		            response.sendRedirect(request.getContextPath() + "/pages/EditFood.jsp");
		            System.out.println("Food updated successfully");
		        } else {
		            session.setAttribute("messageType", "error");
		            session.setAttribute("message", "Failed to update food.");
		            response.sendRedirect(request.getContextPath() + "/pages/EditFood.jsp");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        session.setAttribute("messageType", "error");
		        session.setAttribute("message", "System error occurred. Please try again.");
		        response.sendRedirect(request.getContextPath() + "/EditFoodServlet");
		    }
		}
		        
		    	private void setErrorAndRedirect(HttpServletRequest request, HttpSession session, HttpServletResponse response, String errorMsg, String name, String description,  String price, String category_id, String serving_size, String image_url) throws IOException, ServletException {
		    		request.setAttribute("messageType", "error");
		    		request.setAttribute("message", errorMsg);

		    		request.setAttribute("name", name);
		    		request.setAttribute("description", description);
		    		request.setAttribute("price", price);
		    		request.setAttribute("category_id", category_id);
		    		request.setAttribute("serving_size", serving_size);
		    		request.setAttribute("image_url", image_url);

		    		try {
		    		    FoodDAO foodDAO = new FoodDAO();
		    		    List<Category> categoryList = foodDAO.getAllCategories();
		    		    request.setAttribute("categoryList", categoryList);
		    		} catch (Exception e) {
		    		    e.printStackTrace();
		    		    request.setAttribute("categoryList", null);
		    		}

		    		// Forward instead of redirect
		    		request.getRequestDispatcher("pages/EditFood.jsp").forward(request, response);

}
		        
		    
		    
	}


