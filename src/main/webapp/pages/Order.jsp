<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Order</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Order.css">
</head>
<body>
<c:set var="activePage" value="orders" scope="request" />
	<jsp:include page="AdminHeader.jsp" />
	<jsp:include page="SideNavAdmin.jsp" />
	
	<main>
	<div class="manage_order_section">
		<h3>Craver's Corner’s Order List</h3>
	</div>
	
	<hr>
	
	     <c:if test="${not empty errorMessage}">
    <div class="error-message">
      <i class="fa-solid fa-burger"></i> ${errorMessage}
    </div>
</c:if>

<c:if test="${not empty successMessage}">
    <div class="success-message" id="successMessage">
        <i class="fa-solid fa-check"></i> ${successMessage}
         <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>

    </div>
    <c:remove var="successMessage" scope="session" />
</c:if>
	
    <!-- Table-like header row -->
    <div class="order-header">
	     <div>Order Id</div>
	     <div>Total Amount</div>
	     <div>Status</div>
	     <div>Action</div>
    </div>
		<c:forEach var="order" items="${orders}">
		  <div class="order-row">
		    <div>${order.orderId}</div>
		    <div>Rs. ${order.totalAmount}</div>
		    <div>
		  <form action="${pageContext.request.contextPath}/UpdateOrderStatusServlet" method="post">
		    <input type="hidden" name="orderId" value="${order.orderId}" />
		    <select name="status" onchange="this.form.submit()">
		      <option value="pending" ${order.status == 'pending' ? 'selected' : ''}>Pending</option>
		      <option value="completed" ${order.status == 'completed' ? 'selected' : ''}>Completed</option>
		    </select>
		  </form>
</div>
		    <div>
		      <a href="${pageContext.request.contextPath}/ViewOrderDetailServlet?orderId=${order.orderId}">
		        <button>View Details</button>
		      </a>
		    </div>
		  </div>
		</c:forEach>
	</main>
</body>
</html>