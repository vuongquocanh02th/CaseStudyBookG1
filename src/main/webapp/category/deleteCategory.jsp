<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Delete Category</title>
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
  <h2>Delete Category</h2>
  <p>Are you sure you want to delete the category "${category.name}"?</p>
  <form action="categories" method="post">
    <input type="hidden" name="action" value="confirmDeleteCategory">
    <input type="hidden" name="id" value="${category.categoryId}">
    <button type="submit">Confirm Delete</button>
  </form>
  <form action="categories" method="get">
    <input type="hidden" name="action" value="listCategories">
    <button type="submit" class="cancel-button">Cancel</button>
  </form>
</div>
</body>
</html>