<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<h1>Edit Book</h1>
<form action="books?action=edit" method="post">
    <input type="hidden" name="id" value="${book.id}">
    <label for="bookName">Name:</label>
    <input type="text" id="bookName" name="bookName" value="${book.bookName}" required><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required>${book.description}</textarea><br>
    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="Available" ${book.status == 'Available' ? 'selected' : ''}>Available</option>
        <option value="Unavailable" ${book.status == 'Unavailable' ? 'selected' : ''}>Unavailable</option>
        <option value="Reserved" ${book.status == 'Reserved' ? 'selected' : ''}>Reserved</option>
    </select><br>
    <label for="genID">Genre:</label>
    <select id="genID" name="genID">
        <option value="">Select Genre</option>
        <!-- Options will be populated dynamically -->
    </select>
    <input type="text" id="newGenre" name="newGenre" placeholder="Or add new genre"><br>
    <label for="publisherID">Publisher:</label>
    <select id="publisherID" name="publisherID">
        <option value="">Select Publisher</option>
        <!-- Options will be populated dynamically -->
    </select>
    <input type="text" id="newPublisher" name="newPublisher" placeholder="Or add new publisher"><br>
    <label for="categoryID">Category:</label>
    <select id="categoryID" name="categoryID">
        <option value="">Select Category</option>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}" ${book.categoryID == category.id ? 'selected' : ''}>${category.name}</option>
        </c:forEach>
    </select>
    <input type="text" id="newCategory" name="newCategory" placeholder="Or add new category"><br>
    <button type="submit">Update Book</button>
</form>
</body>
</html>