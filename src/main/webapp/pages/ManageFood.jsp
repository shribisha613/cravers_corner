<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Manage Food</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManageFood.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
  <jsp:include page="AdminHeader.jsp" />
  <jsp:include page="SideNavAdmin.jsp" />

  <main>
    <h2>Welcome, Admin</h2>
    
   <hr>

    <div class="category-section">
      <h3>Cravers Corner’s Menu Board</h3>
       
      <div class="category-controls">
        <div class="search-container">
          <input type="text" placeholder="Search..." class="search" />
          <i class="fas fa-search"></i>
        </div>

        <div class="sort-wrapper">
			  <span class="sort-label">Sort By:</span>
			  <select class="sort-dropdown">
			    <option value="name">Name</option>
			    <option value="price-asc">Price: Low to High</option>
			    <option value="price-desc">Price: High to Low</option>
			    <option value="date">Date Added</option>
			  </select>
			</div>

      </div>
    </div>

    <!-- Table-like header row -->
    <div class="food-header">
      <div>Name</div>
      <div>Image</div>
      <div>Description</div>
      <div>Category</div>
      <div>Price</div>
      <div>Action</div>
    </div>

    <!-- Food item rows -->
    <div class="food-row">
      <div>Pizza</div>
      <div><img src="../images/jodi combo.jpg" alt="Pizza" /></div>
      <div>Cheesy and delicious</div>
      <div>Italian</div>
      <div>$8.99</div>
      <div>
        <i class="fas fa-edit" title="Edit"></i>
        <i class="fas fa-trash" title="Delete"></i>
      </div>
    </div>

    <div class="food-row">
      <div>Burger</div>
      <div><img src="../images/jodi combo.jpg" alt="Burger" /></div>
      <div>Grilled beef patty</div>
      <div>American</div>
      <div>$6.49</div>
      <div>
        <i class="fas fa-edit" title="Edit"></i>
        <i class="fas fa-trash" title="Delete"></i>
      </div>
    </div>

    <div class="add-category">
      <button class="green-btn">Add Food</button>
    </div>
  </main>
</body>
</html>
