<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<html>
<head>
    <title>Delete Customer</title>
    <style>
        .confirm-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
            background-color: #f9f9f9;
        }

        .btn {
            padding: 10px 20px;
            margin: 10px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            color: white;
        }

        .btn-confirm {
            background-color: green;
        }

        .btn-cancel {
            background-color: red;
        }

        .btn-confirm:hover {
            background-color: darkgreen;
        }

        .btn-cancel:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>

<c:if test="${not empty customer}">
    <div class="confirm-container">
        <h2>Are you sure you want to delete this customer?</h2>
        <table>
            <tr>
                <td><strong>ID:</strong></td>
                <td>${customer.id}</td>
            </tr>
            <tr>
                <td><strong>Name:</strong></td>
                <td>${customer.name}</td>
            </tr>
            <tr>
                <td><strong>School Name:</strong></td>
                <td>${customer.schoolName}</td>
            </tr>
            <tr>
                <td><strong>Address:</strong></td>
                <td>${customer.address}</td>
            </tr>
            <tr>
                <td><strong>Date of Birth:</strong></td>
                <td>${customer.dob}</td>
            </tr>
        </table>

        <form action="customers?action=delete&id=${customer.id}" method="post">
            <button type="submit" class="btn btn-confirm">Yes, delete</button>
            <a href="customers" class="btn btn-cancel">Cancel</a>
        </form>
    </div>
</c:if>

</body>
</html>
