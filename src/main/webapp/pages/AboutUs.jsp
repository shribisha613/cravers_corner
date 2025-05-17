<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cravers corner About Us</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/AboutUs.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	</head>
<body>
<jsp:include page="Header.jsp" />
	<div class="aboutUs-container">
		<img src="${pageContext.request.contextPath}/images/aboutUs.jpeg" alt="About Us Banner">
		<div class="overlay-text">
		    <h1>About Us</h1>
		    <p>Welcome to Cravers Corner!</p>
		    <p>"We're a team of 5 passionate foodies dedicated to bringing delicious meals to your fingertips. Our mission is to make online food ordering easy, fun, and satisfying for everyone."</p>
    	</div>
    </div>
    <h2 class="section-heading">Our Members</h2>
	<div class="team">
		<div class="team-card">
			 <div class="image-container">
			    <img src="${pageContext.request.contextPath}/images/shribisha.jpg" alt="shribisha">
			    <div class="social-icons">
			      <a href="https://github.com/shribisha613" target="_blank"><i class="fab fa-github"></i></a>
			      <a href="www.linkedin.com/in/shribisha-buddhacharya-6a64732a8 -linked in link" target="_blank"><i class="fab fa-linkedin-in"></i></a>
			    </div>
			 </div>        
	          	<h3>Shribisha Buddhacharya</h3>
	  			<p class="email-box">shribisha@gmail.com</p>
	  			<p>Shirbisha is responsible for handling backend for authentication, user profiles, category and food management, and created related servlets and DAOs.</p>
	    </div>
	    
	    <div class="team-card">
			 <div class="image-container">
			    <img src="${pageContext.request.contextPath}/images/yashica.jpg" alt="yashica">
			    <div class="social-icons">
			      <a href="https://github.com/Yashi-mgr" target="_blank"><i class="fab fa-github"></i></a>
			      <a href="https://linkedin.com/in/member1" target="_blank"><i class="fab fa-linkedin-in"></i></a>
			    </div>
			</div>        
	          	<h3>Yashica Kamu Magar</h3>
	  			<p class="email-box">yashicamagar@gmail.com</p>
	  			<p>Yashica developed frontend and backend for the menu, implemented search functionality, and tested menu and management pages ensuring a smooth user experience for customers..</p>    
	    </div>
	    
	    <div class="team-card">
			<div class="image-container">
			    <img src="${pageContext.request.contextPath}/images/juna.jpg" alt="juna">
			    <div class="social-icons">
			      <a href="https://github.com/" target="_blank"><i class="fab fa-github"></i></a>
			      <a href="https://linkedin.com/in/member2" target="_blank"><i class="fab fa-linkedin-in"></i></a>
			    </div>
			</div>        
	          	<h3>Chitra Kala Chettri</h3>
	  			<p class="email-box">chitrakala@gmail.com</p>
	  			<p>Juna created frontend for adding categories, handled database management and normalization, and contributed to testing and references.</p>
	        </div>
	    
	    <div class="team-card">
			 <div class="image-container">
			    <img src="${pageContext.request.contextPath}/images/diya.jpg" alt="diya">
			    <div class="social-icons">
			      <a href="https://github.com/diyaxox" target="_blank"><i class="fab fa-github"></i></a>
			      <a href="https://linkedin.com/in/member3" target="_blank"><i class="fab fa-linkedin-in"></i></a>
			    </div>
			</div>        
	          	<h3>Diya Gurung</h3>
	  			<p class="email-box">diyagurung@gmail.com</p>
	  			<p>Diya designed wireframes and built frontend for registration, login, user profile, checkout, and category retrieval </p>
	    </div>
	    
	    <div class="team-card">
			  <div class="image-container">
			     <img src="${pageContext.request.contextPath}/images/mishma.jpeg" alt="mishma">
			     <div class="social-icons">
			       <a href="https://github.com/mishma-thapa-magar" target="_blank"><i class="fab fa-github"></i></a>
			       <a href="https://linkedin.com/in/member4" target="_blank"><i class="fab fa-linkedin-in"></i></a>
			     </div>
			 </div>        
	           	<h3>Mishma Thapa Magar</h3>
	  			<p class="email-box">mishmathapa@gmail.com</p>
	  			<p>Mishma designed the admin dashboard, managed categories and order history, worked on ERD, and conducted testing and documentation.</p>
	     </div> 
	</div>	    

<jsp:include page="Footer.jsp" />
</body>
</html>