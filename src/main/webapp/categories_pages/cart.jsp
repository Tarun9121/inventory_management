<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .cart-item {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.05);
        }
        .product-image {
            max-height: 150px;
            object-fit: contain;
        }
    </style>
</head>
<body>

<jsp:include page="../components/navbar.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Your Shopping Cart</h2>

    <!-- Cart Message (Optional) -->
    <c:if test="${not empty sessionScope.cartMessage}">
        <div class="alert alert-info text-center">
            ${sessionScope.cartMessage}
        </div>
        <c:remove var="cartMessage" scope="session"/>
    </c:if>

    <c:choose>
        <c:when test="${empty cartProducts}">
            <div class="alert alert-warning text-center">
                Your cart is empty. <a href="home.jsp" class="alert-link">Start shopping</a>!
            </div>
        </c:when>

        <c:otherwise>
            <!-- Total Price and Checkout Button -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h4 class="text-primary">Total Price: ₹${totalPrice}</h4>
                <a href="checkout" class="btn btn-success btn-lg">Proceed to Checkout</a>
            </div>

            <div class="row">
                <c:forEach var="product" items="${cartProducts}">
                    <div class="col-md-12 cart-item d-flex flex-column flex-md-row align-items-md-center">
                        <!-- Product Image -->
                        <div class="me-md-4 mb-3 mb-md-0 text-center">
                            <img src="${product.imageUrl}" alt="${product.name}" class="img-fluid product-image" />
                        </div>

                        <!-- Product Information -->
                        <div class="flex-grow-1">
                            <h5>${product.name}</h5>
                            <p class="mb-1">${product.description}</p>
                            <p class="text-muted mb-2">₹${product.price}</p>

                            <!-- Product Quantity -->
                            <p><strong>Quantity:</strong> ${product.quantity}</p>

                            <!-- Action Buttons -->
                            <div class="d-flex flex-wrap align-items-center">
                                <!-- Decrease Quantity Form -->
                                <form action="Cart" method="POST" class="d-inline me-2">
                                    <input type="hidden" name="_method" value="PUT">
                                    <input type="hidden" name="action" value="decrease">
                                    <input type="hidden" name="productId" value="${product.productId}">
                                    <button type="submit" class="btn btn-warning btn-sm">-</button>
                                </form>

                                <!-- Increase Quantity Form -->
                                <form action="Cart" method="POST" class="d-inline me-2">
                                    <input type="hidden" name="_method" value="PUT">
                                    <input type="hidden" name="action" value="increase">
                                    <input type="hidden" name="productId" value="${product.productId}">
                                    <button type="submit" class="btn btn-primary btn-sm">+</button>
                                </form>

                                <!-- Remove Item Form -->
                                <form action="Cart" method="POST" class="d-inline">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="productId" value="${product.productId}">
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="../components/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
