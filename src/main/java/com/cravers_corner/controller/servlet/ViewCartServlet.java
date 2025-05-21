package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.CartDAO;
import com.cravers_corner.controller.dao.CartItemDAO;
import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Cart;
import com.cravers_corner.model.CartItem;

import com.cravers_corner.model.User;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
 // ViewCartServlet.java - Corrected version
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);

        // Check if session exists and user is logged in
        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }
        
        String returnPage = request.getParameter("returnPage");
        if (returnPage == null || returnPage.isEmpty()) {
            returnPage = request.getContextPath() + "/pages/Home.jsp";
        }
       
        

        User user = (User) session.getAttribute("userWithSession");
        int customer_id = user.getUser_id();
        try {
            CartDAO cartDAO = new CartDAO();
            
            
            // Get customer's cart
            Cart cart = cartDAO.getCartByCustomerId(customer_id);
            
            if (cart != null) {
            	CartItemDAO cartItemDAO = new CartItemDAO();
                // Get cart items
                List<CartItem> cartItems = cartItemDAO.getCartItems(cart.getCart_id());
                
                // Store in session - using the actual CartItem objects
                session.setAttribute("cartItems", cartItems);
                session.setAttribute("cartTotal", cart.getTotal_amount());
                session.setAttribute("cartItemCount", cartItems.size());
                
                // Store as request attributes too
                request.setAttribute("cart", cart);
            } else {
                // Empty cart
                session.setAttribute("cartItems", new ArrayList<>());
                session.setAttribute("cartTotal", 0.0);
                session.setAttribute("cartItemCount", 0);
            }
            
            // Forward to cart page
           
            
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
            
            if (returnPage.contains("/pages/UserProfile.jsp")) {
                returnPage = returnPage.replace("/pages/UserProfile.jsp", "/UserProfileServlet");
                
                
            }
            
            if (returnPage.contains("/pages/AdminUserProfile.jsp")) {
                returnPage = returnPage.replace("/pages/AdminUserProfile.jsp", "/AdminUserProfileServlet");
                
                
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
            boolean isCartOpen = false;
            if (returnPage != null && returnPage.contains("openCart=true")) {
                isCartOpen = true;
            }

            // Set attribute to forward info to JSP
            request.setAttribute("isCartOpen", isCartOpen);
            response.sendRedirect(returnPage);
            
            
        
            
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Error loading cart: " + e.getMessage());
            response.sendRedirect(returnPage);
        }
    }
    
}