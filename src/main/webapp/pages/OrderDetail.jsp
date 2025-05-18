<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Order Details</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OrderDetail.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<jsp:include page="AdminHeader.jsp" />
<jsp:include page="SideNavAdmin.jsp" />

<main class="order-container">
  <div class="order-box">
    <h2>Order Detail</h2> 

    <div class="order-top">
      <div>
        <p><strong>Order Id:</strong> 1</p>
        <p><strong>Date:</strong> June 1, 2025</p>
      </div>
      <div class="order-by">
        <p><strong>Ordered by:</strong> username</p>
        <p class="contact-no"><strong>Contact No:</strong> +977-1234567890</p>
      </div>
    </div>

    <hr>

    <div class="order-address-note">
      <p><strong>Delivery Address:</strong> Pokhara</p>
      <div class="order-note">
        <p><strong>Order Note:</strong></p>
        <p>Please deliver quickly.</p>
      </div>
    </div>

    <hr>

    <div class="order-item">
  <img src="img/pizza.jpg" alt="Pizza">
  <div class="item-details">
    <p><strong>Pizza</strong></p>
  </div>
  <div class="qty-sub">
    <p>Quantity: 1</p>
    <p>Subtotal: Rs. 250</p>
  </div>
</div>
    
<div class="order-item">
  <img src="img/burger.jpg" alt="Burger">
  <div class="item-details">
    <p><strong>Burger</strong></p>
  </div>
  <div class="qty-sub">
    <p>Quantity: 1</p>
    <p>Subtotal: Rs. 100</p>
  </div>
</div>

      

    <hr>

    <div class="order-total">
      <p><strong>Total:</strong> Rs. 350</p>
    </div>
  </div>
</main>

</body>
</html>