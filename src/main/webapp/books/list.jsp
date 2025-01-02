<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
</head>
<body>
    <h1>Book List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.category}</td>
                <td>
                    <a href="edit?id=${book.id}">Edit</a>
                    <a href="delete?id=${book.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="new">Add New Book</a>
</body>
</html>