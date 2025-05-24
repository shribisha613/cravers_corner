package com.cravers_corner.controller.servlet;

import com.cravers_corner.controller.dao.CategoryDAO;
import com.cravers_corner.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/EditCategoryServlet")
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCategoryServlet() {
        super();
    }

    // Load existing category data to edit
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
        try {
        	System.out.println("EditCategoryServlet called");
            int categoryId = Integer.parseInt(request.getParameter("id"));
            System.out.println("categoryId = " + categoryId);
            
            CategoryDAO dao = new CategoryDAO();
            Category category = dao.getCategoryById(categoryId);
            System.out.println("category = " + category);     

            //Null check
            if (category == null) {
                
                session.setAttribute("messageType", "error");
                session.setAttribute("message", "Category not found.");
                response.sendRedirect(request.getContextPath() + "/GetCategoryServlet"); // or categories.jsp
                return;
            }
            
            request.setAttribute("category", category);
            System.out.println("Forwarding to edit_category.jsp");
            
            request.getRequestDispatcher("pages/EditCategory.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    // Handle form submission to update category
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            if (name == null || name.trim().isEmpty()) {
                session.setAttribute("messageType", "error");
                session.setAttribute("message", "Category name is required.");
                response.sendRedirect(request.getContextPath() + "/EditCategoryServlet?id=" + categoryId);
                return;
            }

            Category category = new Category();
            category.setCategory_id(categoryId);
            category.setName(name);
            category.setDescription(description);
            category.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            CategoryDAO dao = new CategoryDAO();
            boolean updated = dao.updateCategory(category);

            if (updated) {
                session.setAttribute("messageType", "success");
                session.setAttribute("message", "Category updated successfully.");
            } else {
                session.setAttribute("messageType", "error");
                session.setAttribute("message", "Failed to update category.");
            }

            response.sendRedirect("pages/EditCategory.jsp");
            

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("messageType", "error");
            session.setAttribute("message", "System error occurred.");
            
            response.sendRedirect(request.getContextPath() + "/GetCategoryServlet");
        }
    }
}
