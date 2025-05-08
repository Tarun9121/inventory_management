<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.example.models.User" %>
<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>Profile - Wearnfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-card {
            max-width: 600px;
            margin: auto;
        }
        .info-label {
            font-weight: 500;
            color: #555;
        }
        .info-value {
            font-weight: 600;
        }
    </style>
</head>
<body class="bg-light">

<!-- Navbar -->
<jsp:include page="components/navbar.jsp" />

<!-- Profile Card -->
<div class="container mt-5">
    <div class="card shadow profile-card">
        <div class="card-header text-center bg-dark text-white">
            <h4 class="mb-0">User Profile</h4>
        </div>
        <div class="card-body">
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label info-label">Name:</label>
                <div class="col-sm-8 info-value"><%= user.getName() %></div>
            </div>
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label info-label">Email:</label>
                <div class="col-sm-8 info-value"><%= user.getEmail() %></div>
            </div>
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label info-label">Phone:</label>
                <div class="col-sm-8 info-value"><%= user.getPhone() %></div>
            </div>
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label info-label">Address:</label>
                <div class="col-sm-8 info-value">
                    <%
                        if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
                    %>
                        <form action="${pageContext.request.contextPath}/address" method="post" class="d-flex flex-column flex-sm-row gap-2">
                            <input type="text" name="address" class="form-control" placeholder="Enter your address" required />
                            <button type="submit" class="btn btn-success">Save Address</button>
                        </form>
                    <%
                        } else {
                            out.print(user.getAddress());
                        }
                    %>
                </div>
            </div>

            <div class="text-center mt-4">
                <a href="editProfile.jsp" class="btn btn-primary">Edit Profile</a>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="components/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
