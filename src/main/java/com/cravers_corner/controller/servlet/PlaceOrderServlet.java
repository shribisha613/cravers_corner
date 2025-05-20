package com.cravers_corner.controller.servlet;

import com.cravers_corner.model.Order;
import com.cravers_corner.model.OrderItem;
import com.cravers_corner.model.User;
import com.cravers_corner.controller.dao.CartItemDAO;
import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.controller.dao.OrderItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession(false);

	    // Check if session exists and user is logged in
	    if (session == null || session.getAttribute("userWithSession") == null) {
	        response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
	        return;
	    }

	    Order order = (Order) session.getAttribute("order");
	    List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");

	   

	    // Get the order note parameter and set necessary fields
	    String orderNote = request.getParameter("orderNote");
	    order.setOrderNote(orderNote); // Ensure Order class has this field
	    order.setStatus("Pending");
	    order.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

	    try {
	        OrderDAO orderDAO = new OrderDAO();
	        int orderId = orderDAO.createOrderWithItems(order);

	        if (orderId <= 0) {
	            // Invalid order ID means order creation failed
	           session.setAttribute("errorMessage", "Failed to place the order. Try again later.");
	            response.sendRedirect(request.getContextPath() + "/pages/CheckOut.jsp");
	            return;
	        }

	        // Insert order items linked to the order ID
	        OrderItemDAO itemDAO = new OrderItemDAO();
	        for (OrderItem item : orderItems) {
	            item.setOrderId(orderId);
	            itemDAO.insertOrderItem(item);
	        }
	        
	        User user = (User) session.getAttribute("userWithSession");
	        int userId = user.getUser_id(); // Ensure this method exists
	        CartItemDAO cartItemDAO = new CartItemDAO();
	        cartItemDAO.deleteCartItemsByUserId(userId);
	        
	        double totalPrice = order.getTotalAmount();
	        String successMsg = "Your order is confirmed! Total: ₹" + totalPrice;

	        session.setAttribute("successMessage", successMsg);

	        // Clear session attributes after successful order placement
	        session.removeAttribute("cart");
	        session.removeAttribute("cartItems");
	        session.removeAttribute("cartTotal");
	        session.removeAttribute("order");
	        session.removeAttribute("orderItems");

	        // Set success message for display on CheckOut.jsp
	     

	        response.sendRedirect(request.getContextPath() + "/ViewCartServlet");

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        session.setAttribute("errorMessage", "Failed to place the order. Try again later.");
	        response.sendRedirect(request.getContextPath() + "/pages/CheckOut.jsp");
	    }
	}

}
