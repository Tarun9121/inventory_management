<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirming Payment...</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        setTimeout(function() {
            window.location.href = "${pageContext.request.contextPath}/order_pages/orderSuccess.jsp";
        }, 5000); // Wait for 5 seconds then redirect
    </script>
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            height: 100vh;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="spinner-border text-success mb-4" style="width: 4rem; height: 4rem;" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>

    <h4 class="mb-3">Please wait while we confirm your payment</h4>
    <p class="text-muted">Do not refresh or press back. This may take a few seconds.</p>

</body>
</html>
