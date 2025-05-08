<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login - Wearfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Urbanist:wght@400;600&display=swap" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('https://images.unsplash.com/photo-1521336575822-6da63c094f3a?auto=format&fit=crop&w=1600&q=80') no-repeat center center fixed;
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

        .login-card {
            max-width: 400px;
            margin: 6rem auto;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 16px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.2);
            padding: 2rem;
            animation: fadeIn 1s ease-in;
        }

        .login-card h2 {
            font-weight: 600;
            color: #222;
        }

        .login-card a {
            color: #007bff;
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
    </style>
    <script>
        function validateLogin() {
            let email = document.getElementById('email').value;
            let password = document.getElementById('password').value;

            if (email === "" || password === "") {
                alert("Please fill in both email and password.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="overlay">
    <div class="container">
        <div class="login-card">
            <div class="brand-name mb-3">Wearfinity</div>
            <h2 class="text-center mb-4">Login</h2>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert">
                    ${errorMessage}
                </div>
            </c:if>

            <form action="LoginServlet" method="post" onsubmit="return validateLogin()">
                <div class="mb-3">
                    <label for="email" class="form-label">Email Address</label>
                    <input type="email" name="email" id="email" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <div class="input-group">
                        <input type="password" name="password" id="password" class="form-control" required/>
                        <span class="input-group-text bg-white">
                            <i class="bi bi-eye-slash" id="togglePassword" style="cursor: pointer;"></i>
                        </span>
                    </div>
                </div>

                <a href="supplier-login.jsp">supplier</a>
                <button type="submit" class="btn btn-dark w-100">Login</button>
            </form>

           <div class="mt-3 text-center">
               <p>Don't have an account? <a href="index.jsp">Register here</a></p>
               <p><a href="admin_pages/login.jsp" class="text-decoration-none fw-bold text-dark">Login as Admin</a></p>
           </div>
        </div>
    </div>
</div>

</body>
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

</html>
