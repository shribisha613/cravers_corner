<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart - Cravers Corner</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddToCart.css" />
</head>
<body>

<main>
    <div id="cartPopup" class="cart-popup" style="display: none;">
        <div class="cart-header">
            <h2>Your Cart</h2>
            <span class="close-btn" onclick="closeCart()">&times;</span>
        </div>
        <hr class="divider" />

        <div id="cartItems" class="cart-items"></div>

        <hr class="divider" />

        <div class="cart-summary">
            <div class="summary-line">
                <span>Subtotal</span>
                <span id="subtotal">Rs. 0</span>
            </div>
            <div class="summary-line">
                <span>Delivery Charge</span>
                <span>Rs. 100</span>
            </div>
            <div class="summary-line total-line">
                <strong>Total</strong>
                <strong id="totalWithDelivery">Rs. 100</strong>
            </div>
            <button class="checkout-btn">PlaceOrder</button>
        </div>
    </div>
</main>

<script>
    let cart = [];
    const deliveryCharge = 100;

    function autoAddToCartFromURL() {
        const params = new URLSearchParams(window.location.search);
        const foodId = params.get("foodId");
        const quantity = parseInt(params.get("quantity")) || 1;

        if (foodId && foodList[foodId]) {
            const item = foodList[foodId];
            const existing = cart.find(f => f.name === item.name);
            existing ? existing.quantity += quantity : cart.push({ name: item.name, price: item.price, quantity });
            updateCart();
        }

        if (params.get("openCart") === "true") {
            showCartPopup();
        }
    }

    function updateCart() {
        const cartItems = document.getElementById("cartItems");
        const subtotalElem = document.getElementById("subtotal");
        const totalElem = document.getElementById("totalWithDelivery");

        cartItems.innerHTML = cart.map(item => {
            const itemTotal = item.price * item.quantity;
            return `<div class="cart-item">
                        <span class="item-name">${item.name} x ${item.quantity}</span>
                        <span class="item-price">Rs. ${itemTotal}</span>
                    </div>`;
        }).join('');

        const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
        subtotalElem.textContent = `Rs. ${subtotal}`;
        totalElem.textContent = `Rs. ${subtotal + deliveryCharge}`;
    }

    function showCartPopup() {
        document.getElementById("cartPopup").style.display = "block";
        isCartOpen = true;
    }

    function closeCart() {
        document.getElementById('cartPopup').style.display = 'none';
    }

    document.addEventListener("DOMContentLoaded", function () {
        autoAddToCartFromURL();

        document.querySelector(".checkout-btn").addEventListener("click", function () {
            alert(cart.length === 0 ? "Your cart is empty." : "PlaceOrder Successfully !!!");
        });
    });
</script>