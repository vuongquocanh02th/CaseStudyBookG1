<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Borrow Details</title>
</head>
<body>
<h1>Borrow Details</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Borrow ID</th>
        <th>Book ID</th>
        <th>Borrow Date</th>
        <th>Return Date</th>
        <th>Return Status</th>
    </tr>
    <c:forEach var="detail" items="${borrowDetails}">
        <tr>
            <td>${detail.id}</td>
            <td>${detail.borrowId}</td>
            <td>${detail.bookId}</td>
            <td>${detail.borrowDate}</td>
            <td>${detail.returnDate}</td>
            <td>${detail.returnStatus}</td>
        </tr>
    </c:forEach>
</table>
<a href="borrows">Back to Borrow List</a>
</body>
</html>