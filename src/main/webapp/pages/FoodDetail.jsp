<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>

<%
int quantity = 1; // default value
String qtyParam = request.getParameter("qty");
if (qtyParam != null) {
    try {
        quantity = Integer.parseInt(qtyParam);
        if (quantity < 0) {
            quantity = 0;
        } else if (quantity > 10) {
            quantity = 10;
        }
    } catch (NumberFormatException e) {
        quantity = 1;
    }
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>${food.name} - Food Detail</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FoodDetail.css" />
</head>
<body>

<jsp:include page="Header.jsp" />

<!-- Food Detail Section -->
<div class="menu-content">
  <div class="food-detail">

    <!-- Food Image -->
    <div class="food-image">
      <img src="${food.image_url}" alt="${food.name}" />
    </div>

    <!-- Food Info -->
    <div class="food-info">
      <h1>${food.name}</h1>
      <p class="price">रु. ${food.price}</p>
      <p class="shipping-note">Shipping is calculated at checkout</p>

      <!-- Quantity Control (No page reload) -->
      <div class="quantity-wrapper">
        <button type="button" onclick="updateQuantity(-1)">–</button>
        <span id="quantity-display"><%= quantity %></span>
        <button type="button" onclick="updateQuantity(1)">+</button>
      </div>

      <!-- Add to Cart -->
      <form action="AddToCart.jsp" method="post">
        <input type="hidden" name="food_id" value="${food.food_id}" />
        <input type="hidden" id="quantity-input" name="quantity" value="<%= quantity %>" />
        <button type="submit" class="add-to-cart">Add to Cart</button>
      </form>

      <!-- Description -->
      <div class="description-section">
        <p><strong>Description</strong></p>
        
        <div class="desp">
        <p>${food.description}</p>
        </div>
      </div>
    </div>

  </div>
</div>

<jsp:include page="Footer.jsp" />

<!-- JavaScript for quantity -->
<script>
  let quantity = <%= quantity %>;

  function updateQuantity(change) {
    quantity += change;
    if (quantity < 0) quantity = 0;
    if (quantity > 10) quantity = 10;

    document.getElementById("quantity-display").innerText = quantity;
    document.getElementById("quantity-input").value = quantity;
  }
</script>

</body>
</html>
