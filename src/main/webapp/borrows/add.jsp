<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Borrow</title>
</head>
<body>
<h1>Add New Borrow</h1>
<form action="borrows?action=add" method="post">
    <label for="customerId">Customer:</label>
    <select id="customerId" name="customerId" required>
        <option value="">Select Customer</option>
        <c:forEach var="customer" items="${customers}">
            <option value="${customer.id}">${customer.name}</option>
        </c:forEach>
    </select><br>
    <label for="bookId">Book:</label>
    <select id="bookId" name="bookId" required>
        <option value="">Select Book</option>
        <c:forEach var="book" items="${books}">
            <option value="${book.id}">${book.bookName}</option>
        </c:forEach>
    </select><br>
    <label for="borrowDate">Borrow Date:</label>
    <input type="date" id="borrowDate" name="borrowDate" required><br>
    <label for="returnDate">Return Date:</label>
    <input type="date" id="returnDate" name="returnDate"><br>
    <button type="submit">Add Borrow</button>
</form>
</body>
</html>