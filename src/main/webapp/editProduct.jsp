<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.models.Product" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.dao.JDBCConnection" %>

<html>
<head>
    <title>Edit Product - Wearfinity Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="supplier_dashboard.jsp">Wearfinity Supplier</a>
        <div class="d-flex">
            <a href="<%=request.getContextPath()%>/SupplierLogoutServlet" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Edit Product</h2>

    <%
        int productId = Integer.parseInt(request.getParameter("productId"));
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        Map<Integer, String> categoryMap = new HashMap<>();

        try {
            JDBCConnection jdbc = new JDBCConnection();
            connection = jdbc.connect();

            // Load categories
            String catSql = "SELECT category_id, category_name FROM categories";
            ps = connection.prepareStatement(catSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoryMap.put(rs.getInt("category_id"), rs.getString("category_name"));
            }
            rs.close();
            ps.close();

            // Load product
            String sql = "SELECT * FROM products WHERE product_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>

    <% if (product != null) { %>
        <form action="UpdateProductServlet" method="post">
            <input type="hidden" name="productId" value="<%= product.getProductId() %>"/>

            <div class="mb-3">
                <label for="name" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= product.getName() %>" required/>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required><%= product.getDescription() %></textarea>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Price (₹)</label>
                <input type="number" class="form-control" id="price" name="price" step="0.01" value="<%= product.getPrice() %>" required/>
            </div>

            <div class="mb-3">
                <label for="quantity" class="form-label">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" value="<%= product.getQuantity() %>" required/>
            </div>

            <div class="mb-3">
                <label for="categoryId" class="form-label">Category</label>
                <select class="form-control" id="categoryId" name="categoryId" required>
                    <% for (Map.Entry<Integer, String> entry : categoryMap.entrySet()) { %>
                        <option value="<%= entry.getKey() %>" <%= (entry.getKey() == product.getCategoryId()) ? "selected" : "" %>>
                            <%= entry.getValue() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="mb-3">
                <label for="imageUrl" class="form-label">Image URL</label>
                <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="<%= product.getImageUrl() %>" required/>
            </div>

            <button type="submit" class="btn btn-primary">Update Product</button>
        </form>
    <% } else { %>
        <div class="alert alert-danger">
            Product not found or an error occurred while fetching product details.
        </div>
    <% } %>
</div>

</body>
</html>
