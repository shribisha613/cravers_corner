package com.cravers_corner.controller.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.model.Order;

import java.io.IOException;

@WebServlet("/ViewOrderDetailServlet")
public class ViewOrderDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
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
        
        System.out.println("checking");

        try {
            String orderIdParam = request.getParameter("orderId");
            System.out.println(orderIdParam);

            if (orderIdParam == null || orderIdParam.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/pages/Order.jsp");
                return;
            }

            int orderId = Integer.parseInt(orderIdParam);

            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.getOrderById(orderId);

            if (order != null) {
                request.setAttribute("order", order);
                System.out.println(order);//checking
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/OrderDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/GetOrderServlet");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/GetOrderServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/GetOrderServlet");
        }
    }
}