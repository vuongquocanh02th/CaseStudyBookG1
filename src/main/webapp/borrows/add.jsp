<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Borrow</title>
</head>
<body>
    <h1>Add Borrow</h1>
    <form action="insert" method="post">
        <label for="customer_id">Customer ID:</label>
        <input type="text" id="customer_id" name="customer_id" required><br>
        <label for="book_id">Book ID:</label>
        <input type="text" id="book_id" name="book_id" required><br>
        <label for="borrow_date">Borrow Date:</label>
        <input type="text" id="borrow_date" name="borrow_date" required><br>
        <input type="submit" value="Add Borrow">
    </form>
</body>
</html>