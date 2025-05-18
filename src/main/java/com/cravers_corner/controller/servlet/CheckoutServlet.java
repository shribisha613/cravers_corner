package com.cravers_corner.controller.servlet;

import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userWithSession");
        
        if (user == null) {
            response.sendRedirect("/pages/Login.jsp");
            return;
        }

        List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cartItems");
        Order order = new Order();
        order.setCustomerId(user.getUser_id());
        order.setStatus("pending");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        
        double subtotal = cartItems.stream()
                                 .mapToDouble(OrderItem::getSubtotal)
                                 .sum();
        order.setTotalAmount(subtotal + 50.0);

        request.setAttribute("user", user);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/CheckOut.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userWithSession");

        if (user == null) {
            response.sendRedirect("pages/Login.jsp");
            return;
        }

        try {
            Order order = new Order();
            order.setCustomerId(user.getUser_id());
            order.setStatus("pending");
            order.setOrderNote(request.getParameter("orderNote"));
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            
            List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cart");
            order.setItems(cartItems);

            double subtotal = cartItems.stream()
                                     .mapToDouble(OrderItem::getSubtotal)
                                     .sum();
            order.setTotalAmount(subtotal + 50.0);

            OrderDAO orderDAO = new OrderDAO();
            int orderId = orderDAO.createOrderWithItems(order);
            
            if (orderId > 0) {
                session.removeAttribute("cart");
                response.sendRedirect("order-confirmation.jsp?orderId=" + orderId);
            } else {
                request.setAttribute("errorMessage", "Order failed. Please try again.");
                request.getRequestDispatcher("/CheckOut.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}