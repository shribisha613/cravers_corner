<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Include Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header.css">
<c:set var="isCartOpen" value="${isCartOpen != null && isCartOpen}" />
<header>
    <div class="logo">
        <img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo">
    </div>

    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/pages/Home.jsp">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/menu">Menu</a></li>
            <li><a href="<%=request.getContextPath()%>/AboutUs.jsp">About Us</a></li>
        </ul>
    </nav>

    <div class="icons">
 

		    <section class="icon">
		     
		      <form action="${pageContext.request.contextPath}/ViewCartServlet" method="get" style="display:inline;">
    <c:set var="currentUrl" value="${pageContext.request.requestURI}" />
    <c:if test="${not empty pageContext.request.queryString}">
        <c:set var="currentUrl" value="${currentUrl}?${pageContext.request.queryString}" />
    </c:if>
    <input type="hidden" name="returnPage" value="${currentUrl}" />
    
   <c:set var="isCartOpen" value="${param.openCart == 'true'}" />
    <button type="submit" class="icon-btn cart-btn ${isCartOpen ? 'active' : ''}" aria-label="View Cart">
        <i class="fas fa-shopping-cart"></i>
    </button>
</form>
		        
		            
		       
		    </section>
		    
		      


 <a href="<%=request.getContextPath()%>/UserProfileServlet" class="icon">
                     
                         
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
		            <button type="submit" class="icon-btn logout-btn" >
		                <i class="fas fa-sign-out-alt"></i>
		            </button>
		        </form>
		    </section>
</div>
    
    
</header>
<jsp:include page="/pages/AddToCart.jsp" />




