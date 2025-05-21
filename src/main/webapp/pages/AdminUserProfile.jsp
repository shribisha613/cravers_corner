<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin User Profile</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminUserProfile.css">
</head>
<body>

<jsp:include page="AdminHeader.jsp" />
<jsp:include page="SideNavAdmin.jsp" />

<div class="container">
    <!-- Tab bar -->
    <div class="tabs">
        <a href="#" class="active">Profile</a>
    </div>

    <c:if test="${not empty errorMessage}">
     <div class="error-alert">
                  <c:out value="${errorMessage}" />
                  
                   <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
      </div>
       <c:remove var="errorMessage" scope="session" />
	</c:if>
	
	<c:if test="${not empty info}">
 <div class="info-alert">
      <c:out value="${info}" />
      <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
 </div>
  <c:remove var="info" scope="session" />
</c:if>
    
 <c:if test="${not empty success}">
    <div class="popup-container success">
        <c:out value="${success}" />
        <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
    </div>
     <c:remove var="success" scope="session" />
</c:if>

 <div id="fileMessage" class="popup-container success" style="display: none;">
 
  <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
 </div>
    <!-- Profile Section -->
    <div class="profile-section">
        <div class="profile-pic">
    <c:choose>
    <c:when test="${empty userWithSession.profile_image_url}">
        <!-- Default image if no profile image is uploaded -->
          <img src="${pageContext.request.contextPath}/profile_photos/default_profile.png" alt="Profile Image" class="profile-image" />
    </c:when>
    <c:otherwise>
        <!-- Custom uploaded image -->
          <img src="${pageContext.request.contextPath}/${userWithSession.profile_image_url}" alt="Profile Image" class="profile-image" />
    </c:otherwise>
</c:choose>
    
     <label for="profileImageInput" class="edit-btn" title="Edit Profile">
        <i class="fas fa-edit"></i>
    </label>
    
     
   
    <p style="margin-top:5px">${userProfile.username}</p>
</div>
        <form class="profile-form" 
      action="${pageContext.request.contextPath}/AdminUserProfileServlet" 
      method="post" 
      enctype="multipart/form-data">
      
      
      <input type="file" id="profileImageInput" name="profile_image" accept="image/*" style="display: none;" />
          
         <script>
  document.getElementById('profileImageInput').addEventListener('change', function () {
      const file = this.files[0];
      const messageDiv = document.getElementById('fileMessage');
      
      if (file) {
          messageDiv.innerText = "File selected. Please click Save to change your profile picture.";
          messageDiv.style.display = "block";
      } else {
          messageDiv.innerText = "";
          messageDiv.style.display = "none";
      }
  });
</script>
         
        
          
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


            <input type="hidden" name="existing_profile_image" value="${userWithSession.profile_image_url}" />
            <button type="submit" class="save-btn">Save</button>
        </form>
    </div>
</div>

</body>
</html>