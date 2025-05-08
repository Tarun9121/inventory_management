<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Wishlist - Wearnfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .wishlist-item {
            background: #fff;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
        }
        .wishlist-item img {
            max-height: 150px;
            object-fit: contain;
        }
    </style>
</head>
<body class="bg-light">

<jsp:include page="components/navbar.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center">My Wishlist</h2>

    <c:if test="${empty wishlistItems}">
        <div class="alert alert-info text-center">
            Your wishlist is empty. Explore products and add your favorites!
        </div>
    </c:if>

    <div class="row">
        <c:forEach var="product" items="${wishlistItems}">
            <div class="col-12">
                <div class="wishlist-item d-flex flex-column flex-md-row align-items-center">

                    <!-- Product Image -->
                    <div class="text-center me-md-4 mb-3 mb-md-0">
                        <img src="${product.imageUrl}" alt="${product.name}" class="img-fluid rounded">
                    </div>

                    <!-- Product Info -->
                    <div class="flex-grow-1">
                        <h5>${product.name}</h5>
                        <p class="text-muted mb-1">${product.description}</p>
                        <h6 class="text-primary mb-3">₹${product.price}</h6>

                        <div class="d-flex gap-2">
                            <form action="Wishlist" method="post">
                                <input type="hidden" name="productId" value="${product.productId}" />
                                <input type="hidden" name="action" value="remove" />
                                <button type="submit" class="btn btn-outline-danger">
                                    🗑️ Remove
                                </button>
                            </form>
                            <a href="ProductDetail?productId=${product.productId}" class="btn btn-outline-primary">
                                View Product
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="components/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
