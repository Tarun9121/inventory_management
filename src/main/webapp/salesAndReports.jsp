<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Sales & Reports - Wearfinity Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Wearfinity Admin</a>
        <div class="d-flex">
            <a href="<%=request.getContextPath()%>/AdminLogoutServlet" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Sales & Reports</h2>

    <!-- Total Suppliers -->
    <div class="mb-4">
        <h4>Total Suppliers</h4>
        <p>${totalSuppliers}</p>
    </div>

    <!-- Total Orders -->
    <div class="mb-4">
        <h4>Total Orders</h4>
        <p>${totalOrders}</p>
    </div>

    <!-- Orders by Status -->
    <div class="mb-4">
        <h4>Orders by Status</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Status</th>
                    <th>Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${orderStatusCount}">
                    <tr>
                        <td>${entry.key}</td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Orders by Supplier -->
    <div class="mb-4">
        <h4>Orders by Supplier</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Supplier ID</th>
                    <th>Supplier Name</th>
                    <th>Order Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${supplierOrdersCount}">
                    <tr>
                        <!-- Display Supplier ID -->
                        <td>${entry.key}</td>
                        <!-- Display Supplier Name -->
                        <td>${entry.value.supplierName}</td>
                        <!-- Display Order Count -->
                        <td>${entry.value.orderCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
