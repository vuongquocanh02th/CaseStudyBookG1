<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Category List</title>
</head>
<body>
    <h1>Category List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${listCategory}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>
                    <a href="edit?id=${category.id}">Edit</a>
                    <a href="delete?id=${category.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="new">Add New Category</a>
</body>
</html>