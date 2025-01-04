<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

        .message {
            text-align: center;
            color: green;
            font-size: 18px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary" style="
        background-color: #3498db;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        color: white;
        font-size: 1rem;">
        Back to Home
    </a>
    <h1>Categories Management</h1>
    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>
    <a href="category/addCategory.jsp" class="button">Add New Category</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.categoryId}</td>
                <td>${category.name}</td>
                <td class="action-links">
                    <a href="categories?action=editCategory&id=${category.categoryId}">Edit</a>
                    <a href="categories?action=deleteCategory&id=${category.categoryId}" class="delete-link">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>