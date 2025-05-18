<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <div class="order-row">
    	<div>101</div>
    	<div>Rs. 3456</div>
    	<div>  
    		<select name="status">
    			<option value="pending" >Pending</option>
    			<option value="completed" >Completed</option>
  			</select>
  		</div>
  		<div><a href="${pageContext.request.contextPath}/pages/OrderDetail.jsp"><button>View Details</button></a></div>
    </div>
	</main>
</body>
</html>