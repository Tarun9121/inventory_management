<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Login - Wearfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Urbanist:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),
                        url('https://images.unsplash.com/photo-1607082350920-71c77b532d87?auto=format&fit=crop&w=1600&q=80') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Urbanist', sans-serif;
            height: 100vh;
            overflow: hidden;
        }

        .overlay {
            height: 100%;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.4);
            padding: 3rem;
            width: 400px;
            animation: slideUp 1s ease-out;
        }

        .login-card h2 {
            font-weight: 700;
            color: #222;
        }

        .login-card small {
            color: #777;
        }

        .brand-name {
            font-size: 2.5rem;
            font-weight: bold;
            color: #000;
            text-align: center;
            margin-bottom: 1rem;
            letter-spacing: 1px;
        }

        @keyframes slideUp {
            from { transform: translateY(50px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        .btn-admin {
            background: #000;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-admin:hover {
            background: #222;
            transform: translateY(-2px);
        }

    </style>

    <script>
        function validateAdminLogin() {
            let email = document.getElementById('email').value.trim();
            let password = document.getElementById('password').value.trim();
            if (email === "" || password === "") {
                alert("Please enter both email and password.");
                return false;
            }
            return true;
        }
    </script>

</head>

<body>

<div class="overlay">
    <div class="login-card">

        <div class="brand-name">Wearfinity Admin</div>

        <h2 class="text-center mb-3">Admin Login</h2>
        <small class="d-block text-center mb-4">Authorized personnel only</small>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
        </c:if>

        <form action="<%=request.getContextPath()%>/AdminLoginServlet" method="post" onsubmit="return validateAdminLogin()">
            <div class="mb-3">
                <label for="email" class="form-label">Admin Email</label>
                <input type="email" name="email" id="email" class="form-control" placeholder="admin@example.com" required/>
            </div>

            <div class="mb-4">
                <label for="password" class="form-label">Password</label>
                <div class="input-group">
                    <input type="password" name="password" id="password" class="form-control" placeholder="********" required/>
                    <span class="input-group-text bg-white">
                        <i class="bi bi-eye-slash" id="togglePassword" style="cursor: pointer;"></i>
                    </span>
                </div>
            </div>

            <button type="submit" class="btn btn-admin w-100 mb-3">Login</button>
        </form>

        <div class="text-center">
            <a href="../index.jsp" class="text-decoration-none">Back to Home</a>
        </div>

    </div>
</div>

<script>
    const togglePassword = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('password');

    togglePassword.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        this.classList.toggle('bi-eye');
        this.classList.toggle('bi-eye-slash');
    });
</script>

</body>
</html>
