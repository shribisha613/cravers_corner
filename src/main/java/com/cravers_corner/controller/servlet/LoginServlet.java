package com.cravers_corner.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cravers_corner.controller.dao.UserDAO;
import com.cravers_corner.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String identifier = request.getParameter("identifier");
		String entered_password = request.getParameter("password");

		try {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.login(identifier, entered_password);

			if (user != null) { 
				out.println("The user is found");
				// Create a session for the logged-in user
				HttpSession session = request.getSession();
				session.setAttribute("userWithSession", user);
				session.setMaxInactiveInterval(30 * 60); // 30 minutes

				// Redirect to home/dashboard page
				response.sendRedirect(request.getContextPath() + "/pages/Home.jsp");
			} else {
				// Invalid credentials
				request.setAttribute("loginError", "Invalid email, username or password. Please try again.");
				request.getRequestDispatcher("/pages/Login.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); // Replace with logging in production
			request.setAttribute("loginError", "A system error occurred. Please try again later.");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		} finally {
			out.close();
		}
	}

}
	
	
