<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MyOrders.css">
</head>
</head>
<body>
    <h2>Starting My Orders <h2>
    <jsp:include page="Header.jsp" />
   
    <div class="toggle-buttons">
      <button id="profileBtn" class="active" onclick="showProfile()">Profile</button>
      <button id="ordersBtn" onclick="showOrders()">My Orders</button>
    </div>
    
    <div class="content-box" id="ordersSection" style="display: none;">
      <div class="order-box">
        <div class="order-date">Date: 2025-05-02</div>
        
        <div class="order-table">
          <div class="table-header">
            <div>Item</div>
            <div>Price</div>
            <div>Quantity</div>
            <div>Total</div>
          </div>

          <div class="table-row">
            <div>Chicken Biryani</div>
            <div>Rs. 200</div>
            <div>2</div>
            <div>Rs. 400</div>
          </div>

          <div class="table-row">
            <div>Delivery Charge</div>
            <div>Rs. 50</div>
            <div>-</div>
            <div>Rs. 50</div>
          </div>
        </div>

        <div class="order-summary">
          <div>Status: Complete</div>
          <div>Total: Rs. 450</div>
        </div>

        <hr />

        <!-- Repeat another order -->
        <div class="order-date">Date: 2025-04-28</div>

        <div class="order-table">
          <div class="table-header">
            <div>Item</div>
            <div>Price</div>
            <div>Quantity</div>
            <div>Total</div>
          </div>

          <div class="table-row">
            <div>Veg Thali</div>
            <div>Rs. 150</div>
            <div>1</div> -->
            <div>Rs. 150</div>
          </div>

          <div class="table-row">
            <div>Delivery Charge</div>
            <div>Rs. 30</div>
            <div>-</div>
            <div>Rs. 30</div>
          </div>
        </div>

        <div class="order-summary">
          <div>Status: Complete</div>
          <div>Total: Rs. 180</div>
        </div>

        <hr />
      </div>
    </div>
  </main>
  <script>
    function showProfile() {
      document.getElementById("profileSection").style.display = "block";
      document.getElementById("ordersSection").style.display = "none";

      document.getElementById("profileBtn").classList.add("active");
      document.getElementById("ordersBtn").classList.remove("active");
    }

    function showOrders() {
      document.getElementById("profileSection").style.display = "none";
      document.getElementById("ordersSection").style.display = "block";

      document.getElementById("ordersBtn").classList.add("active");
      document.getElementById("profileBtn").classList.remove("active");
    }
  </script>
    
</html>