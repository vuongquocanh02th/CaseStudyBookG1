<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
    <h1>Edit Book</h1>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${book.id}">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${book.title}" required><br>
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" value="${book.author}" required><br>
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="${book.category}" required><br>
        <input type="submit" value="Update Book">
    </form>
</body>
</html>