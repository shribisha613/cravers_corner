<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Include Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header.css">
<header>
    <div class="logo">
        <img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo">
    </div>

    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/pages/Home.jsp">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/Menu.jsp">Menu</a></li>
            <li><a href="<%=request.getContextPath()%>/AboutUs.jsp">About Us</a></li>
        </ul>
    </nav>

    <div class="icons">
 

		    <section class="icon">
		        <a href="Cart.jsp">
		            <button type="button" class="icon-btn cart-btn">
		                <i class="fas fa-shopping-cart"></i>
		            </button>
		        </a>
		    </section>


    <a href="<%=request.getContextPath()%>/UserProfileServlet" class="icon">
                     
                         <img src="<%=request.getContextPath()%>/images/pfp.jpg" alt="Profile"> <!-- Profile Icon -->
                    
                 </a>

   <section class="icon">

		        <form action="<%=request.getContextPath()%>/LogoutServlet" method="post">
		            <button type="submit" class="icon-btn logout-btn">
		                <i class="fas fa-sign-out-alt"></i>
		            </button>
		        </form>
		    </section>
</div>
    
    
</header>


