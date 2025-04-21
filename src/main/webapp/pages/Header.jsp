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
    <!-- Cart Icon -->
    <a href="<%=request.getContextPath()%>/pages/Cart.jsp" title="Cart" class="icon">
    <i class="fas fa-shopping-cart"></i>
</a>

    <a href="<%=request.getContextPath()%>/UserProfileServlet" class="icon">
 				    
 				        <img src="<%=request.getContextPath()%>/images/pfp.jpg" alt="Profile"> <!-- Profile Icon -->
 				   
 				</a>

    <!-- Logout Icon -->
    <form action="<%=request.getContextPath()%>/LogoutServlet" method="post" style="display: inline;">
        <button type="submit" class="icon logout-btn" title="Logout">
            <i class="fas fa-sign-out-alt"></i>
        </button>
    </form>
</div>
    
    
</header>

<div style="padding: 20px ">
    <c:if test="${not empty userWithSession}">
        <h2>Hi, <c:out value="${userWithSession.first_name}" /></h2>
    </c:if>
    
</div>
