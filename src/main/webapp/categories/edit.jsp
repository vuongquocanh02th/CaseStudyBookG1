<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
    <h1>Edit Category</h1>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${category.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${category.name}" required><br>
        <input type="submit" value="Update Category">
    </form>
</body>
</html>