package com.cravers_corner.controller.servlet;

import com.cravers_corner.model.Order;
import com.cravers_corner.model.OrderItem;
import com.cravers_corner.model.User;
import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.controller.dao.OrderItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userWithSession");
        String returnPage = request.getParameter("returnPage");
        if (user == null) {
            response.sendRedirect("/pages/Login.jsp");
            return;
        }

        // Retrieve the Order object and items from session
        Order order = (Order) session.getAttribute("order");
        @SuppressWarnings("unchecked")
		List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        System.out.println(orderItems);
        if (order == null || orderItems == null || orderItems.isEmpty()) {
            session.setAttribute("errorMessage", "Something went wrong. Please try again.");
            response.sendRedirect(request.getContextPath() + "/pages/CheckOut.jsp");
            return;
            
        }

        try {
            // Save the order
            OrderDAO orderDAO = new OrderDAO();
            int orderId = orderDAO.createOrderWithItems(order);

            // Save each order item
            OrderItemDAO itemDAO = new OrderItemDAO(); // Reuse the same connection
            for (OrderItem item : orderItems) {
                item.setOrderId(orderId);
                itemDAO.insertOrderItem(item);
            }

            // Clear session data after successful placement
            session.removeAttribute("order");
            session.removeAttribute("orderItems");

            // Set success message for alert on CheckOut.jsp
            session.setAttribute("successMessage", "Order placed successfully!");

            response.sendRedirect(request.getContextPath() + "/pages/CheckOut.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Failed to place the order. Try again later.");
            response.sendRedirect(request.getContextPath() + "/pages/CheckOut.jsp");
        }
    }
}
