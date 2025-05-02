<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ValidationMessage.css">
</head>
<body>


     <div class="error-alert">
                  
                  
                   <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
      </div>
	
	
	
 <div class="info-alert">
      
      <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
 </div>

    
 
    <div class="popup-container success">
        
        <button class="close-btn" onclick="this.parentElement.style.display='none'">×</button>
    </div>



</body>
</html>