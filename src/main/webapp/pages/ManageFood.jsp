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
       
      <div class="category-controls">
        <div class="search-container">
          <input type="text" placeholder="Search..." class="search" />
          <i class="fas fa-search"></i>
        </div>

        <div class="sort-wrapper">
			  <span class="sort-label">Sort By:</span>
			  <select class="sort-dropdown">
			    <option value="name">Name</option>
			    <option value="price-asc">Price: Low to High</option>
			    <option value="price-desc">Price: High to Low</option>
			    <option value="date">Date Added</option>
			  </select>
			</div>

      </div>
    </div>

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
          <i class="fas fa-edit" title="Edit"></i>
          <i class="fas fa-trash" title="Delete"></i>
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
