package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Food;

/**
 * Servlet implementation class FoodDetailServlet
 */
@WebServlet("/FoodDetailServlet")
public class FoodDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("userWithSession") == null) {
	            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
	            return;
	        }
	        
	        String role = (String) session.getAttribute("role");
	        if ("admin".equals(role)) {
	            response.sendRedirect(request.getContextPath() + "/pages/AccessDenied.jsp");
	            return;
	        }
		
		String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int food_id = Integer.parseInt(idParam);
                FoodDAO foodDAO = new FoodDAO();
                Food food = foodDAO.getFoodById(food_id);

                if (food != null) {
                    request.setAttribute("food", food);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/FoodDetail.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } catch (NumberFormatException ignored) {} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        response.sendRedirect(request.getContextPath() + "/pages/Menu.jsp"); // fallback if invalid ID
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
