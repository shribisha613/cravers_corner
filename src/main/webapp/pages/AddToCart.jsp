<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart - Cravers Corner</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddToCart.css">
</head>
<body>

<main>
    <div id="cartPopup" class="cart-popup hidden">
        <div class="cart-header">
            <h3>Your Cart</h3>
            <span class="close-btn" onclick="closeCart()">&times;</span>
        </div>

        <hr class="divider" />

       <div id="cartItems" class="cart-items">
 <c:choose>
  <c:when test="${not empty cartItems}">
   <c:forEach var="item" items="${cartItems}">
  <div class="cart-item">
    
    <div class="item-details">
      
        <img src="${pageContext.request.contextPath}/${item.image_url}" alt="food" class="cart-item-image" />
        <div>
          <div class="item-name">${item.food_name}</div>
          <div>Price: Rs. <fmt:formatNumber value="${item.price}" maxFractionDigits="2" /></div>
        </div>
      </div>
   
    
    <div class="quantity-controls">
      <form action="${pageContext.request.contextPath}/UpdateCartServlet" method="post">
        <input type="hidden" name="cart_item_id" value="${item.cart_item_id}" />
        <input type="hidden" name="quantity" value="${item.quantity - 1}" />
        <c:set var="currentUrl" value="${pageContext.request.requestURI}" />
        <c:if test="${not empty pageContext.request.queryString}">
          <c:set var="currentUrl" value="${currentUrl}?${pageContext.request.queryString}" />
        </c:if>
        <input type="hidden" name="returnPage" value="${currentUrl}" />
        <button type="submit" class="btn" aria-label="Decrease quantity" ${item.quantity <= 1 ? "disabled" : ""}>–</button>
      </form>
      
      <span>${item.quantity}</span>
      
      <form action="${pageContext.request.contextPath}/UpdateCartServlet" method="post">
        <input type="hidden" name="cart_item_id" value="${item.cart_item_id}" />
        <input type="hidden" name="quantity" value="${item.quantity + 1}" />
        <input type="hidden" name="returnPage" value="${currentUrl}" />
        <button type="submit" class="btn" aria-label="Increase quantity" ${item.quantity >= 10 ? "disabled" : ""}>+</button>
      </form>
    </div>

    <div style="min-width: 100px; text-align: right;">
      Rs. <fmt:formatNumber value="${item.subtotal}" maxFractionDigits="2" />
    </div>
    
    <form action="${pageContext.request.contextPath}/RemoveFromCartServlet" method="post">
      <input type="hidden" name="cart_item_id" value="${item.cart_item_id}" />
      <input type="hidden" name="returnPage" value="${currentUrl}" />
      <button type="submit" class="remove-btn" aria-label="Remove item">×</button>
    </form>
  </div>
    </c:forEach>
  </c:when>
  
  <c:otherwise>
    <p>Your cart is empty.</p>
  </c:otherwise>
</c:choose>
</div>


        <div class="cart-footer">
            <hr class="divider" />
            <div class="cart-summary">
                <div class="summary-line">
                    <span>Subtotal</span>
                    <span id="subtotal">Rs. <fmt:formatNumber value="${cartTotal}" maxFractionDigits="2" /></span>
                </div>
                <div class="summary-line">
                    <span>Delivery Charge</span>
                    <span>Rs. 100</span>
                </div>
                <div class="total-line">
                    <span>Total</span>
                    <span id="total">Rs. <fmt:formatNumber value="${cartTotal + 100}" maxFractionDigits="2" /></span>
                </div>
                <button class="checkout-btn">Checkout</button>
            </div>
        </div>
    </div>
</main>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const params = new URLSearchParams(window.location.search);
        if (params.get("openCart") === "true") {
            showCartPopup();
        }

        document.querySelector(".checkout-btn").addEventListener("click", function () {
            const itemCount = ${cartItemCount != null ? cartItemCount : 0};
            if (itemCount === 0) {
                alert("Your cart is empty.");
            } else {
                window.location.href = "CheckoutServlet";
            }
        });
    });

    function showCartPopup() {
        document.getElementById("cartPopup").classList.remove("hidden");
        document.body.style.overflow = "hidden";
    }

    function closeCart() {
        document.getElementById("cartPopup").classList.add("hidden");
        document.body.style.overflow = "";
    }
</script>

</body>
</html>
