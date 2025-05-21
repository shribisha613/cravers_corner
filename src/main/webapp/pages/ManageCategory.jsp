<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
   
    <div class="manage_category-section">
      <h3>Cravers Corner’s Manage Category Interface</h3>
           <div class="manage_category-controls">
  				<form action="${pageContext.request.contextPath}/GetCategoryServlet" method="get" class="search-container">
    				<input type="text" name="keyword" placeholder=".....search here....." class="search" value="${param.keyword}" />
    				<button type="submit" style="background: none; border: none; cursor: pointer;">
     				 <i class="fas fa-search"></i>
    				</button>
			   </form>
           </div> 
    </div>
    
   
  
  <c:if test="${not empty successMessage}">
    <div class="success-message" id="successMessage">
        <i class="fa-solid fa-check"></i> ${successMessage}
         <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>

    </div>
    <c:remove var="successMessage" scope="session" />
</c:if>

  <c:if test="${not empty errorMessage}">
    <div class="error-message" id="errorMessage">
        <i class="fas fa-exclamation-circle"></i> ${errorMessage}
         <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>

    </div>
    <c:remove var="errorMessage" scope="session" />
</c:if>

    <!-- Table-like header row -->
    <div class="food-header">
      <div>Category Name</div>
      <div>Description</div>
      <div>Action</div>
    </div>
    
    

    <c:forEach var="category" items="${categoryList}">
		  <div class="food-row">
		        <div>${category.name}</div>
		        <div>${category.description}</div>
		        
		        <div>
                   <a href="${pageContext.request.contextPath}/EditCategoryServlet?id=${category.category_id}" title="Edit">
                     <i class="fas fa-edit"></i>
                   </a>
  
                   <a href="${pageContext.request.contextPath}/DeleteCategoryServlet?id=${category.category_id}" 
                      onclick="return confirm('Are you sure you want to delete this category?');" title = "Delete">
                      <i class="fas fa-trash" ></i>
                   </a>
  
                </div>	
           </div>
    </c:forEach>

    <div class="add-category">
      <a href="${pageContext.request.contextPath}/AddCategoryServlet">
    <button type="button" class="green-btn">Add Category</button>
</a>
    </div>
    
    
    
  </main>
  
</body>
</html>
     