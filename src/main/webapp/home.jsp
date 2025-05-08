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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Wearnfinity</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-gray-900 text-white p-4 shadow">
    <div class="container mx-auto flex items-center justify-between">
        <a href="#" class="text-xl font-bold">Inventory-Management</a>
        <form class="flex w-full max-w-md ml-4" action="SearchServlet" method="get">
            <input type="text" name="query" placeholder="Search products or categories"
                   class="flex-grow px-4 py-2 rounded-l border border-gray-300 text-black focus:outline-none focus:ring-2 focus:ring-blue-500">
            <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded-r">Search</button>
        </form>

        <ul class="flex space-x-6 text-sm">
            <li><a href="${pageContext.request.contextPath}/home.jsp" class="hover:text-blue-400">Home</a></li>
            <li><a href="Wishlist" class="hover:text-blue-400">Wishlist</a></li>
            <li><a href="Cart" class="hover:text-blue-400">Cart</a></li>
            <li><a href="${pageContext.request.contextPath}/profile.jsp" class="hover:text-blue-400">Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/orders" class="hover:text-blue-400">Orders</a></li>
            <li><a href="LogoutServlet" class="text-red-400 hover:text-red-500">Logout</a></li>
        </ul>
    </div>
</nav>

<!-- Welcome Section -->
<div class="container mx-auto mt-10 text-center">
    <h2 class="text-3xl font-bold mb-2">Welcome to <span class="text-blue-600">Wearnfinity</span></h2>
    <p class="text-lg mb-2">Your one-stop destination for the latest products across various categories.</p>
    <p>Logged in as: <strong><%= user.getEmail() %></strong></p>
</div>

<!-- Carousel Section -->
<div class="container mx-auto mt-10">
    <div class="relative overflow-hidden rounded-lg shadow-lg">
        <div class="flex animate-scroll-x space-x-4">
            <img src="./images/carosel1.jpg" class="w-full h-80 object-cover" alt="Banner 1">
            <img src="./images/carosel2.jpg" class="w-full h-80 object-cover" alt="Banner 2">
            <img src="./images/carosel3.jpg" class="w-full h-80 object-cover" alt="Banner 3">
        </div>
    </div>
</div>

<!-- Promotional Section -->
<div class="container mx-auto mt-10">
    <h3 class="text-2xl font-semibold text-center mb-6">Exclusive Offers</h3>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div class="bg-white rounded shadow p-4">
            <img src="./images/upcoming-product.jpg" class="w-full h-60 object-cover rounded" alt="Upcoming Product">
            <div class="text-center mt-4">
                <h5 class="text-lg font-bold">Coming Soon: New iPhone 14!</h5>
                <p>Pre-order now to get special offers and early delivery.</p>
            </div>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/flash-sale.jpg" class="w-full h-60 object-cover rounded" alt="Flash Sale">
            <div class="text-center mt-4">
                <h5 class="text-lg font-bold">Flash Sale - 50% Off!</h5>
                <p>Hurry! Limited stock available. Don't miss out on huge discounts!</p>
            </div>
        </div>
    </div>
</div>

<!-- Featured Products -->
<div class="container mx-auto mt-10">
    <h3 class="text-2xl font-semibold text-center mb-6">Featured Products</h3>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-6">
        <!-- Repeat for each product -->
        <div class="bg-white rounded shadow p-4">
            <img src="./images/mobile.jpg" class="w-full h-48 object-cover rounded" alt="Mobile">
            <h5 class="text-lg font-bold mt-2">Samsung Galaxy S21</h5>
            <p class="text-sm text-gray-600">120Hz display and 5G support.</p>
            <p class="text-blue-600 font-bold">₹69,999</p>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/laptop.jpg" class="w-full h-48 object-cover rounded" alt="Laptop">
            <h5 class="text-lg font-bold mt-2">Dell XPS 13</h5>
            <p class="text-sm text-gray-600">Compact ultrabook with Intel i7.</p>
            <p class="text-blue-600 font-bold">₹1,29,999</p>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/fashion.jpg" class="w-full h-48 object-cover rounded" alt="Fashion">
            <h5 class="text-lg font-bold mt-2">Men's Casual Shirt</h5>
            <p class="text-sm text-gray-600">Stylish and comfortable wear.</p>
            <p class="text-blue-600 font-bold">₹1,299</p>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/home_appliance.jpg" class="w-full h-48 object-cover rounded" alt="Appliance">
            <h5 class="text-lg font-bold mt-2">Philips Air Fryer</h5>
            <p class="text-sm text-gray-600">Healthy fries with less oil.</p>
            <p class="text-blue-600 font-bold">₹7,999</p>
        </div>
    </div>
</div>

<!-- Best Sellers -->
<div class="container mx-auto mt-10 mb-10">
    <h3 class="text-2xl font-semibold text-center mb-6">Best Sellers</h3>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-6">
        <div class="bg-white rounded shadow p-4">
            <img src="./images/product1.jpg" class="w-full h-48 object-cover rounded" alt="Product 1">
            <h5 class="text-lg font-bold mt-2">Apple iPhone 13</h5>
            <p class="text-sm text-gray-600">A15 Bionic chip & great camera.</p>
            <p class="text-blue-600 font-bold">₹79,999</p>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/product2.jpg" class="w-full h-48 object-cover rounded" alt="Product 2">
            <h5 class="text-lg font-bold mt-2">HP Gaming Laptop</h5>
            <p class="text-sm text-gray-600">GTX 1650 GPU for gaming.</p>
            <p class="text-blue-600 font-bold">₹55,000</p>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/product3.jpg" class="w-full h-48 object-cover rounded" alt="Smartwatch">
            <h5 class="text-lg font-bold mt-2">Fossil Smartwatch</h5>
            <p class="text-sm text-gray-600">Fitness tracking & notifications.</p>
            <p class="text-blue-600 font-bold">₹18,999</p>
        </div>
        <div class="bg-white rounded shadow p-4">
            <img src="./images/product4.jpg" class="w-full h-48 object-cover rounded" alt="Headphones">
            <h5 class="text-lg font-bold mt-2">Sony Bluetooth Headphones</h5>
            <p class="text-sm text-gray-600">Noise-cancelling and clear audio.</p>
            <p class="text-blue-600 font-bold">₹9,999</p>
        </div>
    </div>
</div>

</body>
</html>