<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Category</title>
</head>
<body>
    <h1>Add Category</h1>
    <form action="insert" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        <input type="submit" value="Add Category">
    </form>
</body>
</html>