<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogIn</title>
<link rel="stylesheet" href="../css/Login.css">
</head>
<body>
	<div class="left">
        <h1>Welcome Back!</h1>
        <p>Log in to order your craving quick and swift from Cravers Corner.</p>
    </div>

    <div class="right">
        <div class="form-box">
            <h2>Login to your account</h2>

            <form action="index.html" method="post">
                

                <input type="email" name="email" placeholder="Your email" >
                <input type="password" name="password" placeholder="Password" >

                <a href="#"><input type="submit" value="Log in"></a>
            </form>

            <div class="signup">
                Don't have an account? <a href="register.jsp">Register Now</a>
            </div>
        </div>
    </div>
</body>
</html>