<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <style>
        /* Keep your original CSS here, unchanged (already well styled) */
        /* ... (CSS code from your original post) ... */
    </style>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header.css">
    
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserProfile.css">
</head>
<body>

<jsp:include page="Header.jsp" />


<div class="container">
    <!-- Tab bar -->
    <div class="tabs">
        <a href="#" class="active">Profile</a>
        <a href="#">My Orders</a>
    </div>

    <c:if test="${not empty errorMessage}">
     <div class="error-alert">
                  <c:out value="${errorMessage}" />
                  
                   <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
      </div>
	</c:if>
    
    <c:if test="${not empty successMessage}">
        <p style="color:green; margin-bottom:10px;">${successMessage}</p>
    </c:if>

    <!-- Profile Section -->
    <div class="profile-section">
        <div class="profile-pic">
    <img src="<%=request.getContextPath()%>/images/pfp.jpg" alt="Profile">
    
    <!-- Edit Icon -->
    <button type="button" class="edit-btn" title="Edit Profile">
        <i class="fas fa-edit"></i>
    </button>

    <p><strong>${userProfile.username}</strong></p>
</div>
        <form class="profile-form"  action="${pageContext.request.contextPath}/UserProfileServlet" method="post">
            <div class="form-group">
                <label>First Name</label>
                <input type="text" name="first_name" value="${first_name}" required>
            </div>
            <div class="form-group">
                <label>Last Name</label>
                <input type="text" name="last_name" value="${last_name}" required>
            </div>
            
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" value="${username}" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" name="email" value="${email}" required/>

            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="tel" name="phone" value="${phone}" required>
            </div>
            
            <div class="form-group" >
                <label>Address</label>
                <input type="text" name="address" value="${current_address}" required>
            </div>
            <div class="form-group">
                <label>Your Password</label>
                <input type="password" name="password" value="${password}" required>
            </div>
            <div class="form-group">
                <label>Retype Password to confirm the change</label>
                <input type="password" name="confirm_password" >
            </div>
            
            <button type="submit" class="save-btn">Save</button>
        </form>
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
