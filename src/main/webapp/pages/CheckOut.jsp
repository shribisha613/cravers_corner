<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Check Out</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="../css/CheckOut.css">
</head>
<body>
<jsp:include page="Header.jsp" />
     <div class="container">
        <div class="top">
            <section class="back-icon">
                <button type="button" class="back-btn">
                    <i class="fas fa-arrow-left"></i>
                </button>
            </section>
            <section class="label">
                <h2>Check Out</h2>
            </section>
        </div>
        <div class="main">
            <section class="form">
                <form>
                    <!-- General Information Section -->
                    <div class="form-section">
                        <h3>1. General Information</h3>
                        <div class="form-group">
                            <label for="firstName">Username</label>
                            <input type="text" id="firstName" name="firstName" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="tel" id="phone" name="phone" required>
                        </div>
                        <div class="form-group">
                            <label for="orderNote">Order Note</label>
                            <textarea id="orderNote" name="orderNote" rows="4"></textarea>
                        </div>
                    </div>

                    <!-- Delivery Address Section -->
                    <div class="form-section">
                        <h3>2. Delivery Address</h3>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" id="address" name="address" required>
                        </div>
                        <div class="form-group address-group">
                            <label for="changeAddress">Change Address</label>
                            <div class="input-with-icon">
                                <input type="text" id="changeAddress" name="changeAddress" required>
                                <button type="button" class="plus-btn" title="Change Address">
                                    <i class="fas fa-plus"></i>
                                </button>
                            </div>
                        </div>
                        
                    </div>
                </form>
            </section>

            <section class="order-summary">
                <div class="summary-body">
                    <hr>
                    <div class="food-item">
                        <img src="../images/pizza.jpg" alt="Food Picture" width="100" height="100">
                        <div class="food-details">
                            <span class="food-name">Food name</span>
                            <span class="food-price">Rs. 350 X 1</span>
                        </div>
                    </div>
                    <hr>
                </div>

                <div class="summary-footer">
                    <div class="price-row">
                        <span>Sub-total</span>
                        <span>Rs. 350</span>
                    </div>
                    <div class="price-row">
                        <span>Delivery charge</span>
                        <span>Rs. 50</span>
                    </div>
                    <div class="total-row">
                        <span>Total</span>
                        <span>Rs. 400</span>
                    </div>
                    <button class="place-order-btn">Place Order</button>
                </div>
            </section>
        </div>
    </div>
<jsp:include page="Footer.jsp" />
</body>
</html>