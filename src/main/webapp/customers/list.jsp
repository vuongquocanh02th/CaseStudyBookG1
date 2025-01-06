<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Customer List</h1>
<a href="customers?action=add">Add New Customer</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>School Name</th>
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
                <a href="customers?action=edit&id=${customer.id}">Edit</a>
                <form action="customers" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${customer.id}">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>