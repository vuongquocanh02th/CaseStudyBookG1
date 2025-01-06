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
        /* Basic Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            min-height: 100vh;
        }

        .container {
            display: flex;
            width: 100%;
        }

        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            padding: 20px;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .sidebar h2 {
            margin-bottom: 20px;
            font-size: 1.5rem;
        }

        .sidebar a {
            color: white;
            text-decoration: none;
            font-size: 1rem;
            margin: 10px 0;
            padding: 10px 20px;
            width: 100%;
            text-align: center;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .sidebar a:hover {
            background-color: #3498db;
        }

        .main-content {
            flex: 1;
            background: url('https://s.net.vn/uehy') no-repeat center center;
            background-size: cover;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .main-content h2 {
            font-size: 2.5rem;
            color: #cdff00;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .logout {
            margin-top: auto;
        }

        .logout a {
            color: white;
            text-decoration: none;
            font-size: 1rem;
            background-color: #e74c3c;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .logout a:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="sidebar">
        <h2>Library Management</h2>
        <%
            String role = (String) session.getAttribute("role");
            if ("admin".equals(role)) {
        %>
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
        <div class="logout">
            <a href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </div>
    <div class="main-content">
        <h2>Welcome to Library Management</h2>
    </div>
</div>
</body>
</html>