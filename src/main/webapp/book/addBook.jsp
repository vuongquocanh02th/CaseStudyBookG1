<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Book</title>
    <style>
        .form-container {
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #2c3e50;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input, select, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #27ae60;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #219150;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Add New Book</h2>

    <form action="books" method="post">
        <input type="hidden" name="action" value="add">

        <label>Book Name:</label>
        <input type="text" name="bookName" required>

        <label>Description:</label>
        <textarea name="description" required></textarea>

        <label>Status:</label>
        <select name="status">
            <option value="Available">Available</option>
            <option value="Unavailable">Unavailable</option>
            <option value="Reserved">Reserved</option>
        </select>
<%--        <input type="text" name="status" required>--%>
        <label>Genre:</label>
        <select name="genId">
            <c:forEach var="genre" items="${genres}">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select>

        <label>Publisher:</label>
        <select name="publisherId">
            <c:forEach var="publisher" items="${publishers}">
                <option value="${publisher.publisherId}">${publisher.name}</option>
            </c:forEach>
        </select>

        <label>Category:</label>
        <select name="categoryId">
            <c:forEach var="category" items="${categories}">
                <option value="${category.categoryId}">${category.name}</option>
            </c:forEach>
        </select>

        <button type="submit">Add Book</button>
    </form>
</div>
</body>
</html>
