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

<body>
    <jsp:include page="Header.jsp" />

 <main>
 
  <!-- Tab bar -->
    <div class="tabs">
        <a href="${pageContext.request.contextPath}/UserProfileServlet" >Profile</a>
        <a href="#" class="active">My Orders</a>
    </div>
    
    <c:if test = "${not empty errorMessage}">
    <div class = "error-alert">
        <c:out value = "${errorMessage}"/>
        <button class = "close-btn" onclick= "this.parentElement.style.display= 'none'">x</button>
    </div>
    </c:if>
    
    
    <c:if test="${not empty info}">
     <div class="info-alert">
           <c:out value="${info}" />
                  
            <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
      </div>
	</c:if>
    
    <div class="content-box" id="ordersSection">
      <div class="order-box">
      <label>Order Date</label>
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
    </body>

    
</html>