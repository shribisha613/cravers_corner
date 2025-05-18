<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Out</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/CheckOut.css">
</head>
<body>
<jsp:include page="Header.jsp" />


<div class="container">
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>
    
    
    
    <div class="main">
    
    <form action="${pageContext.request.contextPath}/PlaceOrderServlet" method="post">
    <section class="form">
    
        <!-- General Information Section -->
        <div class="form-section">
            <h3>1. General Information</h3>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" value="${user.username}" readonly>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" value="${user.email}" readonly>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="tel" id="phone" value="${user.phone}" readonly>
            </div>
            <div class="form-group">
                <label for="orderNote">Order Note</label>
                <textarea id="orderNote" name="orderNote" rows="3" placeholder= "Please enter any order note you want."></textarea>
            </div>
        </div>
        <!-- Delivery Address Section -->
        
         <div class="form-group">
                <label for="address">Delivery Address</label>
                <input type="text" id="address" name = "shipping_address" value="${user.current_address}">
            </div>
        
		<!-- Payment Methods Section -->
		<div class="form-section payment-section">
		    <h3>2. Payment Methods</h3>
		    <div class="payment-method">
		        <input type="radio" id="COD" name="payment" value="COD" checked>
		        <label for="COD">Cash on delivery</label>
		    </div>
		</div>

	
	    <h3>Order Summary</h3>
	    <div class="summary-body">
	        <c:forEach var="item" items="${order.items}">
	            <div class="food-item">
	                 <img src="${pageContext.request.contextPath}/${item.image_url}" alt="Food Image" class="food-image">
	                <p class="food-name">${item.food_name}</p>
	                <span class="food-price">Rs. ${item.price} X ${item.quantity}</span>
	            </div>
	        </c:forEach>
	    </div>
	    <div class="summary-footer">
	        <div class="price-row">
	            <span>Sub-total</span>
	            <span>Rs. ${order.totalAmount}</span>
	        </div>
	        <div class="price-row">
	            <span>Delivery charge</span>
	            <span>Rs. 100</span>
	        </div>
	        <div class="total-row">
	            <span>Total</span>
	            <span>Rs. ${order.totalAmount + 100}</span>
	        </div>
	        <button type="submit" class="place-order-btn">Place Order</button>
	        
	    </div>
	        
	</section>
	</form>
    </div>
</div>
<jsp:include page="Footer.jsp" />
</body>
</html>