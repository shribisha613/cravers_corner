<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Fetch user role from session
    String role = (String) session.getAttribute("role");
    request.setAttribute("role", role); // expose it for JSTL
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .container {
            margin-top: 100px;
            padding: 20px;
        }

        h1 {
            color: red;
            font-size: 28px;
        }

        p {
            color: #555;
            font-size: 16px;
            margin: 20px 0;
        }

        a.button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
        }

        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Access Denied</h1>
    <p>You do not have permission to access this page.</p>

    <!-- Conditional button based on session role -->
    <c:choose>
        <c:when test="${role == 'admin'}">
            <a href="${pageContext.request.contextPath}/pages/AdminDashboard.jsp" class="button">Return to Home</a>
        </c:when>
        <c:when test="${role == 'customer'}">
            <a href="${pageContext.request.contextPath}/pages/Home.jsp" class="button">Return to Home</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/index.jsp" class="button">Return to Home</a>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
