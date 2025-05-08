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
    <title>Edit Profile - Wearfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .edit-card {
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>
<body class="bg-light">

<!-- Navbar -->
<jsp:include page="components/navbar.jsp" />

<!-- Edit Profile Form -->
<div class="container mt-5">
    <div class="card shadow edit-card">
        <div class="card-header text-center bg-primary text-white">
            <h4 class="mb-0">Edit Profile</h4>
        </div>
        <div class="card-body">
            <form action="profile" method="post">
                <div class="mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" name="name" class="form-control" value="<%= user.getName() %>" required />
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" value="<%= user.getEmail() %>" required />
                </div>
                <div class="mb-3">
                    <label class="form-label">Phone</label>
                    <input type="text" name="phone" class="form-control" value="<%= user.getPhone() %>" required />
                </div>
                <div class="mb-3">
                    <label class="form-label">Address</label>
                    <input type="text" name="address" class="form-control" value="<%= user.getAddress() != null ? user.getAddress() : "" %>" />
                </div>

                <div class="d-flex justify-content-between">
                    <a href="profile.jsp" class="btn btn-secondary">Cancel</a>
                    <button type="submit" class="btn btn-success">Update Profile</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="components/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
