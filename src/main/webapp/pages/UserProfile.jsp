<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cravers Corner User Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserProfile.css"><!-- Include your styles here -->
</head>
<body>
<div class="main">
<div>
    <h1>Update Your Profile</h1>
    
    </div>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    
  
    
    <div>
    <form action="${pageContext.request.contextPath}/UserProfileServlet" method="post">
    <div>
        <label for="first_name">First Name</label>
        <input type="text" id="first_name" name="first_name" value="${userProfile.first_name}" placeholder="${userProfile.first_name}" required>
    </div>
    <div>
        <label for="last_name">Last Name</label>
        <input type="text" id="last_name" name="last_name" value="${userProfile.last_name}" placeholder="${userProfile.last_name}" required>
    </div>
    <div>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="${userProfile.email}" placeholder="${userProfile.email}" required>
    </div>
    <div>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" value="${userProfile.username}" placeholder="${userProfile.username}" required>
    </div>
    <div>
        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" value="${userProfile.phone}" placeholder="${userProfile.phone}" required>
    </div>
    <div>
        <label for="address">Address</label>
        <textarea id="address" name="address" placeholder="${userProfile.current_address}" required>${userProfile.current_address}</textarea>
    </div>
    <div>
        <label for="password">New Password (leave empty to keep current)</label>
        <input type="password" id="password" name="password" placeholder="Enter new password (leave empty to keep current)">
    </div>
    <div>
        <label for="confirm_password">Confirm Password</label>
        <input type="password" id="confirm_password" name="confirm_password" placeholder="Re-enter your new password">
    </div>
    <div>
        <button type="submit">Save Changes</button>
    </div>
</form>

</div>

</div>


    
</body>
</html>
