<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String type = request.getParameter("type");
    if (type == null || type.isEmpty()) {
        type = "Nepali";
    }
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cravers Corner Menu Page</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.css">
</head>

<body>
<jsp:include page="Header.jsp" />

<div class="menu-container">
    <!-- Sidebar -->
    <div class="sidebar">
        <h3>Category</h3>
        <ul>
            <li><a href="menu.jsp?type=Nepali">Nepali</a></li>
            <li><a href="menu.jsp?type=Korean">Korean</a></li>
            <li><a href="menu.jsp?type=Western">Western</a></li>
            <li><a href="menu.jsp?type=Combo">Combo</a></li>
        </ul>
    </div>
    
    <!-- Main Menu Content -->
    <div class="menu-content">
        <form method="get" action="menu.jsp" class="search-form">
            <input type="text" name="search" placeholder="Search..." />
        </form>
        
        <h2><%= type %> Food</h2>
        
        <div class="food-grid">
            <%-- Dummy Food Items (replace with DB items later) --%>
            <div class="food-item">
                 <img src="${pageContext.request.contextPath}/images/SukutiSadeko.jpg" alt="Food Image">
                <div class="food-info">
                    <p class="food-name"><%= type %> Dish 1</p>
                    <p class="food-price">Rs. 120</p>
                </div>
            </div>
            <div class="food-item">
                 <img src="${pageContext.request.contextPath}/images/cutemomo.jpg" alt="Food Image">
                <div class="food-info">
                    <p class="food-name"><%= type %> Dish 2</p>
                    <p class="food-price">Rs. 130</p>
                </div>
            </div>
            <div class="food-item">
                <img src="${pageContext.request.contextPath}/images/spicymomo.jpg" alt="Food Image">            
                <div class="food-info">
                    <p class="food-name"><%= type %> Dish 3</p>
                    <p class="food-price">Rs. 140</p>
                </div>
            </div>
            <div class="food-item">
                 <img src="${pageContext.request.contextPath}/images/chowmein.jpg" alt="Food Image">
                <div class="food-info">
                    <p class="food-name"><%= type %> Dish 4</p>
                    <p class="food-price">Rs. 150</p>
                </div>
            </div>
            <div class="food-item">
                 <img src="${pageContext.request.contextPath}/images/dalbhat.jpg" alt="Food Image">
                <div class="food-info">
                    <p class="food-name"><%= type %> Dish 5</p>
                    <p class="food-price">Rs. 160</p>
                </div>
            </div>
            <div class="food-item">
                <img src="${pageContext.request.contextPath}/images/JholLaphing.jpg" alt="Food Image">
                <div class="food-info">
                    <p class="food-name"><%= type %> Dish 6</p>
                    <p class="food-price">Rs. 170</p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp" />
</body>
</html>