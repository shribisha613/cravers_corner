<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
</head>
<body>

<c:set var="activePage" value="dashboard" scope="request" />
<jsp:include page="AdminHeader.jsp" />
<jsp:include page="SideNavAdmin.jsp" />

<!-- Main Content -->
<main class="main-content">
    <h2 class="greeting">Hello, ${username}</h2>

    <!-- Top Boxes -->
    <div class="stats-grid">
        <!-- Total Active Orders -->
        <div class="stat-card">
	        <div class="stat-icon"><i class="fas fa-users"></i></div>
	        <div>
	            <div class="stat-title">Total Active Orders</div>
	            <div class="stat-count-box">${totalActiveOrders}</div>
	        </div>
		</div>
        <!-- Total Customers -->
        <div class="stat-card">
            <div class="stat-icon"><i class="fas fa-users"></i></div>
            <div>
                <div class="stat-title">Total Customers</div>
                <div class="stat-count-box">${totalCustomers}</div>
            </div>
        </div>

        <!-- Total Categories -->
        <div class="stat-card">
	        <div class="stat-icon"><i class="fa-solid fa-list"></i></div>
		    <div>
	            <div class="stat-title">Total Categories</div>
	            <div class="stat-count-box">${totalCategories}</div>
	        </div>
		</div>
        <!-- Total Food Items -->
        <div class="stat-card">
            <div class="stat-icon"><i class="fa-solid fa-bowl-food"></i></div>
            <div>
                <div class="stat-title">Total Food Items</div>
                <div class="stat-count-box">${totalFoodItems}</div>
            </div>
        </div>

        <!-- New Features -->

        <!-- Orders Today -->
        <div class="stat-card">
        	<div class="stat-icon"><i class="fa-solid fa-bowl-food"></i></div>
            <div>
	            <div class="stat-title">Orders Today</div>
	            <div class="stat-count-box">${todaysOrders}</div>
	        </div>
        </div>

        <!-- Most Ordered Item -->
        <div class="stat-card">
	        <div class="stat-icon"><i class="fas fa-utensils"></i></div>
	        <div>
	            <div class="stat-title">Most Ordered Item</div>
	            <div class="stat-count-box">${mostOrderedItem}</div>
	        </div>
        </div>

        <!-- Most Ordered Category -->
        <div class="stat-card">
        	<div class="stat-icon"><i class="fa-solid fa-list"></i></div>
	        <div>
	            <div class="stat-title">Most Ordered Category</div>
	            <div class="stat-count-box">${mostOrderedCategory}</div>
            </div>
        </div>

        <!-- Users Registered (Last Month) -->
        <div class="stat-card">
        	<div class="stat-icon"><i class="fas fa-users"></i></div>
	        <div>
	            <div class="stat-title">Users This Month</div>
	            <div class="stat-count-box">${usersLastMonth}</div>
	         </div>
        </div>
    </div>

    <!-- Recent Orders Table -->
    <div class="recent-orders-section">
        <h3>Most Recent Orders</h3>
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
    </div>
</main>
</body>
</html>
