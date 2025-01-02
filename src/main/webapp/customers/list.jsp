<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h1>Customer List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${listCustomer}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.email}</td>
                <td>
                    <a href="edit?id=${customer.id}">Edit</a>
                    <a href="delete?id=${customer.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="new">Add New Customer</a>
</body>
</html>