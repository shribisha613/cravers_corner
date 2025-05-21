package com.cravers_corner.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.FoodDAO;

/**
 * Servlet implementation class DeleteFoodServlet
 */
@WebServlet("/DeleteFoodServlet")
public class DeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }

        System.out.println("DeleteFoodServlet reached!");
        System.out.println("Food ID: " + request.getParameter("food_id"));

        try {
            int foodId = Integer.parseInt(request.getParameter("food_id"));
            System.out.println("DeleteFoodServlet called for foodId = " + foodId);

            FoodDAO dao = new FoodDAO();
            boolean deleted = dao.deleteFoodById(foodId);
            System.out.println("Food ID to delete: " + foodId);

            if (deleted) {
               
                session.setAttribute("success", "Food item discontinued successfully.");
            } else {
               
                session.setAttribute("errorMessage", "Failed to delete food item.");
            }

        } catch (Exception e) {
            e.printStackTrace();
    
            session.setAttribute("errorMessage", "System error occurred during deletion.");
        }

        response.sendRedirect(request.getContextPath() + "/GetFoodServlet");
    }

  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
