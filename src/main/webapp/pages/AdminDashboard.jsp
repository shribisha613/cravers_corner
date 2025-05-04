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

<!-- Header -->
<header class="header">
    <div class="header-left">
        <i class="fas fa-user-circle"></i>
        <a href="logout.jsp" class="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </div>
    <div class="header-right">
        <div class="logo">Foodie Admin</div>
    </div>
</header>

<!-- Page Container -->
<div class="page-container">
    <!-- Sidebar -->
    <aside class="sidebar">
        <a href="dashboard.jsp" class="active">Dashboard</a>
        <a href="orderlist.jsp">Order List</a>
        <a href="managecategory.jsp">Manage Category</a>
        <a href="managefood.jsp">Manage Food</a>
        <a href="reports.jsp">Reports</a>
    </aside>

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
                    <div class="stat-title">Total Active Users</div>
                    <div class="stat-count-box">${totalActiveUsers}</div>
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
</div>

<!-- Footer -->
<footer class="footer">
    &copy; 2025 Online Food Ordering Admin Panel
</footer>

</body>
</html>
