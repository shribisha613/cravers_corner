package controller.servlets;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cravers_corner.controller.dao.OrderDAO;
import com.cravers_corner.model.Order;

import java.io.IOException;

@WebServlet("/viewOrderDetail")
public class ViewOrderDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("hh");

        try {
            // Get the orderId from request
            String orderIdParam = request.getParameter("orderId");
            System.out.println(orderIdParam);
            if (orderIdParam == null || orderIdParam.isEmpty()) {
                response.sendRedirect("${pageContext.request.contextPath}" + "/pages/Orders.jsp");
                return;
            }

            int orderId = Integer.parseInt(orderIdParam);

            // Fetch order details from DAO
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.getOrderById(orderId);

            if (order != null) {
                // Pass the order to the JSP
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/OrderDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                // Order not found
                response.sendRedirect("/pages/Orders.jsp?error=OrderNotFound");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/pages/Orders.jsp?error=InvalidOrderId");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/pages/Orders.jsp");
        }
    }
}
