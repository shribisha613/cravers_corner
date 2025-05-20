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
        <p><strong>Order Id:</strong> ${order.id}</p>
        <p><strong>Date:</strong> ${order.date}</p>
      </div>
      <div class="order-by">
        <p><strong>Ordered by:</strong> ${order.orderedBy}</p>
        <p class="contact-no"><strong>Contact No:</strong> ${order.contactNo}</p>
      </div>
    </div>

    <hr>

    <div class="order-address-note">
      <p><strong>Delivery Address:</strong> ${order.address}</p>
      <div class="order-note">
        <p><strong>Order Note:</strong></p>
        <p>${order.note}</p>
      </div>
    </div>

    <hr>

    <!-- Loop through order items -->
    <c:forEach var="item" items="${order.items}">
      <div class="order-item">
        <img src="${pageContext.request.contextPath}/img/${item.imagePath}" alt="${item.productName}" />
        <div class="item-details">
          <p><strong>${item.productName}</strong></p>
        </div>
        <div class="qty-sub">
          <p><strong>Quantity:</strong> ${item.quantity}</p>
          <p><strong>Subtotal:</strong> Rs. ${item.subtotal}</p>
        </div>
      </div>
      <hr>
    </c:forEach>

    <div class="order-total">
      <p><strong>Total:</strong> Rs. ${order.total}</p>
    </div>
  </div>
</main>

</body>
</html>
