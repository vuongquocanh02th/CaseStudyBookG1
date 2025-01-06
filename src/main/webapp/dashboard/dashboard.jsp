<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main-content {
            background: url('https://s.net.vn/uehy') no-repeat center center;
            background-size: cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .main-content h2 {
            font-size: 2.5rem;
            color: #cdff00;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body class="d-flex min-vh-100">
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- Sidebar -->
        <div class="col-auto col-md-3 col-lg-2 bg-dark text-white d-flex flex-column p-3">
            <h2 class="text-center mb-4">Library Management</h2>
            <ul class="nav nav-pills flex-column mb-auto">
                <%
                    String role = (String) session.getAttribute("role");
                    if ("admin".equals(role)) {
                %>
                <li class="nav-item mb-2">
                    <a href="${pageContext.request.contextPath}/books?action=listBooks" class="nav-link text-white">Manage Books</a>
                </li>
                <li class="nav-item mb-2">
                    <a href="${pageContext.request.contextPath}/categories" class="nav-link text-white">Manage Categories</a>
                </li>
                <li class="nav-item mb-2">
                    <a href="${pageContext.request.contextPath}/customers" class="nav-link text-white">Manage Customers</a>
                </li>
                <li class="nav-item mb-2">
                    <a href="${pageContext.request.contextPath}/borrows?action=list" class="nav-link text-white">Manage Borrows</a>
                </li>
                <% } else if ("user".equals(role)) { %>
                <li class="nav-item mb-2">
                    <a href="${pageContext.request.contextPath}/books" class="nav-link text-white">View Books</a>
                </li>
                <li class="nav-item mb-2">
                    <a href="${pageContext.request.contextPath}/borrows?action=add" class="nav-link text-white">Borrow Books</a>
                </li>
                <% } %>
            </ul>
            <div class="mt-auto">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-danger w-100">Logout</a>
            </div>
        </div>

        <!-- Main Content -->
        <div class="col py-5 main-content">
            <h2>Welcome to Library Management</h2>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
