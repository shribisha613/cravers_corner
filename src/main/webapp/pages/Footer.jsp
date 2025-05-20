<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cravers Corner Footer</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
    <footer class="footer">
        <div class="footer-content">
            <div class="footer-logo">
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo">
            </div>
            <div class="footer-column">
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/HomeServlet">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/menu">Menu</a></li>
            <li><a href="<%=request.getContextPath()%>/pages/AboutUs.jsp">About Us</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h4>Socials</h4>
                <ul>
                    <li><a href="https://www.instagram.com">Instagram</a></li>
                    <li><a href="https://www.facebook.com">Facebook</a></li>
                    <li><a href="https://www.twitter.com">Twitter</a></li>
                    <li><a href="https://www.youtube.com">YouTube</a></li>
                </ul>
            </div>
            
            <div class="footer-column">
                <h4>Contact Us</h4>
                <ul >
                    <li>Email: <a href="#">support@cravers_corner.com</a></li>
                    <li>Phone: +977 9846050022</li>
                    <li>Lakeside, Pokhara</li>
                    
                </ul>
            </div>
        </div>
        
        
        <div class="footer-bottom">
            <p>&copy; 2025 C2. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>