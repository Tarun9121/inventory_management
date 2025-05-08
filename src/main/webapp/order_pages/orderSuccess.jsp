<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Successful!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9f7ef;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
            flex-direction: column;
        }
        .success-icon {
            font-size: 80px;
            color: #28a745;
        }
    </style>
</head>
<body>

    <div class="success-icon mb-4">
        ✅
    </div>
    <h2>Order Confirmed!</h2>
    <p class="text-muted">Thank you for shopping with us.</p>
    <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-success mt-3">Continue Shopping</a>

</body>
</html>
