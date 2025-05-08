<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.models.Product" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category Products</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="../components/navbar.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center">Products</h2>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
        <c:choose>
            <c:when test="${not empty products}">
                <c:forEach var="product" items="${products}">
                    <div class="col">
                        <!-- Wrap the card in an anchor tag to make it clickable -->
                        <a href="ProductDetailServlet?productId=${product.productId}">
                            <div class="card h-100 shadow-sm">
                                <img src="${product.imageUrl}" class="card-img-top" alt="${product.name}" style="height: 300px; object-fit: cover;">
                                <div class="card-body">
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">${product.description}</p>
                                    <p class="card-text fw-bold text-primary">₹${product.price}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-12">
                    <div class="alert alert-warning text-center">No products found in this category.</div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<jsp:include page="../components/footer.jsp" />

</body>
</html>
