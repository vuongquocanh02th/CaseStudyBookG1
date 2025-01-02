<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
    <h1>Edit Customer</h1>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${customer.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${customer.name}" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${customer.email}" required><br>
        <input type="submit" value="Update Customer">
    </form>
</body>
</html>