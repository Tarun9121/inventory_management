<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if (session.getAttribute("adminObj") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard - Wearfinity</title>
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
    <h2 class="mb-4">Welcome, Admin!</h2>

    <div class="row">
        <div class="col-md-4">
            <a href="<%=request.getContextPath()%>/AdminManageOrdersServlet" class="btn btn-primary w-100 mb-3">Manage Users</a>
        </div>
        <div class="col-md-4">
            <a href="<%=request.getContextPath()%>/SupplierListServlet" class="btn btn-success w-100 mb-3">
                Manage Suppliers
            </a>

        </div>
       <div class="col-md-4">
           <a href="<%=request.getContextPath()%>/SalesAndReportsServlet" class="btn btn-warning w-100 mb-3">Sales & Reports</a>
       </div>

</div>

</body>
</html>
