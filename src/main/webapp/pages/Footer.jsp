<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cravers Corner Footer</title>
<style>
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
        
         .footer-logo {
         width: 200px;
         height:200px;
         
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
</style>
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
                    <li><a href="<%=request.getContextPath()%>/pages/Home.jsp">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/Menu.jsp">Menu</a></li>
            <li><a href="<%=request.getContextPath()%>/AboutUs.jsp">About Us</a></li>
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