<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                

                <input type="text" name="identifier"  value="${param.identifier}"  placeholder=" Your email or username" required >
                <input type="password" name="password" value="${param.password}"  placeholder="Enter your Password" required >
                   <c:if test="${not empty loginError}">
                    <div style="color: red; text-align: center; font-size: 14px; margin:2px">
                        ${loginError}
                    </div>
                </c:if>

                <a href="#"><input type="submit" value="Log in"></a>
            </form>

            <div class="signup">
            Don't have an account yet? <a href="Register.jsp">  Register Now</a>
            </div>
        </div>
    </div>
</body>
</html>