package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.CategoryDAO;

import com.cravers_corner.controller.util.ValidationUtil;
import com.cravers_corner.model.Category;

/**
 * Servlet implementation class AddCategoryServlet
 */
@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
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
		
		

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        HttpSession session = request.getSession();

        // Validate inputs
        if (ValidationUtil.isNullOrEmpty(name)) {
            setErrorAndRedirect(session, response, "Category name is required.", name, description);
            return;
        }

        if (ValidationUtil.isNullOrEmpty(description)) {
            setErrorAndRedirect(session, response, "Category description is required.", name, description);
            return;
        }

        if (name.length() < 3 || description.length() < 3) {
            setErrorAndRedirect(session, response, "Category name and description must be at least 3 characters.", name, description);
            return;
        }

        try {
            // Create category
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            category.setCreated_at(new Timestamp(System.currentTimeMillis()));
            category.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            CategoryDAO categoryDAO = new CategoryDAO();
            boolean isInserted = categoryDAO.addCategory(category);

            if (isInserted) {
                session.setAttribute("messageType", "success");
                session.setAttribute("message", "Category added successfully!");
                response.sendRedirect(request.getContextPath() + "/pages/AddCategory.jsp");
            } else {
                setErrorAndRedirect(session, response, "Failed to add category. Please try again.", name, description);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            setErrorAndRedirect(session, response, "A system error occurred. Please try again later.", name, description);
        }
    }

    private void setErrorAndRedirect(HttpSession session, HttpServletResponse response, String errorMsg, String name, String description)
            throws IOException {
        session.setAttribute("messageType", "error");
        session.setAttribute("message", errorMsg);
        session.setAttribute("formName", name);
        session.setAttribute("formDescription", description);
        response.sendRedirect("pages/AddCategory.jsp");
    }
}
	