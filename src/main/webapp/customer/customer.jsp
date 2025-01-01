<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="customer.css">
    <style>

        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;

            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 90%;
            border-collapse: collapse;
            margin: 25px auto;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            text-decoration: none;
            color: #007bff;
            padding: 8px 12px;
            border-radius: 4px;
            transition: 0.3s;
        }

        a:hover {
            background-color: #0056b3;
            color: white;
        }

        table tr:last-child td {
            border-bottom: none;
        }
    </style>
</head>
<body>
<h1>Customer List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>School</th>
        <th>Address</th>
        <th>Date of Birth</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.schoolName}</td>
            <td>${customer.address}</td>
            <td>${customer.dob}</td>
            <td>
                <a href="/customers?action=edit&id=${customer.id}">Edit</a>
                <a href="/customers?action=delete&id=${customer.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
