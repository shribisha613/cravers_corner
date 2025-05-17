package com.cravers_corner.controller.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.CartDAO;
import com.cravers_corner.controller.dao.CartItemDAO;
import com.cravers_corner.model.CartItem;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }

        try {
            int cart_item_id = Integer.parseInt(request.getParameter("cart_item_id"));

            CartItemDAO cartItemDAO = new CartItemDAO();
            CartDAO cartDAO = new CartDAO();

            // Get the cart_id before deleting the item
            CartItem item = cartItemDAO.getCartItemById(cart_item_id);
            if (item != null) {
                int cart_id = item.getCart_id();

                // Delete the item
                cartItemDAO.removeCartItem(cart_item_id);

                // Recalculate total
                List<CartItem> items = cartDAO.getCartItems(cart_id);
                double total = 0;
                for (CartItem ci : items) {
                    total += ci.getSubtotal();
                }

                cartDAO.updateCartTotal(cart_id, total);

                session.setAttribute("cartItems", items);
                session.setAttribute("cartTotal", total);
                session.setAttribute("cartItemCount", items.size());
                session.setAttribute("message", "Item removed from cart");
            }

            response.sendRedirect("AddToCart.jsp?openCart=true");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Failed to remove item: " + e.getMessage());
            response.sendRedirect("AddToCart.jsp?openCart=true");
        }
    }
}
