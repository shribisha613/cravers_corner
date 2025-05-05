<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cravers_corner.model.Category" %>
<%
    String type = request.getParameter("type");
    if (type == null || type.isEmpty()) {
        type = "Nepali";
    }
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Cravers Corner Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.css">

</head>
<body>

<jsp:include page="Header.jsp" />
<a href="${pageContext.request.contextPath}/GetCategoryServlet">Menu</a>
<div class="menu-container">
    <!-- Sidebar -->

	<div class="sidebar">
  <h3>Categories</h3>
  <ul>
    
    <c:choose>
      <c:when test="${not empty categoryList}">
        <c:forEach items="${categoryList}" var="category">
          <li>${category.name}</li> <!-- Just display the name, no link -->
        </c:forEach>
      </c:when>
      <c:otherwise>
        <li>No categories available.</li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>
</div>
    


  
    <div class="menu-content">
        <form method="get" action="Menu.jsp" class="search-form">
            <input type="text" name="search" placeholder="Search..." />
        </form>

      

        <div class="food-grid">
           
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp" />
</body>
</html>
