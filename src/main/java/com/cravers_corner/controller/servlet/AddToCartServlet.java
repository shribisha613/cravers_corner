package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cravers_corner.controller.dao.CartDAO;
import com.cravers_corner.controller.dao.CartItemDAO;
import com.cravers_corner.model.Cart;
import com.cravers_corner.model.CartItem;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/pages/AddToCart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int customer_id = (int) request.getSession().getAttribute("customer_id"); // assuming login session
	    int food_id = Integer.parseInt(request.getParameter("food_id"));
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    double price = Double.parseDouble(request.getParameter("price")); // get price from request or lookup

	    try {
	        CartDAO cartDAO = new CartDAO();
	        Cart cart = cartDAO.getCartByCustomerId(customer_id);

	        if (cart == null) {
	            cart = new Cart();
	            cart.setCustomer_id(customer_id);
	            cart.setTotal_amount(0);
	            int cart_id = cartDAO.createCart(cart);
	            cart.setCart_id(cart_id);
	        }

	        CartItem item = new CartItem();
	        item.setCart_id(cart.getCart_id());
	        item.setFood_id(food_id);
	        item.setQuantity(quantity);
	        item.setPrice(price);
	        item.setSubtotal(quantity * price);

	        CartItemDAO cartItemDAO = new CartItemDAO();
	        cartItemDAO.addOrUpdateCartItem(item);

	        // Update total amount in cart
	        List<CartItem> items = cartDAO.getCartItems(cart.getCart_id());
	        double total = 0;
	        for (CartItem ci : items) {
	            total += ci.getSubtotal();
	        }
	        cartDAO.updateCartTotal(cart.getCart_id(), total);

	        response.sendRedirect("cart.jsp"); // or wherever you want to send user next

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(500, "Unable to add to cart");
	    }
	}

}
