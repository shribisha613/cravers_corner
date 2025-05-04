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
    <div class="nav-item ${activePage == 'dashboard' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/pages/AdminDashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
    </div>
  
   
    <div class="nav-item ${activePage == 'orders' ? 'active' : ''}">
        <a href="orders.jsp"><i class="fas fa-receipt"></i> Orders</a>
    </div>
    
    <div class="nav-item ${activePage == 'category' ? 'active' : ''}">
         <a href="${pageContext.request.contextPath}/GetCategoryServlet"><i class="fas fa-list"></i> Manage Category</a>
    </div>
    <div class="nav-item ${activePage == 'food' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/GetFoodServlet">
  <i class="fas fa-hamburger"></i> Manage Food
</a>
    </div>
    <div class="nav-item ${activePage == 'reports' ? 'active' : ''}">
        <a href="reports.jsp"><i class="fas fa-chart-bar"></i> Reports</a>
    </div>
</div>

</body>
</html>
