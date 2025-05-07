<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Food</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddFood.css">

</head>
<body>



  <c:set var="activePage" value="food" scope="request" />
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />
  

  <div class="form-wrapper">
   
      <a href="${pageContext.request.contextPath}/GetFoodServlet" class="back-button">
  <i class="fa-solid fa-arrow-left"></i>
</a>
  
  
    <div class="popup">
      <c:if test="${not empty sessionScope.message}">
        <div class="popup-container ${sessionScope.messageType}">
          <c:out value="${sessionScope.message}" />
          <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
        </div>
          <c:if test="${sessionScope.messageType == 'success'}">
    <c:remove var="name" scope="session" />
    <c:remove var="description" scope="session" />
    <c:remove var="price" scope="session" />
    <c:remove var="serving_size" scope="session" />
    <c:remove var="category_id" scope="session" />
  </c:if>

  <!-- Always remove message info after showing -->
  <c:remove var="message" scope="session" />
  <c:remove var="messageType" scope="session" />
</c:if>
      
      
      
 
      <h3>Edit Food Details</h3>
     
      

      <form action="${pageContext.request.contextPath}/EditFoodServlet" method="post" enctype="multipart/form-data">

        <div class="form-group">
          <label for="food_name">Food Name</label>
          <input type="text" id="food_name" name="name" value="${food.name}" placeholder="Enter Food Name" required>
        </div>

        

        <div class="form-group">
          <label for="description">Description</label>
          <textarea id="description" name="description" placeholder="Enter Description" rows="3" required>${food.description}</textarea>
        </div>
        
        <div class="form-group">
          <label for="price">Price (NPR)</label>
          <input type="text" id="price" name="price" value="${food.price}" placeholder="Enter Price" required>
        </div>

            <div class="form-group">
          <label for="serving_size">Serving Size</label>
          <select id="serving_size" name="serving_size" required>
            <!-- Option for Serving Size based on the current data -->
            <option value="Small" <c:if test="${food.serving_size == 'Small'}">selected</c:if>>Small (For 1 person)</option>
            <option value="Medium" <c:if test="${food.serving_size == 'Medium'}">selected</c:if>>Medium (For 2-3 people)</option>
            <option value="Large" <c:if test="${food.serving_size == 'Large'}">selected</c:if>>Large (For 4-5 people)</option>
            <option value="Family" <c:if test="${food.serving_size == 'Family'}">selected</c:if>>Family Size (For 6+ people)</option>
            <option value="Mini" <c:if test="${food.serving_size == 'Mini'}">selected</c:if>>Mini (Snack portion)</option>
            <option value="Party" <c:if test="${food.serving_size == 'Party'}">selected</c:if>>Party Size (For gatherings)</option>
          </select>
        </div>

           <div class="form-group">
          <label for="category_id">Category</label>
          <select name="category_id" required>
            <option value="0" <c:if test="${food.category_id == '0'}">selected</c:if>>-- Select Category --</option>
            <c:forEach var="category" items="${categoryList}">
              <option value="${category.category_id}" <c:if test="${food.category_id == category.category_id}">selected</c:if>>
                ${category.name}
              </option>
            </c:forEach>
          </select>
        </div>
        
        
        <div class="form-group">
          <label for="image">Food Image</label>
          
          <input type="file" id="image" name="image" accept="image/*" required>
          <c:if test="${not empty food.image_url}">
  <div class="form-group">
    <label>Current Image:</label><br>
    <img src="${pageContext.request.contextPath}/${food.image_url}" alt="Food Image" width="150px" height="auto" />
  </div>
</c:if>
        </div>

        <div class="add-button-container">
          <button type="submit">Edit Food</button>
        </div>
      </form>
    </div>
  </div>

  <div id="foodList"></div>

</body>
</html>


