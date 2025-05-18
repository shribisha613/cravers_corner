<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Category</title>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddCategory.css">
  
</head>
<body>
<c:set var="activePage" value="category" scope="request" />
 <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />
  
  
    

      <div class="form-wrapper">
      <a href="${pageContext.request.contextPath}/pages/ManageCategory.jsp" class="back-button">
		  <i class="fa-solid fa-arrow-left"></i>
		</a>
      
    
        <div class="popup">
         
        <c:if test="${not empty sessionScope.message}">
    <div class="popup-container ${sessionScope.messageType}">
        <c:out value="${sessionScope.message}" />
        <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
    </div>
    
     <c:if test="${sessionScope.messageType == 'success'}">
    <c:remove var="category name" scope="session" />
    <c:remove var="description" scope="session" />
    <c:remove var="category_id" scope="session" />
  </c:if>
    

    <!-- Clean up after display -->
    <c:remove var="message" scope="session" />
    <c:remove var="messageType" scope="session" />
    
  
</c:if>
  
  		 <c:if test="${empty category}">
            <div style="color:red;">Category is NULL!</div>
         </c:if> 
 		 
          <h3>Edit Food Category</h3>
         
          <form action="${pageContext.request.contextPath}/EditCategoryServlet" method="post" >
            <div class="form-group">
             <input type="hidden" name="category_id" value="${category.category_id}" />
    
            
              <label for="name">Name</label>
              <input type="text" id="category_name" name="name" value="${category.name}" placeholder="Enter Category Name" required>
            </div>
           
            <div class="form-group">
               <label for="description">Description</label>
  <textarea id="description" name="description" placeholder="Enter Description" rows="3" required>${category.description}</textarea>
  
            </div>
            <div class="add-button-container">
              <button type="submit">Update Category</button>
            </div>
          </form>
        </div>
      </div>

      <!-- Category Display -->
      <div id="categoryList"></div>


</body>
</html>