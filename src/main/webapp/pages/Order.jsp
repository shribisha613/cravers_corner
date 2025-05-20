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
	<jsp:include page="AdminHeader.jsp" />
	<jsp:include page="SideNavAdmin.jsp" />
	
	<main>
	<div class="manage_order_section">
		<h3>Craver's Corner’s Order List</h3>
	</div>
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
		      <select name="status">
		        <option value="pending" ${order.status == 'pending' ? 'selected' : ''}>Pending</option>
		        <option value="completed" ${order.status == 'completed' ? 'selected' : ''}>Completed</option>
		      </select>
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