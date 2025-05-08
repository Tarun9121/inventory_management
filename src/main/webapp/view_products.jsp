<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.models.Product" %>

<html>
<head>
    <title>Your Products - Wearfinity Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="supplier_dashboard.jsp">Wearfinity Supplier</a>
        <div class="d-flex">
            <a href="<%=request.getContextPath()%>/SupplierLogoutServlet" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Your Products</h2>

    <c:if test="${not empty productList}">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>₹${product.price}</td>
                        <td>${product.categoryName}</td>
                        <td>${product.quantity}</td>
                        <td>
                            <!-- Only the Edit Button is available, no delete button -->
                            <a href="editProduct.jsp?productId=${product.productId}" class="btn btn-primary btn-sm">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${param.success == '1'}">
        <div class="alert alert-success">Product updated successfully!</div>
    </c:if>

    <c:if test="${param.error == '1'}">
        <div class="alert alert-danger">Error updating product. Please try again!</div>
    </c:if>

    <c:if test="${empty productList}">
        <p>You haven't added any products yet.</p>
    </c:if>

</div>

</body>
</html>
