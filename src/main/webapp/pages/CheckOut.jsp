<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Out</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="../css/CheckOut.css">
</head>
<body>
<jsp:include page="Header.jsp" />
<div class="container">
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>
    
    <div class="main">
    <section class="form">
    <form action="${pageContext.request.contextPath}/CheckoutServlet" method="post">
        <!-- General Information Section -->
        <div class="form-section">
            <h3>1. General Information</h3>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" value="${user.getUsername}" readonly>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" value="${user.getEmail}" readonly>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="tel" id="phone" value="${user.getPhone}" readonly>
            </div>
            <div class="form-group">
                <label for="orderNote">Order Note</label>
                <textarea id="orderNote" name="orderNote" rows="3"></textarea>
            </div>
        </div>
        <!-- Delivery Address Section -->
        <div class="form-section">
            <h3>2. Delivery Address</h3>
            <div class="form-group address-group">
                <div class="input-with-icon">
                    <input type="text" id="address" value="${user.getAddress}" readonly>
                    <button type="button" class="plus-btn">
                        <i class="fas fa-edit"></i>
                    </button>
                </div>
            </div>
        </div>
		<!-- Payment Methods Section -->
		<div class="form-section payment-section">
		    <h3>3. Payment Methods</h3>
		    <div class="payment-method">
		        <input type="radio" id="COD" name="payment" value="COD" checked>
		        <label for="COD">Cash on delivery</label>
		    </div>
		</div>
    </form>
	</section>
	<section class="order-summary">
	    <h3>Order Summary</h3>
	    <div class="summary-body">
	        <c:forEach var="item" items="${order.items}">
	            <div class="food-item">
	                <span class="food-name">Food ${item.foodId}</span>
	                <span class="food-price">Rs. ${item.price} X ${item.quantity}</span>
	            </div>
	        </c:forEach>
	    </div>
	    <div class="summary-footer">
	        <div class="price-row">
	            <span>Sub-total</span>
	            <span>Rs. ${order.totalAmount - 50}</span>
	        </div>
	        <div class="price-row">
	            <span>Delivery charge</span>
	            <span>Rs. 50</span>
	        </div>
	        <div class="total-row">
	            <span>Total</span>
	            <span>Rs. ${order.totalAmount}</span>
	        </div>
	        <button type="submit" class="place-order-btn">Place Order</button>
	    </div>
	</section>
    </div>
</div>
<jsp:include page="Footer.jsp" />
</body>
</html>