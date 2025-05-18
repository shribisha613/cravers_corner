package com.cravers_corner.controller.servlet;

import com.cravers_corner.controller.dao.CategoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteCategoryServlet() {
        super();
    }

    // Handles category deletion via GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }

        System.out.println("DeleteCategoryServlet reached!");
        System.out.println("Category ID  " + request.getParameter("id"));

        
        try {
            int categoryId = Integer.parseInt(request.getParameter("id"));
            System.out.println("DeleteCategoryServlet called for categoryId = " + categoryId);

            CategoryDAO dao = new CategoryDAO();
            boolean deleted = dao.deleteCategory(categoryId);
            System.out.println("Category ID to delete: " +categoryId);

            if (deleted) {
                session.setAttribute("messageType", "success");
                session.setAttribute("message", "Category deleted successfully.");
            } else {
                session.setAttribute("messageType", "error");
                session.setAttribute("message", "Failed to delete category.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("messageType", "error");
            session.setAttribute("message", "System error occurred during deleting.");
        }

        response.sendRedirect(request.getContextPath() + "/GetCategoryServlet");
    }

    // Optional: Prevent deletion via POST if not needed
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // redirect POST to GET
    }
}
