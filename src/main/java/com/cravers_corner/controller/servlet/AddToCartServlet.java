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
import com.cravers_corner.controller.dao.FoodDAO;
import com.cravers_corner.model.Cart;
import com.cravers_corner.model.CartItem;
import com.cravers_corner.model.Food;
import com.cravers_corner.model.User;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public AddToCartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/AddToCart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if session exists and user is logged in
        if (session == null || session.getAttribute("userWithSession") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Login.jsp");
            return;
        }

        try {
            // Retrieve logged-in user from session
            User user = (User) session.getAttribute("userWithSession");
            int customer_id = user.getUser_id();

            int food_id = Integer.parseInt(request.getParameter("food_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            System.out.println("Adding to cart: food_id=" + food_id + ", quantity=" + quantity + ", user_id=" + customer_id);

            // Get food details from DB
            FoodDAO foodDAO = new FoodDAO();
            Food food = foodDAO.getFoodById(food_id);

            if (food == null) {
                throw new Exception("Food item not found");
            }

            double price = food.getPrice();
            System.out.println("Food price: " + price);

            // Get or create cart for user
            CartDAO cartDAO = new CartDAO();
            Cart cart = cartDAO.getCartByCustomerId(customer_id);

            if (cart == null) {
                cart = new Cart();
                cart.setCustomer_id(customer_id);
                cart.setTotal_amount(0);
                int cart_id = cartDAO.createCart(cart);
                cart.setCart_id(cart_id);
                System.out.println("Created new cart with ID: " + cart_id);
            } else {
                System.out.println("Using existing cart with ID: " + cart.getCart_id());
            }

            // Create cart item and add/update
            CartItem item = new CartItem();
            item.setCart_id(cart.getCart_id());
            item.setFood_id(food_id);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setSubtotal(quantity * price);
            item.setFood_name(food.getName());
            item.setImage_url(food.getImage_url());

            CartItemDAO cartItemDAO = new CartItemDAO();
            cartItemDAO.addOrIncrementCartItem(item);
            System.out.println("Added/updated item in cart");

            // Update cart total
            List<CartItem> items = cartItemDAO.getCartItems(cart.getCart_id());
            for (CartItem i : items) {
                System.out.println("Food name: " + i.getFood_name() + " | Image URL: " + i.getImage_url());
            }

            double total = 0;
            for (CartItem ci : items) {
                total += ci.getSubtotal();
                
            }
            cartDAO.updateCartTotal(cart.getCart_id(), total);
            System.out.println("Updated cart total: " + total);

            // Store cart info in session for display
            session.setAttribute("cartItems", items);
            session.setAttribute("cartTotal", total);
            session.setAttribute("cartItemCount", items.size());

            // Redirect back to food detail with cart popup open
            response.sendRedirect(request.getContextPath() + "/FoodDetailServlet?id=" + food_id + "&openCart=true");

        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to food detail page with error parameter
            try {
                int food_id = Integer.parseInt(request.getParameter("food_id"));
                response.sendRedirect(request.getContextPath() + "/FoodDetailServlet?id=" + food_id + "&error=true");
            } catch (Exception ex) {
                response.sendRedirect(request.getContextPath() + "/MenuServlet");
            }
        }
    }

}