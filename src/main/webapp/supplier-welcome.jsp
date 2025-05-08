<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    com.example.models.Supplier supplier = (com.example.models.Supplier) session.getAttribute("supplier");
    if (supplier == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String name = supplier.getName();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Supplier Dashboard - Wearfinity</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Urbanist:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('https://images.unsplash.com/photo-1495121605193-b116b5b09c63?auto=format&fit=crop&w=1600&q=80') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Urbanist', sans-serif;
        }

        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            background-color: rgba(255, 255, 255, 0.6);
            backdrop-filter: blur(5px);
        }

        .dashboard-card {
            max-width: 700px;
            margin: 6rem auto;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 16px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.2);
            padding: 2rem;
            animation: fadeIn 1s ease-in;
        }

        .brand-name {
            font-size: 2rem;
            font-weight: bold;
            text-align: center;
            color: #111;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .navbar-custom {
            background-color: rgba(255, 255, 255, 0.9);
            backdrop-filter: blur(5px);
        }
    </style>
</head>
<body>
<div class="overlay">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light navbar-custom shadow-sm">
        <div class="container">
            <a class="navbar-brand fw-bold" href="#">Inventory-Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <span class="nav-link">Hi, <strong><%= name %></strong></span>
                    </li>
                    <li>
                    <a href="view_products" class="btn btn-secondary mb-3">View Your Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet"><i class="bi bi-box-arrow-right"></i> Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Dashboard Content -->
    <div class="container">
        <div class="dashboard-card text-center">
            <h2 class="mb-3">Welcome, <%= name %>!</h2>
            <p class="text-muted mb-4">Manage your products and orders below.</p>

            <div class="row justify-content-center">
                <div class="col-md-5">
                    <a href="<%=request.getContextPath()%>/SupplierOrdersServlet" class="btn btn-primary w-100 mb-3">
                        Manage Orders
                    </a>
                </div>
                <div class="col-md-5">
                    <a href="<%=request.getContextPath()%>/CategoryListServlet" class="btn btn-success w-100 mb-3">
                        Manage Products
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
