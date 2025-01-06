<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Borrow List</title>
</head>
<body>
<h1>Borrow List</h1>
<a href="borrows?action=add">Add New Borrow</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Customer ID</th>
        <th>Customer Name</th>
        <th>Book Name</th>
        <th>Borrow Date</th>
        <th>Return Date</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="borrow" items="${borrows}">
        <tr>
            <td>${borrow.id}</td>
            <td>${borrow.customerId}</td>
            <td>${borrow.customerName}</td>
            <td>${borrow.bookName}</td>
            <td>${borrow.borrowDate}</td>
            <td>${borrow.returnDate}</td>
            <td>
                <a href="borrows?action=details&id=${borrow.id}">Details</a>
                <form action="borrows" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${borrow.id}">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>