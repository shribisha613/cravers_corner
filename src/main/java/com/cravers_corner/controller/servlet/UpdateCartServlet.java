package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.CartDAO;
import com.cravers_corner.controller.dao.CartItemDAO;
import com.cravers_corner.model.CartItem;

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            System.out.println("user is not logged in ");
            
            return;
        }
        String returnPage = request.getParameter("returnPage");
        System.out.println("return page: " + returnPage);
        
        try {
            int cart_item_id = Integer.parseInt(request.getParameter("cart_item_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            
            
            
            
            if (quantity < 1) {
                quantity = 1;
            } else if (quantity > 10) {
                quantity = 10;
            }

            // Set or clear error message based on normalized quantity
            if (quantity >= 10) {
                session.setAttribute("errorMessage", "You can only order up to 10 units of each item.");
            } else {
                session.removeAttribute("errorMessage");
            }

            CartItemDAO cartItemDAO = new CartItemDAO();
            CartDAO cartDAO = new CartDAO();

            // Get the cart item
            CartItem item = cartItemDAO.getCartItemById(cart_item_id);

            if (item != null) {
                int cart_id = item.getCart_id();
                
                // Update quantity and subtotal
                item.setQuantity(quantity);
                item.setSubtotal(item.getPrice() * quantity);
                System.out.println("the cart ite id is:"+ cart_item_id );
                
                // Update in database
                boolean updated = cartItemDAO.updateCartItem(item);
                
                if (updated) {
                	
                    List<CartItem> cartItems = cartItemDAO.getCartItems(cart_id);
                    
                    // Get cart items
                    
                    // Recalculate cart total
                    double total = 0;
                    for (CartItem ci : cartItems) {
                        total += ci.getSubtotal();
                    }
                    
                    // Update cart total in database
                    cartDAO.updateCartTotal(cart_id, total);
                    
                    // Update session attributes
                    session.setAttribute("cartItems", cartItems);
                    session.setAttribute("cartTotal", total);
                    session.setAttribute("cartItemCount", cartItems.size());
                    session.setAttribute("success", "Cart updated successfully");
                } else {
                    session.setAttribute("errorMessage", "Failed to update cart item");
                }
            } else {
                session.setAttribute("errorMessage", "Cart item not found");
            }
            
            // Redirect back to cart page
            
            
            if (returnPage.contains("/pages/FoodDetail.jsp")) {
                returnPage = returnPage.replace("/pages/FoodDetail.jsp", "/FoodDetailServlet");
            }

            
            if (returnPage.contains("/pages/Menu.jsp")) {
                returnPage = returnPage.replace("/pages/Menu.jsp", "/menu");
            }
            
            if (returnPage.contains("/pages/Home.jsp")) {
                returnPage = returnPage.replace("/pages/Home.jsp", "/HomeServlet");
            }
            
            if (returnPage.contains("/pages/MyOrders.jsp")) {
                returnPage = returnPage.replace("/pages/MyOrders.jsp", "/UserOrderServlet");
            }
            // Add openCart=true only if not already present
            if (!returnPage.contains("openCart=true")) {
                if (returnPage.contains("?")) {
                    returnPage += "&openCart=true";
                } else {
                    returnPage += "?openCart=true";
                }
            }
            System.out.println(returnPage);
            response.sendRedirect(returnPage);

            
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error updating cart ");
            response.sendRedirect(returnPage);

        }
    }
}
