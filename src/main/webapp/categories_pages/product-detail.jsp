<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.name}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .variant-form button {
            min-width: 120px;
        }
        @media (max-width: 576px) {
            .product-image {
                max-height: 300px;
            }
        }
    </style>
</head>
<body>

<jsp:include page="../components/navbar.jsp" />

<div class="container mt-5">
    <div class="row flex-column-reverse flex-md-row">
        <!-- Product Details -->
        <div class="col-md-6">
            <h2 class="mb-3">${product.name}</h2>
            <h4 class="text-primary mb-3">₹${product.price}</h4>
            <p class="mb-4">${product.description}</p>

            <!-- Stock Status and Add to Cart -->
            <c:choose>
                <c:when test="${product.quantity == 0}">
                    <div class="alert alert-danger">❌ Stock not available</div>
                </c:when>
                <c:otherwise>
                    <!-- Add to Cart Button -->
                    <div class="mb-3">
                        <form action="Cart" method="post">
                            <input type="hidden" name="productId" value="${product.productId}" />
                            <button type="submit" class="btn btn-success">
                                🛒 Add to Cart
                            </button>
                        </form>
                    </div>

                    <!-- Show low stock warning if < 5 -->
                    <c:if test="${product.quantity < 5}">
                        <div class="text-warning small mb-3">⚠️ Only ${product.quantity} left in stock!</div>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <!-- Add to Wishlist -->
            <div class="mb-4">
                <form action="Wishlist" method="post">
                    <input type="hidden" name="productId" value="${product.productId}" />
                    <button type="submit" class="btn btn-outline-danger">
                        ❤️ Add to Wishlist
                    </button>
                </form>
            </div>

        </div>

        <!-- Product Image -->
        <div class="col-md-6 text-center mb-4 mb-md-0">
            <img src="${product.imageUrl}" alt="${product.name}" class="img-fluid rounded product-image" style="max-height: 500px; object-fit: contain;">
        </div>
    </div>
</div>

<!-- Toast Notification -->
<c:if test="${not empty sessionScope.cartMessage}">
    <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 1055;">
        <div class="toast show align-items-center text-white bg-${sessionScope.cartMessage.contains('successfully') ? 'success' : 'danger'} border-0">
            <div class="d-flex">
                <div class="toast-body">
                    ${sessionScope.cartMessage}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        setTimeout(() => {
            const toastEl = document.querySelector('.toast');
            if (toastEl) new bootstrap.Toast(toastEl).show();
        }, 100);
    </script>

    <c:remove var="cartMessage" scope="session" />
</c:if>

<jsp:include page="../components/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
