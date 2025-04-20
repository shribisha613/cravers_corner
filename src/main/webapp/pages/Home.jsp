<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cravers Corner Home Page</title>
<style>
        * {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            box-sizing: border-box;
        }

        body {
            background-color: #f5f5f5;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            
            flex: 1;
            width: 100%;
        }

        header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 80px;
            z-index: 1000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: linear-gradient(to right, #EF9651, #EC5228);
            padding: 10px 30px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .logo img {
            height: 60px;
            width: auto;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 30px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .icons img {
            height: 30px;
            margin-left: 15px;
            cursor: pointer;
        }

        .icons img:hover {
            transform: scale(1.1);
        }

        /* Hero Section */
        .hero {
            background-color: #444;
            background:url('../images/home bg.jpg');
            background-size: cover;
            background-position: center;
            height: 500px;
            width: 100%;
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: flex-end;
            padding-right: 50px;
            margin-bottom: 40px;
        }

        .hero h1 {
            font-size: 2.8rem;
            margin-bottom: 20px;
            font-style: italic;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
        }

        .order-btn {
            display: inline-block;
            background-color: #ff5722;
            color: white;
            padding: 12px 30px;
            border-radius: 5px;
            font-weight: bold;
            font-size: 1.2rem;
            margin-top: 20px;
            transition: background-color 0.3s;
            border: none;
            cursor: pointer;
            text-decoration: none;
        }

        .order-btn:hover {
            background-color: #e64a19;
        }

        /* Categories Section */
        .section-title {
            text-align: center;
            margin: 40px 0 30px;
            font-size: 2rem;
            color: #222;
        }

        .categories, .offers {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 30px;
            margin-bottom: 50px;
        }

        .category, .offer {
            background-color: white;
            padding: 25px 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            font-weight: bold;
            font-size: 1.2rem;
            transition: transform 0.3s;
            cursor: pointer;
            text-align: center;
            min-width: 200px;
        }

        .category:hover, .offer:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
        }

        .footer {
            width: 100%;
            background: linear-gradient(to right, #EF9651, #EC5228);
            color: white;
            padding: 30px 20px;
            margin-top: 40px;
        }

        .footer-content {
            display: flex;
            justify-content: space-around;
            gap: 100px;
            max-width: 1200px;
            margin: 0 auto 20px;
        }

        .footer-logo img {
            height: 100px;
            width: auto;
        }

        .footer-column {
            text-align: center;
        }

        .footer-column h4 {
            margin-bottom: 15px;
            font-size: 18px;
        }

        .footer-column ul {
            list-style: none;
            padding: 0;
        }

        .footer-column ul li {
            margin-bottom: 8px;
        }

        .footer-column ul li a {
            color: white;
            text-decoration: none;
            transition: 0.3s;
        }

        .footer-column ul li a:hover {
            text-decoration: underline;
        }

        .footer-bottom {
            text-align: center;
            padding-top: 20px;
            border-top: 1px solid rgba(255, 255, 255, 0.2);
            font-size: 14px;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            header {
                padding: 10px 15px;
            }
            
            nav ul {
                gap: 15px;
            }
            
            .hero {
                padding-right: 20px;
                height: 400px;
                align-items: center;
                text-align: center;
            }
            
            .hero h1 {
                font-size: 2rem;
            }
            
            .footer-content {
                gap: 30px;
                flex-direction: column;
                align-items: center;
            }
            
            .footer-column {
                margin-bottom: 20px;
            }
        }
</style>
</head>
<body>

<!--<jsp:include page="Header.jsp" />

		<div style="padding: 20px;">
    <c:if test="${not empty userWithSession}">
        <h2>Hi, <c:out value="${userWithSession.first_name}" /></h2>
    </c:if>
    
</div>
-->
    <header>
        <div class="logo">
            <img src="../images/logo.png" alt="logo">
        </div>
        <nav>
            <ul>
                <li><a href="Home.jsp">Home</a></li>
                <li><a href="Menu.jsp">Menu</a></li>
                <li><a href="AboutUs.jsp">About Us</a></li>
            </ul>
        </nav>
        <div class="icons">
            <a href="Cart.jsp"><img src="../images/cart.png" alt="cart"></a>
            <a href="#"><img src="../images/profile.png" alt="profile"></a>
            <a href="LogOut.jsp"><img src="../images/logout.png" alt="logout"></a>
        </div>
    </header>
    
    <div class="container">
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