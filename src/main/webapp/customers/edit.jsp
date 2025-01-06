<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<h1>Edit Customer</h1>
<form action="customers?action=edit" method="post">
    <input type="hidden" name="id" value="${customer.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${customer.name}" required><br>
    <label for="schoolName">School Name:</label>
    <input type="text" id="schoolName" name="schoolName" value="${customer.schoolName}"><br>
    <label for="address">Address:</label>
    <textarea id="address" name="address">${customer.address}</textarea><br>
    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" value="${customer.dob}" required><br>
    <button type="submit">Update Customer</button>
</form>
</body>
</html>