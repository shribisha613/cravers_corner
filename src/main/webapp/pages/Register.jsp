<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>


<link rel="stylesheet" href="../css/Login.css">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css">
</head>
<body>

<div class="left">
    <h1>Craving something?<br>Cravers Corner right on corner.</h1>
    <p>Cravers Corner, satisfies your craving any time and any where.</p>
</div>

<div class="right">
    <div class="form-box">
        <h2>Register your account</h2>
        
        	
	<c:if test="${not empty errorMessage}">
     <div class="error-alert">
                  ${errorMessage}
                  
                   <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
      </div>
	</c:if>

        <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
    
            
            <div class="form-group">
			    <input type="text" id="first_name" name="first_name" value="${first_name}" placeholder="Enter your First Name" required>
			</div>
			
			<div class="form-group">
			    <input type="text" id="last_name" name="last_name" value="${last_name}" placeholder="Enter your Last Name" required>
			</div>

            <div class="form-group">
                <input type="text" id="username" name="username" value="${username}" placeholder="Enter your Username" required>
            </div>

            <div class="form-group">
                <input type="text" id="phone" name="phone" value="${phone}" placeholder="Enter your Phone Number" required>
            </div>

            <div class="form-group">
                <input type="text" id="address" name="address" value="${address}" placeholder="Enter your Address" required>
            </div>

            <div class="form-group">
                <input type="email" id="email" name="email" value="${email}" placeholder="Enter your Email" required>
            </div>

            <div class="form-group">
                <input type="password" id="password" name="password" placeholder="Set a new Password" required>
            </div>

            <div class="form-group">
                <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirm your password" required>
            </div>

            <div class="form-group">
                <input type="submit" value="Sign Up">
            </div>
        </form>

        <div class="signin">
            Already have an account? <a href="${pageContext.request.contextPath}/pages/Login.jsp">Log In</a>
        </div>
    </div>
</div>

</body>
</html>
