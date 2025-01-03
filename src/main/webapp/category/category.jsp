<%@ page import="model.Categories" %>
<%@ page import="java.util.List" %>
<%
    List<Categories> categories = (List<Categories>) request.getAttribute("categories");
%>
<html>
<head>
    <title>Categories List</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 80%;
            max-width: 1200px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 2.5em;
        }

        a.button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            margin: 20px 0;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        a.button:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 15px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .action-links a {
            margin: 0 10px;
            color: #007BFF;
            text-decoration: none;
            font-weight: 500;
        }

        .action-links a:hover {
            text-decoration: underline;
            color: #0056b3;
        }

        .delete-link {
            color: #dc3545;
        }

        .delete-link:hover {
            color: #c82333;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Categories Management</h1>
    <a href="category/addCategory.jsp" class="button">Add New Category</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <% for ( Categories category : categories) { %>
        <tr>
            <td><%= category.getCategoryId() %></td>
            <td><%= category.getName() %></td>
            <td class="action-links">
                <a href="publisher/editPublishers.jsp?id=<%= category.getCategoryId() %>">Edit</a>
                <a href="genres?action=delete&id=<%= category.getCategoryId() %>" class="delete-link"
                   onclick="return confirm('Are you sure you want to delete this genre?')">
                    Delete
                </a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
