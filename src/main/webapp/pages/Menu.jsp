<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<div class="menu-container">
    <!-- Sidebar -->
    <div class="sidebar">
        <h3>Category</h3>
        <ul>
            <li><a href="Menu.jsp?type=Nepali">Nepali</a></li>
            <li><a href="Menu.jsp?type=Korean">Korean</a></li>
            <li><a href="Menu.jsp?type=Western">Western</a></li>
            <li><a href="Menu.jsp?type=Combo">Combo</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="menu-content">
        <form method="get" action="Menu.jsp" class="search-form">
            <input type="text" name="search" placeholder="Search..." />
        </form>

        <h2><%= type %> Food</h2>

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
