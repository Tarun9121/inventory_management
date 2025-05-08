<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout - Place Your Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .checkout-container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-top: 40px;
        }
        .address-box {
            border: 1px solid #dee2e6;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .order-summary {
            border: 1px solid #dee2e6;
            padding: 20px;
            border-radius: 8px;
        }
        .edit-btn {
            float: right;
            font-size: 14px;
        }
    </style>
</head>
<body>

<jsp:include page="../components/navbar.jsp" />

<div class="container">
    <div class="checkout-container">
        <h2 class="mb-4">Checkout</h2>

        <!-- Address Section -->
        <div class="address-box mb-4">
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h5>Delivery Address</h5>
                <a href="${pageContext.request.contextPath}/profile.jsp" class="btn btn-outline-primary btn-sm edit-btn">Edit Address</a>
            </div>

            <c:choose>
                <c:when test="${not empty address}">
                    <p>${address}</p>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning">
                        No address found. Please <a href="${pageContext.request.contextPath}/profile.jsp">add your address</a> before placing an order.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Cart Products Section -->
        <div class="order-summary mb-4">
            <h5 class="mb-3">Order Summary</h5>
            <c:forEach var="product" items="${cartProducts}">
                <div class="d-flex align-items-center mb-3">
                    <img src="${product.imageUrl}" alt="${product.name}" class="img-thumbnail me-3" style="width: 80px; height: 80px; object-fit: contain;">
                    <div>
                        <h6 class="mb-1">${product.name}</h6>
                        <p class="mb-0 text-muted">₹${product.price}</p>
                        <small>Quantity: ${product.quantity}</small><br>
                    </div>
                </div>
                <hr>
            </c:forEach>

            <!-- Total Price -->
            <div class="d-flex justify-content-between">
                <h5>Total Price:</h5>
                <h5>₹${totalPrice}</h5>
            </div>
        </div>

        <!-- Place Order Button -->
        <form action="orders" method="POST">
            <input type="hidden" name="totalPrice" value="${totalPrice}" />
            <button type="submit" class="btn btn-success btn-lg w-100">Proceed to Payment</button>
        </form>

    </div>
</div>

<jsp:include page="../components/footer.jsp" />

</body>
</html>
