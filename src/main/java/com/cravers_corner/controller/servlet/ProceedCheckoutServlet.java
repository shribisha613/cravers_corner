package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.CartDAO;
import com.cravers_corner.controller.dao.CartItemDAO;
import com.cravers_corner.model.*;

@WebServlet("/ProceedCheckoutServlet")
public class ProceedCheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProceedCheckoutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userWithSession");
        String returnPage = request.getParameter("returnPage");
        if (user == null) {
            response.sendRedirect("/pages/Login.jsp");
            return;
        }

        try {
            // Step 1: Get the user's cart
            CartDAO cartDAO = new CartDAO();
            Cart userCart = cartDAO.getCartByCustomerId(user.getUser_id());

            if (userCart == null) {
                request.setAttribute("errorMessage", "Your cart is empty or unavailable.");
                request.getRequestDispatcher(returnPage).forward(request, response);
                return;
            }

            // Step 2: Get cart items
            CartItemDAO cartItemDAO = new CartItemDAO();
            List<CartItem> cartItems = cartItemDAO.getCartItems(userCart.getCart_id());
           
            if (cartItems == null || cartItems.isEmpty()) {
                request.setAttribute("errorMessage", "Your cart has no items.");
                request.getRequestDispatcher(returnPage).forward(request, response);
                return;
            }

            // Step 3: Calculate total
            double cartTotal = 0;
            for (CartItem item : cartItems) {
                cartTotal += item.getSubtotal();
            }

            // Step 4: Create Order and OrderItems
            Order order = new Order();
            order.setCustomerId(user.getUser_id());
            order.setTotalAmount(cartTotal+ 100);
            order.setStatus("Pending");
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Convert CartItem to OrderItem if needed
            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem ci : cartItems) {
                OrderItem oi = new OrderItem();
                oi.setImage_url(ci.getImage_url());
                oi.setQuantity(ci.getQuantity());
                oi.setPrice(ci.getPrice());
                oi.setSubtotal(ci.getSubtotal());
                oi.setFood_name(ci.getFood_name());
                oi.setFoodId(ci.getFood_id());
                orderItems.add(oi);
            }
            order.setItems(orderItems);

            // Step 5: Set attributes for JSP
            session.setAttribute("user", user);
            session.setAttribute("cart", userCart);
            session.setAttribute("cartItems", cartItems);
            session.setAttribute("cartTotal", cartTotal);
            session.setAttribute("order", order);
            session.setAttribute("orderItems", orderItems);

            request.getRequestDispatcher("/pages/CheckOut.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Something went wrong while preparing your checkout.");
            request.getRequestDispatcher("/menu").forward(request, response);
        }
    }


}
