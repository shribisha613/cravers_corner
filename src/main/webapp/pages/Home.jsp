<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cravers Corner Home Page</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css">


</head>
<body>

<jsp:include page="Header.jsp" />
    <div class="container">
        <section class="hero">
            <h1>Cravings Delivered, Happiness Guaranteed !</h1>
            <h2 class="slogan">Crave. Order. Enjoy. Repeat.</h2>
            <p class="order-btn">Order Now</p>
        </section>

      <h2 class="section-title">Discover Tasty Categories</h2>
      
      


<div class="category-container">
    <c:forEach var="category" items="${categoryList}" varStatus="status">
        
            <div class="category-card" onclick="location.href='${pageContext.request.contextPath}/menu?type=${category.name}'">
                <h3 class="category-title">${category.name}</h3>
           <div class="food-thumbnails ${allCategoryFoods[status.index].size() == 2 ? 'two-items' : ''}">
                    <c:forEach var="food" items="${allCategoryFoods[status.index]}" end="3" varStatus="foodStatus">
                        <div class="thumbnail-box ${allCategoryFoods[status.index].size() == 1 ? 'single' : ''}">
                            <img src="${pageContext.request.contextPath}/${food.image_url}" alt="Food Image">
                            <p class="food-name">${food.name}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
       
    </c:forEach>
</div>




    <!-- Offers Section -->
   <h2 class="section-title">Top Picks on the Menu</h2>
<div class="top-ordered-container" >
    <c:forEach var="food" items="${topOrderedFoods}">
        <div class="top-food-card" onclick="location.href='${pageContext.request.contextPath}/FoodDetailServlet?id=${food.food_id}'">
            <img src="${pageContext.request.contextPath}/${food.image_url}" alt="${food.name}" />
            <h3>${food.name}</h3>
            <p>Price: $${food.price}</p>
        </div>
    </c:forEach>
</div>

<jsp:include page="Footer.jsp" />
</body>
</html>