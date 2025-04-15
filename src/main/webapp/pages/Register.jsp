<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/Login.css">
</head>
<body>
<div class="left">
        <h1>Craving something?<br>Cravers Corner right on corner.</h1>
        <p>Cravers Corner, satisfies your craving any time and any where.</p>
    </div>

    <div class="right">
        <div class="form-box">
            <h2> Register an account</h2>

            <form action="" method="post">
                
				
				<input type="text" name="full_name" placeholder="Full Name" >
				<input type="text" name="userName" placeholder="Username" >
				<input type="text" name="phoneNumber" placeholder="Phone Number" >
				<input type="text" name="address" placeholder="Address" >
                <input type="email" name="email" placeholder="Your email" >
                <input type="password" name="password" placeholder="Set password" required>
                <input type="password" name="re-password" placeholder="Confirm password" required>
                <input type="submit" value="Sign up">
				
				
            </form>

            <div class="signin">
                Already have an account? <a href="login.jsp">Log In</a>
            </div>
        </div>
    </div>

</body>
</html>