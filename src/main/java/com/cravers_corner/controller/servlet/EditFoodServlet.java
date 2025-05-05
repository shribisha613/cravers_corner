package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Category;
import com.cravers_corner.model.Food;

/**
 * Servlet implementation class EditFoodServlet
 */
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
		doGet(request, response);
	}

}
