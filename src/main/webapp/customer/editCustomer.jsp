<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f9;
            margin: 0;
        }
        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .back-button {
            background-color: #007bff;
            margin-top: 10px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
        .notification {
            color: green;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Edit Customer</h1>
    <% if (request.getAttribute("message") != null) { %>
    <div class="notification">
        <%= request.getAttribute("message") %>
    </div>
    <% } %>
    <form action="/customers" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${customer.id}">
        <label>Name:</label>
        <input type="text" name="name" value="${customer.name}" required>
        <label>School Name:</label>
        <input type="text" name="schoolName" value="${customer.schoolName}">
        <label>Address:</label>
        <input type="text" name="address" value="${customer.address}">
        <label>Date of Birth:</label>
        <input type="date" name="dob" value="${customer.dob}">
        <button type="submit">Update</button>
    </form>
    <form action="/customers">
        <button type="submit" class="back-button">Back to Customer List</button>
    </form>
</div>
</body>
</html>