<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Manage Suppliers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Supplier List</h2>
    <c:if test="${not empty supplierList}">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Supplier ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="supplier" items="${supplierList}">
                    <tr>
                        <td>${supplier.id}</td>
                        <td>${supplier.name}</td>
                        <td>${supplier.email}</td>
                        <td>
                            <form action="DeleteSupplierServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this supplier?');">
                                <input type="hidden" name="supplierId" value="${supplier.id}" />
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty supplierList}">
        <p>No suppliers found.</p>
    </c:if>
</div>
</body>
</html>
