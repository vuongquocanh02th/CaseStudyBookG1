<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        .link-box {
            display: flex;
            justify-content: center;
            margin-top: 40px;
        }

        .link-box a {
            margin: 0 20px;
            padding: 15px 30px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            font-size: 18px;
            border-radius: 8px;
            font-weight: 600;
        }

        .link-box a:hover {
            background-color: #0056b3;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #0056b3;
            padding: 20px 50px;
            color: white;
        }

        .navbar h2 {
            margin: 0;
            font-size: 28px;
        }

        .logout a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            background-color: #dc3545;
            padding: 10px 20px;
            border-radius: 5px;
        }

        .logout a:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome to Library Management</h2>
    <div class="navbar">
        <h2>Welcome to Library Management</h2>
        <div class="logout">
            <a href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </div>
    <%
        String role = (String) session.getAttribute("role");
    %>
    <div class="link-box">
        <% if ("admin".equals(role)) { %>
        <a href="${pageContext.request.contextPath}/books?action=listBooks">Manage Books</a>
        <a href="${pageContext.request.contextPath}/categories">Manage Categories</a>
        <a href="${pageContext.request.contextPath}/genres">Manage Genres</a>
        <a href="${pageContext.request.contextPath}/publishers">Manage Publishers</a>
        <a href="${pageContext.request.contextPath}/customers">Manage Customers</a>
        <a href="${pageContext.request.contextPath}/borrow?action=manageBorrows">Manage Borrows</a>
        <% } else if ("user".equals(role)) { %>
        <a href="${pageContext.request.contextPath}/books">View Books</a>
        <a href="${pageContext.request.contextPath}/borrow?action=borrowBooks">Borrow Books</a>
        <% } %>
    </div>
</div>
</body>
</html>
