<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Manage Food</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManageFood.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<c:set var="activePage" value="food" scope="request" />
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />

  <main>
   

    <div class="category-section">
      <h3>Cravers Corner’s Menu Board</h3>
       
    <div class="search-sort-container">  
        <form action="${pageContext.request.contextPath}/GetFoodServlet" method="get" class="search-container">
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
          
          
	 <form id="sortForm" method="get" action="${pageContext.request.contextPath}/GetFoodServlet">
	  <div class="sort-wrapper">
	    <span class="sort-label">Sort By:</span>
	    <select class="sort-dropdown" name="sort" onchange="document.getElementById('sortForm').submit()">
	      <option value="joined_date_desc" ${param.sort == 'joined_date_desc' ? 'selected' : ''}>Newest Addition</option>
	      <option value="name" ${param.sort == 'name' ? 'selected' : ''}>Name</option>
	      <option value="price_desc" ${param.sort == 'price_desc' ? 'selected' : ''}>Price: High to Low</option>
	      <option value="price_asc" ${param.sort == 'price_asc' ? 'selected' : ''}>Price: Low to High</option>
	    </select>
	  </div>
</form>

        

      </div>
      </div>
      
 <c:if test="${not empty success}">
    <div class="success-message" id="successMessage">
        <i class="fa-solid fa-check"></i> ${success}
         <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>

    </div>
    <c:remove var="success" scope="session" />
</c:if>

    <!-- Table-like header row -->
    <div class="food-header">
      <div>Name</div>
      <div>Image</div>
      <div>Description</div>
      <div>Category</div>
      <div>Serving Size</div>
      <div>Price</div>
      <div>Action</div>
    </div>
    
    <c:if test="${not empty errorMessage}">
    <div class="error-message">
        <i class="fas fa-exclamation-circle"></i> ${errorMessage}
    </div>
     <c:remove var="errorMessage" scope="session" />
</c:if>

 


    <c:forEach var="food" items="${foodList}">
      <div class="food-row">
        <div>${food.name}</div>
        <div><img src="${pageContext.request.contextPath}/${food.image_url}" alt="${food.name}"/>
</div>
        <div>${food.description}</div>
        <div>${food.category_name}</div> <!-- Display the category name -->
        <div>${food.serving_size}</div>
        <div>${food.price}</div>
        <div>
  <a href="${pageContext.request.contextPath}/EditFoodServlet?id=${food.food_id}" title="Edit">
    <i class="fas fa-edit"></i>
  </a>
 
  <a href="${pageContext.request.contextPath}/DeleteFoodServlet?food_id=${food.food_id}" 
   title="Delete" 
   onclick="return confirm('Are you sure you want to discontinue this food item?');">
   <i class="fas fa-trash" title="Delete"></i>
</a>
  
</div>
      </div>
    </c:forEach>
    
     
    

    <div class="add-category">
      <a href="${pageContext.request.contextPath}/AddFoodServlet">
    <button type="button" class="green-btn">Add Food</button>
</a>
    
    </div>
  </main>
</body>
</html>
