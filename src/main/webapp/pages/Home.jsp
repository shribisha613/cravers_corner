<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cravers Corner Home Page</title>
</head>
<body>

<jsp:include page="Header.jsp" />

		<div style="padding: 20px;">
    <c:if test="${not empty userWithSession}">
        <h2>Hi, <c:out value="${userWithSession.first_name}" /></h2>
    </c:if>
    
</div>

 
</body>
</html>