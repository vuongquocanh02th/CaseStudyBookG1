<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Borrow List</title>
</head>
<body>
    <h1>Borrow List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Customer ID</th>
            <th>Book ID</th>
            <th>Borrow Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="borrow" items="${listBorrow}">
            <tr>
                <td>${borrow.id}</td>
                <td>${borrow.customerId}</td>
                <td>${borrow.bookId}</td>
                <td>${borrow.borrowDate}</td>
                <td>
                    <a href="edit?id=${borrow.id}">Edit</a>
                    <a href="delete?id=${borrow.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="new">Add New Borrow</a>
</body>
</html>