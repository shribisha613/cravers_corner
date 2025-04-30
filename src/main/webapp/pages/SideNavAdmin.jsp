<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Navigation Bar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/SideNavAdmin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<div class="side-nav">
    <div class="nav-item">
        <a href="dashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
    </div>
    <div class="nav-item">
        <a href="orders.jsp"><i class="fas fa-receipt"></i> Orders</a>
    </div>
    <div class="nav-item">
        <a href="manage-category.jsp"><i class="fas fa-list"></i> Manage Category</a>
    </div>
    <div class="nav-item">
        <a href="manage-food.jsp"><i class="fas fa-hamburger"></i> Manage Food</a>
    </div>
    <div class="nav-item">
        <a href="reports.jsp"><i class="fas fa-chart-bar"></i> Reports</a>
    </div>
</div>

</body>
</html>
