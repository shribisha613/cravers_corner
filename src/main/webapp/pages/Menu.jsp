<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cravers_corner.model.Category" %>
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
<a href="${pageContext.request.contextPath}/GetCategoryServlet">Menu</a>
<div class="menu-container">
    <!-- Sidebar -->
	<div class="sidebar">
	  <h3>Categories</h3>
	  <ul>
	    <c:choose>
	      <c:when test="${not empty categories}">
	        <c:forEach items="${categories}" var="category">
	          <li>
	            <a href="FoodListServlet?categoryId=${category.category_id}">
	              ${category.name}
	            </a>
	          </li>
	        </c:forEach>
	      </c:when>
	      <c:otherwise>
	        <li>No categories available.</li>
	      </c:otherwise>
	    </c:choose>
	  </ul>
	</div>
    
    <!-- Main Menu Content -->
    <div class="menu-content">
        <form method="get" action="Menu.jsp" class="search-form">
            <input type="text" name="search" placeholder="Search..." />
        </form>
        
        <h2><%= type %> Food</h2>
        
        <div class="food-grid">
            <c:choose>
		        <c:when test="${param.type == 'Nepali'}">
		            <!-- Nepali foods -->
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/SukutiSadeko.jpg" alt="Sukuti Sadeko">
		                <div class="food-info">
		                    <p class="food-name">Sukuti Sadeko</p>
		                    <p class="food-price">Rs. 120</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/dalbhat.jpg" alt="Dal Bhat">
		                <div class="food-info">
		                    <p class="food-name">Dal Bhat</p>
		                    <p class="food-price">Rs. 160</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/cutemomo.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Jhol Laphing</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/JholLaphing.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Jhol Laphing</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/chowmein.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Jhol Laphing</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/spicymomo.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Jhol Laphing</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/chatpat.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Jhol Laphing</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/ShaPhaley.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Jhol Laphing</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		             <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/BuffSalad.png" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Buff Salad</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		        </c:when>
		
		        <c:when test="${param.type == 'Korean'}">
		            <!-- Korean foods -->
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/samgak.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Samgak Kimbap</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/bao.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Fried Chicken Bao</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		           	<div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/tteokbokki.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Spicy tteokbokki</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Bibim.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Kimchi Bibim Guksu</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Bibimbap.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Bibimbap</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/sesameChicken.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Honey Sesame Chicken</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Rabokki.png" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Vegan Rabokki</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/gimbap.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Mixed Gimbap</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Kkwabaegi.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Korean Twisted Doughnut  </p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/blackbean.jpg" alt="Jhol Laphing">
		                <div class="food-info">
		                    <p class="food-name">Black bean noodle</p>
		                    <p class="food-price">Rs. 170</p>
		                </div>
		            </div>
		        </c:when>
		
		        <c:when test="${param.type == 'Western'}">
		            <!-- Western foods -->
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Nuggets.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Chicken Nuggets</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/spaghetti.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Spaghetti Carbonara</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/ChickenBurger.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Cheesy Chicken Burger </p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Ravioli.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name"> Lobster Ravioli</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Burritos.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Buff Burritos</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/BaconBurger.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Cheesy Bacon Burger</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/ScallopedPotatoes.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Garlic Cheesy Pork Chops</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Pepperoni.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Pepperoni Pizza</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/SALMONSANDWICH.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Salmon Sandwich</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Tacos.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Chicken Tacos</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Pasta.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">Creamy Chicken Marsala Pasta</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/ChickenPizza.jpg" alt="Burger">
		                <div class="food-info">
		                    <p class="food-name">BBQ Chicken Pizza</p>
		                    <p class="food-price">Rs. 200</p>
		                </div>
		            </div>
		            
		        </c:when>
		
		        <c:when test="${param.type == 'Combo'}">
		            <!-- Combo meals -->
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/RamenCombo.png" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Ramen Combo</p>
		                    <p class="food-price">Rs. 300</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/momoPlatter.jpg" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Momo Platter </p>
		                    <p class="food-price">Rs. 300</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/download.jpg" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Couple Combo </p>
		                    <p class="food-price">Rs. 700</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/Favplatter.jpg" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Group Combo</p>
		                    <p class="food-price">Rs. 1999</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/solo combo.jpg" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Single Combo</p>
		                    <p class="food-price">Rs. 300</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/solo combo.jpg" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Single Combo</p>
		                    <p class="food-price">Rs. 300</p>
		                </div>
		            </div>
		            <div class="food-item">
		                <img src="${pageContext.request.contextPath}/images/solo combo.jpg" alt="Combo Meal">
		                <div class="food-info">
		                    <p class="food-name">Single Combo</p>
		                    <p class="food-price">Rs. 300</p>
		                </div>
		            </div>
		        </c:when>
		
		        <c:otherwise>
		            <p>No food items available for this category.</p>
		        </c:otherwise>
   			</c:choose>
       	</div>
	</div>
</div>
<jsp:include page="Footer.jsp" />
</body>
</html>