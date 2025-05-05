<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Manage Category</title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManageCategory.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
  <c:set var="activePage" value="category" scope="request" />
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />
  <main>
   
      <div class="category-section">
        <h3>Cravers Corner Admin's Manage Category Section</h3>
        
        <div class = "manage-category">
        <div class="search-container">
        <input type="text" placeholder="Search..." class="search" />
        <i class="fas fa-search"></i>
      </div>

      <!-- Table-like header row -->
    <div class="food-header">
      <div>Name</div>
      <div>Description</div>
      <div>Action</div>
    </div>
     
</div>
        <div>${food.description}</div>
        
          <i class="fas fa-edit" title="Edit"></i>
          <i class="fas fa-trash" title="Delete"></i>
        </div>
      </div>

    
    <div class="add-category">
      <a href="${pageContext.request.contextPath}/AddCategoryServlet">
    <button type="button" class="green-btn">Add Category</button>
</a>  
    </main>
  </div>
</body>
</html>