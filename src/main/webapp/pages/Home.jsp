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
            <h1>"Crave It, Wave It,<br>We'll Slay It"</h1>
            <a href="#" class="order-btn">Order Now</a>
        </section>

      <h2 class="section-title">Order By Category</h2>
      
      


<div class="category-container">
    <c:forEach var="category" items="${categoryList}" varStatus="status">
        
            <div class="category-card" onclick="location.href='${pageContext.request.contextPath}/menu?type=${category.name}'">
                <h3 class="category-title">${category.name}</h3>
                <div class="food-thumbnails">
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
    <h2 class="section-title">Best Combo Offers</h2>
    <div class="offers">
        <div class="offer" style="background-image: url('../images/jodi combo.jpg')">
            <span>Jodi Combo</span>
        </div>
        <div class="offer" style="background-image: url('../images/fam combo.jpg')">
            <span>Family Combo</span>
        </div>
        <div class="offer" style="background-image: url('../images/solo combo.jpg')">
            <span>Solo Yolo Combo</span>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp" />
</body>
</html>