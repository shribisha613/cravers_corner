<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cravers_corner.model.Category" %>



<%
    String type = request.getParameter("type");
    if (type == null || type.isEmpty()) {
        type = "Nepali";
    }
    request.setAttribute("type", type); // ✅ Add this line
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

<div class="menu-container">
    <!-- Sidebar -->
	<div class="sidebar">
	  <h3>Categories</h3>
		<ul>
		  <c:choose>
		    <c:when test="${not empty categoryList}">
		      <c:forEach items="${categoryList}" var="category">
				      <li>
				  <a href="${pageContext.request.contextPath}/menu?type=${category.name}"
				   class="category-link <c:if test='${category.name eq type}'>active</c:if>">
				    ${category.name}
				  </a>
				</li>
		      </c:forEach>
		    </c:when>
		    <c:otherwise>
		      <li><span class="no-categories">No categories available</span></li>
		    </c:otherwise>
		  </c:choose>
		</ul>
	</div>
	
    <div class="menu-content">
        <!-- ✅ Search Form -->
        <form method="get" action="menu" class="search-form">
    		<input type="text" name="search" placeholder="Search..." value="${searchKeyword}" />
    		<input type="hidden" name="type" value="${type}" />

		    <label for="sort">Sort by:</label>
		    <select name="sort" id="sort" onchange="this.form.submit()">
		        <option value="">-- Select --</option>
		        <option value="asc" <c:if test="${sortOrder == 'asc'}">selected</c:if>>Low to High</option>
		        <option value="desc" <c:if test="${sortOrder == 'desc'}">selected</c:if>>High to Low</option>
		    </select>
		</form>

        <h2>Cravers Corner Menu Board</h2>

        <!-- ✅ Food Items Display -->
        <div class="food-grid">
            <c:choose>
                <c:when test="${not empty foodList}">
                    <c:forEach var="food" items="${foodList}">
                        <div class="food-item">
                            <img src="${food.image_url}" alt="${food.name}" />
                            <h3>${food.name}</h3>
                            <p>रु. ${food.price}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No food items found.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp" />
</body>
</html>
