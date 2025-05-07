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
            <!-- Active Orders -->
            <div class="stat-card">
                <div class="stat-title">Total Active Orders</div>
                <div class="stat-count-box">${totalActiveOrders}</div>
            </div>

            <!-- Active Users -->
            <div class="stat-card">
                <div class="stat-icon"><i class="fas fa-users"></i></div>
                <div>
                    <div class="stat-title">Total Customers</div>
                    <div class="stat-count-box">${totalCustomers}</div>
                </div>
            </div>

            <!-- Total Categories -->
            <div class="stat-card">
                <div class="stat-title">Total Categories</div>
                <div class="stat-count-box">${totalCategories}</div>
            </div>

            <!-- Total Food Items -->
            <div class="stat-card">
                <div class="stat-icon"><i class="fas fa-utensils"></i></div>
                <div>
                    <div class="stat-title">Total Food Items</div>
                    <div class="stat-count-box">${totalFoodItems}</div>
                </div>
            </div>
        </div>

        <!-- Recent Orders Table -->
        <div class="recent-orders-section">
            <h3>Most Recent Orders</h3>
            <table class="recent-orders-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Amount (Rs.)</th>
                        <th>Status</th>
                        <th>View</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${recentOrders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.amount}</td>
                            <td>${order.status}</td>
                            <td><a href="viewOrder.jsp?id=${order.id}" class="view-link">View Details</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
