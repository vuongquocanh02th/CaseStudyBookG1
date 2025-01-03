<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Borrow</title>
</head>
<body>
<h1>Add New Borrow</h1>
<form action="borrows?action=add" method="post">
    <label for="customerId">Customer ID:</label>
    <input type="text" id="customerId" name="customerId" required><br>
    <label for="borrowDate">Borrow Date:</label>
    <input type="date" id="borrowDate" name="borrowDate" required><br>
    <label for="returnDate">Return Date:</label>
    <input type="date" id="returnDate" name="returnDate"><br>
    <button type="submit">Add Borrow</button>
</form>
</body>
</html>