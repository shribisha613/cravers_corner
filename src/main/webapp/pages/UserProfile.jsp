<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    
    
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
	
	<c:if test="${not empty info}">
 <div class="info-alert">
      <c:out value="${info}" />
      <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
 </div>
</c:if>
    
 <c:if test="${not empty success}">
    <div class="popup-container success">
        <c:out value="${success}" />
        <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
    </div>
</c:if>
    <!-- Profile Section -->
    <div class="profile-section">
        <div class="profile-pic">
    <img src="<%=request.getContextPath()%>/images/pfp.jpg" alt="Profile">
    
    <!-- Edit Icon -->
    <button type="button" class="edit-btn" title="Edit Profile">
        <i class="fas fa-edit"></i>
    </button>

    <p style="margin-top:5px">${userProfile.username}</p>
</div>
        <form class="profile-form"  action="${pageContext.request.contextPath}/UserProfileServlet" method="post">
          <div class="form-group">
    <label>First Name</label>
    <input type="text" name="first_name" value="${param.first_name != null ? param.first_name : userProfile.first_name}" required>
</div>
<div class="form-group">
    <label>Last Name</label>
    <input type="text" name="last_name" value="${param.last_name != null ? param.last_name : userProfile.last_name}" required>
</div>
<div class="form-group">
    <label>Username</label>
    <input type="text" name="username" value="${param.username != null ? param.username : userProfile.username}" required>
</div>
<div class="form-group">
    <label>Email</label>
    <input type="text" name="email" value="${param.email != null ? param.email : userProfile.email}" required/>
</div>
<div class="form-group">
    <label>Phone</label>
    <input type="tel" name="phone" value="${param.phone != null ? param.phone : userProfile.phone}" required>
</div>
<div class="form-group">
    <label>Address</label>
    <input type="text" name="address" value="${param.address != null ? param.address : userProfile.current_address}" required>
</div>
<div class="form-group">
    <label>New Password (optional)</label>
    <input type="password" name="password" placeholder="Enter New Password" />
</div>

<div class="form-group">
    <label>Confirm New Password</label>
    <input type="password" name="confirm_password" placeholder="Retype the new password" />
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
