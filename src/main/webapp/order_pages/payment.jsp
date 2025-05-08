<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment - Complete Your Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .payment-container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            margin-top: 50px;
            text-align: center;
        }
        .qr-code {
            width: 250px;
            height: 250px;
            margin: 20px auto;
        }
        .amount-due {
            font-size: 24px;
            font-weight: bold;
            margin-top: 20px;
        }
        .paid-btn {
            margin-top: 30px;
        }
    </style>
</head>
<body>

<jsp:include page="../components/navbar.jsp" />

<div class="container">
    <div class="payment-container">
        <h2 class="mb-4">Complete Your Payment</h2>

        <!-- QR Code -->
        <div class="qr-code">
            <!-- Display the QR code by setting the image source dynamically using JSTL -->
            <img src="https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=upi://pay?pa=example@upi&pn=YourStoreName&am=${totalPrice}" alt="Payment QR Code" class="img-fluid">
        </div>

        <!-- Amount Due -->
        <div class="amount-due">
            Amount to Pay: ₹${totalPrice}
        </div>

        <!-- Paid Button -->
        <form action="confirmPayment" method="POST">
            <button type="submit" class="btn btn-success btn-lg paid-btn">I Paid</button>
        </form>

    </div>
</div>

<jsp:include page="../components/footer.jsp" />

</body>
</html>
