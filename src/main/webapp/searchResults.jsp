<%@ page import="java.util.List" %>
<%@ page import="com.example.models.Product" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    String keyword = (String) request.getAttribute("keyword");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results - Wearnfinity</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 min-h-screen">

    <!-- Custom Tailwind Navbar -->
    <nav class="bg-blue-700 text-white shadow">
        <div class="max-w-7xl mx-auto px-4 py-3 flex items-center justify-between">
            <a href="home.jsp" class="text-2xl font-bold">Wearnfinity</a>
            <form class="flex w-full max-w-md ml-4" action="SearchServlet" method="get">
                <input type="text" name="query" placeholder="Search products or categories"
                       class="flex-grow px-4 py-2 rounded-l border-none focus:outline-none text-black"
                       required>
                <button type="submit" class="bg-yellow-500 hover:bg-yellow-600 px-4 py-2 rounded-r text-black font-semibold">
                    Search
                </button>
            </form>
            <div class="hidden md:flex gap-4 ml-auto">
                <a href="Wishlist" class="hover:underline">Wishlist</a>
                <a href="Cart" class="hover:underline">Cart</a>
                <a href="profile.jsp" class="hover:underline">Profile</a>
                <a href="orders" class="hover:underline">Orders</a>
                <a href="LogoutServlet" class="text-red-300 hover:text-red-500 font-semibold">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Search Results Header -->
    <div class="max-w-7xl mx-auto px-4 mt-6">
        <h2 class="text-2xl font-bold mb-4">
            Search Results for: <span class="text-blue-600">"<%= keyword %>"</span>
        </h2>

        <!-- Product Grid -->
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            <% if (products != null && !products.isEmpty()) {
                for (Product product : products) { %>
                    <a href="ProductDetailServlet?productId=<%= product.getProductId() %>"
                       class="bg-white p-4 rounded-lg shadow hover:shadow-lg transition duration-300 flex flex-col text-inherit no-underline">
                        <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>"
                             class="w-full h-48 object-cover rounded mb-3">
                        <div class="flex-grow">
                            <h3 class="text-lg font-semibold mb-1"><%= product.getName() %></h3>
                            <p class="text-sm text-gray-500 mb-1"><%= product.getCategoryName() %></p>
                            <p class="text-sm text-gray-700 overflow-hidden truncate" style="max-height: 3.5em;"><%= product.getDescription() %></p>
                        </div>
                        <p class="text-lg font-bold text-green-600 mt-3">₹<%= product.getPrice() %></p>
                    </a>
            <%  }
            } else { %>
                <p class="text-red-600 text-lg col-span-full">No products found for "<%= keyword %>".</p>
            <% } %>
        </div>
    </div>

</body>
</html>
