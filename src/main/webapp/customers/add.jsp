<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<h1>Add New Customer</h1>
<form action="customers?action=add" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="schoolName">School Name:</label>
    <input type="text" id="schoolName" name="schoolName"><br>
    <label for="address">Address:</label>
    <textarea id="address" name="address"></textarea><br>
    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" required><br>
    <button type="submit">Add Customer</button>
</form>
</body>
</html>