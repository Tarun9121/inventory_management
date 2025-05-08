<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.models.SupplierOrder" %>
<%
    List<SupplierOrder> orders = (List<SupplierOrder>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Supplier Orders</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

<div class="container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-center mb-6 text-gray-800">Your Orders</h1>

    <% if (orders != null && !orders.isEmpty()) { %>
        <div class="overflow-x-auto">
            <table class="min-w-full bg-white rounded-lg shadow-md">
                <thead class="bg-gray-200 text-gray-700">
                <tr>
                    <th class="py-3 px-4 text-left">Order ID</th>
                    <th class="py-3 px-4 text-left">Product</th>
                    <th class="py-3 px-4 text-left">Variant ID</th>
                    <th class="py-3 px-4 text-left">Quantity</th>
                    <th class="py-3 px-4 text-left">Price</th>
                    <th class="py-3 px-4 text-left">Total Amount</th>
                    <th class="py-3 px-4 text-left">Order Date</th>
                    <th class="py-3 px-4 text-left">Status</th>
                    <th class="py-3 px-4 text-left">Action</th>
                </tr>
                </thead>
                <tbody class="text-gray-600">
                <% for (SupplierOrder order : orders) { %>
                    <tr class="border-t">
                        <td class="py-3 px-4"><%= order.getOrderId() %></td>
                        <td class="py-3 px-4"><%= order.getProductName() %></td>
                        <td class="py-3 px-4"><%= order.getVariantId() %></td>
                        <td class="py-3 px-4"><%= order.getQuantity() %></td>
                        <td class="py-3 px-4">₹<%= order.getPrice() %></td>
                        <td class="py-3 px-4">₹<%= order.getTotalAmount() %></td>
                        <td class="py-3 px-4"><%= order.getOrderDate() %></td>
                        <td class="py-3 px-4">
                            <span class="inline-block px-2 py-1 text-sm rounded
                                <%= "Delivered".equals(order.getStatus()) ? "bg-green-200 text-green-800" : "bg-yellow-200 text-yellow-800" %>">
                                <%= order.getStatus() %>
                            </span>
                        </td>
                        <td class="py-3 px-4">
                            <!-- Status Update Form -->
                            <form action="<%= request.getContextPath() %>/UpdateOrderStatusServlet" method="post">
                                <input type="hidden" name="orderId" value="<%= order.getOrderId() %>" />
                                <select name="status" class="px-2 py-1 rounded">
                                    <option value="Pending" <%= "Pending".equals(order.getStatus()) ? "selected" : "" %>>Pending</option>
                                    <option value="Delivered" <%= "Delivered".equals(order.getStatus()) ? "selected" : "" %>>Delivered</option>
                                </select>
                                <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                                    Update Status
                                </button>
                            </form>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    <% } else { %>
        <p class="text-center text-gray-600 mt-8 text-lg">No orders found.</p>
    <% } %>

    <div class="mt-8 text-center">
        <a href="<%= request.getContextPath() %>/supplier-welcome.jsp"
           class="inline-block px-6 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition">
            Back to Dashboard
        </a>
    </div>
</div>

</body>
</html>
