<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add Food</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddFood.css">

</head>
<body>



  <c:set var="activePage" value="food" scope="request" />
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />
  

  <div class="form-wrapper">
      <a href="${pageContext.request.contextPath}/pages/ManageFood.jsp" class="back-button">
  <i class="fa-solid fa-arrow-left"></i>
</a>
  
    <div class="popup">
      <c:if test="${not empty sessionScope.message}">
        <div class="popup-container ${sessionScope.messageType}">
          <c:out value="${sessionScope.message}" />
          <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
        </div>
        <c:remove var="message" scope="session" />
        <c:remove var="messageType" scope="session" />
      </c:if>
      

      <h3>Add New Food Item</h3>
     
      

      <form action="${pageContext.request.contextPath}/AddFoodServlet" method="post" enctype="multipart/form-data">

        <div class="form-group">
          <label for="foodName">Food Name</label>
          <input type="text" id="foodName" name="foodName" value="${sessionScope.foodName}" placeholder="Enter Food Name" required>
        </div>

        

        <div class="form-group">
          <label for="description">Description</label>
          <textarea id="description" name="description" placeholder="Enter Description" rows="3" required>${sessionScope.foodDescription}</textarea>
        </div>
        
        <div class="form-group">
          <label for="price">Price (NPR)</label>
          <input type="text" id="price" name="price" value="${sessionScope.foodPrice}" placeholder="Enter Price" required>
        </div>

        <div class="form-group">
          <label for="servingSize">Serving Size</label>
          <select id="servingSize" name="servingSize" required>
            <option value="" disabled selected>Select a size</option>
            <option value="Small">Small</option>
            <option value="Medium">Medium</option>
            <option value="Large">Large</option>
          </select>
        </div>

        <div class="form-group">
          <label for="categoryId">Category</label>
          <select id="categoryId" name="categoryId" required>
            <option value="" disabled selected>Select a category</option>
            <c:forEach var="category" items="${categoryList}">
              <option value="${category.id}">${category.name}</option>
            </c:forEach>
          </select>
        </div>
        
        
        <div class="form-group">
          <label for="image">Food Image</label>
          <input type="file" id="image" name="image" accept="image/*" required>
        </div>

        <div class="add-button-container">
          <button type="submit">Add Food</button>
        </div>
      </form>
    </div>
  </div>

  <div id="foodList"></div>

</body>
</html>


