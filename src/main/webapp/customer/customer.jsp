<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="customer.css">
    <style>
        /* General styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            width: 80%;
            margin: 50px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            font-size: 28px;
            margin-bottom: 20px;
        }

        /* Table styling */
        .table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .table th, .table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .table th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
        }

        .table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .table tr:hover {
            background-color: #f1f1f1;
        }

        .table td a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
            padding: 6px 12px;
            border-radius: 5px;
        }

        .table td a:hover {
            background-color: #007bff;
            color: white;
        }

        /* Buttons styling */
        .table td a.edit {
            background-color: #28a745;
            color: white;
        }

        .table td a.delete {
            background-color: #dc3545;
            color: white;
        }

        .table td a.edit:hover {
            background-color: #218838;
        }

        .table td a.delete:hover {
            background-color: #c82333;
        }

        /* Message styling */
        .message {
            background-color: #d4edda;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            margin-top: 20px;
            text-align: center;
            font-size: 16px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 15px;
            }

            .table th, .table td {
                padding: 10px;
            }

            h2 {
                font-size: 24px;
            }

            .table td a {
                padding: 5px 10px;
            }
        }

    </style>
</head>
<body>

<div class="container">
    <h2>Customer List</h2>

    <!-- Success or error message -->
    <c:if test="${not empty message}">
        <div class="message ${message.contains('Cannot delete') ? 'error' : 'success'}">
                ${message}
        </div>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>School</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.schoolName}</td>
                <td>${customer.address}</td>
                <td>
                    <a href="customers?action=edit&id=${customer.id}" class="edit">Edit</a>
                    <a href="customers?action=delete&id=${customer.id}" class="delete">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="text-align: center; margin-top: 20px;">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <a href="customers?action=listCustomers&page=${i}" style="
                margin: 0 5px;
                padding: 10px 15px;
                text-decoration: none;
                border: 1px solid #3498db;
                border-radius: 5px;
                color: #3498db;
                font-size: 1rem;
                ${i == currentPage ? 'background-color: #3498db; color: white;' : ''}">
                    ${i}
            </a>
        </c:forEach>
    </div>
</div>

</body>
</html>
