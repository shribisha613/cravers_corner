<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MyOrders.css">
</head>
<body>
<jsp:include page="Header.jsp" />

<main>
    <div class="tabs">
        <a href="${pageContext.request.contextPath}/UserProfileServlet">Profile</a>
        <a href="#" class="active">My Orders</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="error-alert">
            <c:out value="${errorMessage}"/>
            <button class="close-btn" onclick="this.parentElement.style.display='none'">x</button>
        </div>
    </c:if>
    
    <c:if test="${empty orders}">
	    <div class="info-alert">You have not placed any orders yet.</div>
	</c:if>
    

    <div class="content-box" id="ordersSection">
        <c:forEach var="order" items="${orders}">
        
             <div class = "order-box"> 
                <div class="order-Id">ORDER ID: <c:out value="${order.orderId}" /></div>
                
                <div class="order-date">Date: <c:out value="${order.orderDate}" /></div>

                <div class="order-table">
                    <div class="table-header">
                        <div>Item</div>
                        <div>Price</div>
                        <div>Quantity</div>
                        <div>Total</div>
                    </div>

                    <c:forEach var="item" items="${order.items}">
                        <div class="table-row">
                            <div><c:out value="${item.food_name}" /></div>
                            <div>Rs. <c:out value="${item.price}" /></div>
                            <div><c:out value="${item.quantity}"/></div>
                            <div>Rs. <c:out value="${item.subtotal}" /></div>
                        </div>
                    </c:forEach>
                </div>

                <div class="order-summary">
                    <div>Status: <c:out value="${order.status}" /></div>
                    <div>Total: Rs. <c:out value="${order.totalAmount}" /></div>
                </div>

                <hr />
            </div>
        </c:forEach>
    </div>
</main>

</body>
</html>
