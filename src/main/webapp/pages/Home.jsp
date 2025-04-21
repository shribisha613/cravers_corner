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
    
   

	
        <!-- Hero Section -->
        <section class="hero">
            <h1>"Crave It, Wave It, We'll Slay It"</h1>
            <a href="#" class="order-btn">Order Now</a>
        </section>
        
        <!-- Categories Section -->
        <h2 class="section-title">Order By Category</h2>
        <div class="categories">
            <div class="category" style="background:url('../images/pizza.jpg');">Western</div>
            <div class="category" style="background:url('../images/bhat.jpg');">Nepali</div>
            <div class="category" style="background:url('../images/teok.jpg');">Korean</div>
        </div>
        
        <!-- Offers Section -->
        <h2 class="section-title">Best Combo Offers</h2>
        <div class="offers">
            <div class="offer" style="background:url('../images/jodi combo.jpg');">Jodi Combo</div>
            <div class="offer" style="background:url('../images/fam combo.jpg');">Family Combo</div>
            <div class="offer" style="background:url('../images/solo combo.jpg');">Solo Yolo Combo</div>
        </div>
    </div>
    
    <footer class="footer">
        <div class="footer-content">
            <div class="footer-logo">
                <img src="../images/logo.png" alt="logo">
            </div>
            <div class="footer-column">
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="Home.jsp">Home</a></li>
                    <li><a href="Menu.jsp">Menu</a></li>
                    <li><a href="AboutUs.jsp">About Us</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h4>Socials</h4>
                <ul>
                    <li><a href="#">Instagram</a></li>
                    <li><a href="#">Facebook</a></li>
                    <li><a href="#">Twitter</a></li>
                    <li><a href="#">YouTube</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 C2. All rights reserved.</p>
        </div>
    </footer>

 
</body>
</html>