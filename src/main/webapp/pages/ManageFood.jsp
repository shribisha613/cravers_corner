<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>ManageFood</title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManageFood.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
  <jsp:include page="AdminHeader.jsp" />
  <div class="container">
   

    <main>
      <h2>Welcome, Username</h2>

      <div class="category-section">
        <h3>Category</h3>
        <input type="text" placeholder="Search..." class="search" style="width: 300px;" />
        <select>
          <option value="">Sort by</option>
          <option value="name">Name</option>
          <option value="price-asc">Price: Low to High</option>
          <option value="price-desc">Price: High to Low</option>
        </select>
      </div>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Description</th>
            <th>Category</th>
            <th>Price</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Pizza</td>
            <td><img src="images/pizza.jpeg" alt="Pizza" width="60"></td>
            <td>Cheesy and delicious</td>
            <td>Italian</td>
            <td>$8.99</td>
            <td>
              <i class="fas fa-edit" title="Edit"></i>
              <i class="fas fa-trash" title="Delete"></i>
            </td>
          </tr>
          <tr>
            <td>Burger</td>
            <td><img src="images/burger.jpeg" alt="Burger" width="60"></td>
            <td>Grilled beef patty</td>
            <td>American</td>
            <td>$6.49</td>
            <td>
              <i class="fas fa-edit" title="Edit"></i>
              <i class="fas fa-trash" title="Delete"></i>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="add-category">
        <button>Add Food</button>
      </div>
    </main>
  </div>
</body>
</html>