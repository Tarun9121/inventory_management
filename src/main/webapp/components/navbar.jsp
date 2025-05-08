<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">Wearnfinity</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/home.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Wishlist">Wishlist</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Cart">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile.jsp">Profile</a>
                </li>
                <!-- Added Orders Link -->
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="LogoutServlet">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
