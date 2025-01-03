<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
<h1>Book List</h1>
<a href="books?action=add">Add New Book</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Genre</th>
        <th>Publisher</th>
        <th>Category</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.bookName}</td>
            <td>${book.description}</td>
            <td>${book.status}</td>
            <td>${book.genID}</td>
            <td>${book.publisherID}</td>
            <td>${book.categoryID}</td>
            <td>
                <a href="books?action=edit&id=${book.id}">Edit</a>
                <form action="books" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${book.id}">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>