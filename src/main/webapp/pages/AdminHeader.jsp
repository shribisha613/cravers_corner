<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Include Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header.css">
<header>
    <div class="logo">
        <img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo">
    </div>

    
    <div class="icons">
 



 <a href="<%=request.getContextPath()%>/AdminUserProfileServlet" class="icon">
                     
                         
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
                    
                 </a>

   <section class="icon">

		        <form action="<%=request.getContextPath()%>/LogoutServlet" method="post">
		            <button type="submit" class="icon-btn logout-btn">
		                <i class="fas fa-sign-out-alt"></i>
		            </button>
		        </form>
		    </section>
</div>
    
    
</header>


