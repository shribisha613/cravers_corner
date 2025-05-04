<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Manage Category</title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManageCategory.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
  <c:set var="activePage" value="category" scope="request" />
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />
  <div class="container">
    <aside>
      <ul>
        <li><a href="#">Dashboard</a></li>
        <li><a href="orders.html">Orders</a></li>
        <li><a href="manage-category.html">Manage Category</a></li>
        <li><a href="manage-food.html">Manage Food</a></li>
        <li><a href="reports.html">Reports</a></li>
      </ul>
    </aside>

    <main>
      <h2>Welcome, Username</h2>

      <div class="category-section">
        <h3>Category</h3>
        <input type="text" placeholder="Search..." class="search" />
      </div>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Description</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Pizza</td>
            <td><img src="images/pizza.jpeg"></td>
            <td>Cheesy and delicious</td>
            <td>
              <i class="fas fa-edit" title="Edit"></i>
              <i class="fas fa-trash" title="Delete"></i>
            </td>
          </tr>
          <tr>
            <td>Burger</td>
            <td><img src="images/burger.jpeg"></td>
            <td>Grilled beef patty</td>
            <td>
              <i class="fas fa-edit" title="Edit"></i>
              <i class="fas fa-trash" title="Delete"></i>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="add-category">
        <button>Add Category</button>
      </div>
    </main>
  </div>
</body>
</html>