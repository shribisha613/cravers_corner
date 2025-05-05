<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Manage Users</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManageUser.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<c:set var="activePage" value="users" scope="request" />
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />

  <main>
   

    <div class="category-section">
      <h3>Cravers Corner User Directory</h3>
       
    <div class="search-sort-container">  
        <form action="${pageContext.request.contextPath}/ManageUserServlet" method="get" class="search-container">
			  <input 
			    type="text" 
			    name="searchQuery" 
			    placeholder="Search..." 
			    class="search" 
			    value="${param.searchQuery}" 
			  />
			  <div class="search-icon-circle">
			    <button type="submit" class="search-button">
			      <i class="fas fa-search"></i>
			    </button>
			  </div>
				</form>
          
          
 
<form id="sortForm" method="get" action="${pageContext.request.contextPath}/your-servlet-url">
        <div class="sort-wrapper">
			  <span class="sort-label">Sort By:</span>
			  <select class="sort-dropdown">
			  <option value="userId">User Id</option> 
			  <option value="joined_date_desc">Newest Joined</option>
			    <option value="name">First Names</option>
			    
    			<option value="joined_date_asc">Oldest Joined</option>
			    
			  </select>
			</div>
			</form>

      </div>
      </div>
      


  <div class="user-header">
  <div>User ID</div>
  <div>Profile</div>
  <div>Username</div>
  <div>First name</div>
  <div>Last Name</div>
  <div>Email</div>
   <div>Phone</div>
   <div>Total Orders</div>
  <div>Joined Date</div>
  
  
</div>
    
    <c:if test="${not empty errorMessage}">
    <div class="error-message">
        <i class="fas fa-exclamation-circle"></i> ${errorMessage}
    </div>
</c:if>

    <c:forEach var="user" items="${userList}">
      <div class="user-row">
        <div>${user.user_id}</div>
        <div><img src="${pageContext.request.contextPath}/${user.profile_image_url}" alt="${user.username}"/>
</div>
        <div>${user.username}</div>
        <div>${user.first_name}</div>
        <div>${user.last_name}</div>
        <div>${user.email}</div> <!-- Display the category name -->
        <div>${user.phone}</div>
        <div>${user.first_name}</div>
         <div><fmt:formatDate value="${user.created_at}" 
                          pattern="yyyy-MM-dd" /></div>
       
      </div>
    </c:forEach>
    
     
    
   
  </main>
</body>
</html>
