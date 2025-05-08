<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders - Wearnfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="../components/navbar.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center">My Orders</h2>

    <c:if test="${empty orders}">
        <div class="alert alert-info text-center">
            You have not placed any orders yet. Explore our products and place your first order!
        </div>
    </c:if>

    <div class="row">
        <c:forEach var="order" items="${orders}">
            <div class="col-12 mb-4">
                <div class="card">
                    <div class="card-header">
                        <h5>Order #${order.orderId}</h5>
                        <p class="text-muted mb-1">Placed on: ${order.orderDate}</p>
                        <p class="mb-0 fw-semibold
                            ${order.status == 'Delivered' ? 'text-success' :
                              order.status == 'Cancelled' ? 'text-danger' :
                              'text-warning'}">
                            Status: ${order.status}
                        </p>
                    </div>
                    <div class="card-body">
                        <h6 class="text-primary">Total Price: ₹${order.totalCost}</h6>
                        <ul class="list-group">
                            <c:forEach var="item" items="${order.orderItems}">
                                <li class="list-group-item">
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <img src="${item.productImageUrl}" alt="${item.productName}" class="img-thumbnail" style="max-width: 60px;">
                                            <strong>${item.productName}</strong> - ₹${item.price} x ${item.quantity}
                                        </div>
                                        <span class="badge bg-secondary">₹${item.price * item.quantity}</span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="../components/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
