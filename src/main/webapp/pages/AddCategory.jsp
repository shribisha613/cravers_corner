<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add Category</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddCategory.css">
</head>
<body>
<c:set var="activePage" value="category" scope="request" />
 <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />
  
  
     

      <div class="form-wrapper">
      
    
        <div class="popup">
        
        <c:if test="${not empty sessionScope.message}">
    <div class="popup-container ${sessionScope.messageType}">
        <c:out value="${sessionScope.message}" />
        <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
    </div>

    <!-- Clean up after display -->
    <c:remove var="message" scope="session" />
    <c:remove var="messageType" scope="session" />
</c:if>
          <h3>Add a New Food Category</h3>
         
          <form action="${pageContext.request.contextPath}/AddCategoryServlet" method="post" >
            <div class="form-group">
              <label for="name">Name</label>
              <input type="text" id="name" name="name" value="${sessionScope.formName}" placeholder="Enter Name" required>
            </div>
           
            <div class="form-group">
               <label for="description">Description</label>
  <textarea id="description" name="description" placeholder="Enter Description" rows="3" required">${sessionScope.formDescription}</textarea>
  
            </div>
            <div class="add-button-container">
              <button type="submit">Add Category</button>
            </div>
          </form>
        </div>
      </div>

      <!-- Category Display -->
      <div id="categoryList"></div>


</body>
</html>