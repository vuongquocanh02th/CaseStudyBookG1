<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<h1>Add New Book</h1>
<form action="books?action=add" method="post">
    <label for="bookName">Name:</label>
    <input type="text" id="bookName" name="bookName" required><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br>
    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="Available">Available</option>
        <option value="Unavailable">Unavailable</option>
        <option value="Reserved">Reserved</option>
    </select><br>
    <label for="genID">Genre:</label>
    <select id="genID" name="genID">
        <option value="">Select Genre</option>
        <!-- Options will be populated dynamically -->
    </select><br>
    <label for="publisherID">Publisher:</label>
    <select id="publisherID" name="publisherID">
        <option value="">Select Publisher</option>
        <!-- Options will be populated dynamically -->
    </select><br>
    <label for="categoryID">Category:</label>
    <select id="categoryID" name="categoryID">
        <option value="">Select Category</option>
        <!-- Options will be populated dynamically -->
    </select><br>
    <button type="submit">Add Book</button>
</form>
</body>
</html>