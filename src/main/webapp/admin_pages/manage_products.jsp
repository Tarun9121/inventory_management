<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Integer supplierId = null;
    if (session.getAttribute("supplierId") != null) {
        supplierId = (Integer) session.getAttribute("supplierId");
    } else if (session.getAttribute("adminId") != null) {
        supplierId = (Integer) session.getAttribute("adminId");
    } else {
        response.sendRedirect("login.jsp"); // Not logged in
        return;
    }
%>

<html>
<head>
    <title>Manage Products - Wearfinity Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="admin_dashboard.jsp">Wearfinity Admin</a>
        <div class="d-flex">
            <a href="<%=request.getContextPath()%>/AdminLogoutServlet" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Add New Product</h2>

    <form action="<%=request.getContextPath()%>/AddProductServlet" method="post">
        <input type="hidden" name="supplierId" value="<%=supplierId%>"/>

        <div class="mb-3">
            <label for="name" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="name" name="name" required/>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price (₹)</label>
            <input type="number" class="form-control" id="price" name="price" step="0.01" required/>
        </div>

        <div class="mb-3">
            <label for="categoryId" class="form-label">Select Category</label>
            <select class="form-control" id="categoryId" name="categoryId" required>
                <option value="">-- Select Category --</option>
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="imageUrl" class="form-label">Image URL</label>
            <input type="text" class="form-control" id="imageUrl" name="imageUrl" required/>
        </div>

        <!-- New Quantity Field -->
        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required/>
        </div>

        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>

    <c:if test="${param.success == '1'}">
        <div class="alert alert-success mt-3">Product added successfully!</div>
    </c:if>

    <c:if test="${param.error == '1'}">
        <div class="alert alert-danger mt-3">Error adding product. Try again!</div>
    </c:if>
</div>

</body>
</html>
