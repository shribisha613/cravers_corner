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
	      <c:when test="${not empty categories}">
	        <c:forEach items="${categories}" var="category">
	          <li>
	            <a href="GetCategoryServlet?categoryId=${category.category_id}">
	              ${category.name}
	            </a>
	          </li>
	        </c:forEach>
	      </c:when>
	      <c:otherwise>
	        <li>No categories available.</li>
	      </c:otherwise>
	    </c:choose>
	  </ul>
	</div>
    


  
    <div class="menu-content">
        <form method="get" action="Menu.jsp" class="search-form">
            <input type="text" name="search" placeholder="Search..." />
        </form>

      

        <div class="food-grid">
            <c:forEach var="food" items="${foodList}">
                <c:if test="${food.category == type}">
                    <div class="food-item">
                        <img src="${pageContext.request.contextPath}/images/${food.image}" alt="${food.name}">
                        <div class="food-info">
                            <p class="food-name">${food.name}</p>
                            <p class="food-price">Rs. ${food.price}</p>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp" />
</body>
</html>
