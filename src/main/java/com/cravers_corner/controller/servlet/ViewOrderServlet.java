package com.cravers_corner.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.model.Order;

/**
 * Servlet implementation class ViewOrderServlet
 */
@WebServlet("/ViewOrderServlet")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hh");

        try {
            // Get the orderId from request
            String orderIdParam = request.getParameter("orderId");
            System.out.println(orderIdParam);
            if (orderIdParam == null || orderIdParam.isEmpty()) {
                response.sendRedirect("${pageContext.request.contextPath}" + "/pages/Orders.jsp");
                return;
            }

            int orderId = Integer.parseInt(orderIdParam);
           
            
            

            // Fetch order details from DAO
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.getOrderById(orderId);

            if (order != null) {
                // Pass the order to the JSP
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/OrderDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                // Order not found
                response.sendRedirect("/pages/Orders.jsp?error=OrderNotFound");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/pages/Orders.jsp?error=InvalidOrderId");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/pages/Orders.jsp");
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
