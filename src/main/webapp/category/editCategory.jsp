<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Category</title>
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
    input {
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
    .message {
      text-align: center;
      color: green;
      font-size: 18px;
      margin-bottom: 20px;
    }
    .return-button {
      background-color: #3498db;
      margin-top: 10px;
    }
    .return-button:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h2>Edit Category</h2>

  <c:if test="${not empty message}">
    <div class="message">${message}</div>
  </c:if>

  <form action="${pageContext.request.contextPath}/categories" method="post">
    <input type="hidden" name="action" value="updateCategory">
    <input type="hidden" name="id" value="${category.categoryId}">

    <label>Category Name:</label>
    <input type="text" name="name" value="${category.name}" required>

    <button type="submit">Update Category</button>
  </form>
  <form action="categories" method="get">
    <input type="hidden" name="action" value="listCategories">
    <button type="submit" class="return-button">Back to Categories List</button>
  </form>
</div>
</body>
</html>