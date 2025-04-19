<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css">
</head>
<body>
	<div class="left">
        <h1>Welcome Back!</h1>
        <p>Log in to order your craving quick and swift from Cravers Corner.</p>
    </div>

    <div class="right">
        <div class="form-box">
            <h2>Login to your account</h2>
            
            <c:if test="${not empty loginError}">
                    <div class="error-alert">
                        ${loginError}
                        
                        <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
                    </div>
                </c:if>

            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                
     

                <input type="text" name="identifier"  value="${param.identifier}"  placeholder=" Your email or username" required >
                <input type="password" name="password" value="${param.password}"  placeholder="Enter your Password" required >
              
                <a href="#"><input type="submit" value="Log in"></a>
            </form>

            <div class="signup">
            Don't have an account yet? <a href="${pageContext.request.contextPath}/pages/Register.jsp">Register Now</a>
            </div>
        </div>
    </div>
</body>
</html>