<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Delete Book</title>
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
    p {
      text-align: center;
      margin-bottom: 20px;
      color: #e74c3c;
    }
    button {
      background-color: #e74c3c;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button:hover {
      background-color: #c0392b;
    }
    .cancel-button {
      background-color: #3498db;
      margin-top: 10px;
    }
    .cancel-button:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h2>Delete Book</h2>
  <p>Are you sure you want to delete the book "${book.bookName}"?</p>
  <form action="books" method="post">
    <input type="hidden" name="action" value="confirmDeleteBook">
    <input type="hidden" name="id" value="${book.id}">
    <button type="submit">Confirm Delete</button>
  </form>
  <form action="books" method="get">
    <input type="hidden" name="action" value="listBooks">
    <button type="submit" class="cancel-button">Cancel</button>
  </form>
</div>
</body>
</html>