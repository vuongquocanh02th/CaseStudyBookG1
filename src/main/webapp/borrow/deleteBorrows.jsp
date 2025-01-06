<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="model.BorrowDetail" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Delete Borrow</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 0;
    }

    .container {
      width: 50%;
      margin: 50px auto;
      background-color: white;
      padding: 20px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }

    h2 {
      text-align: center;
      color: #dc3545;
    }

    .actions {
      display: flex;
      justify-content: center;
      gap: 20px;
    }

    .actions form {
      display: inline;
    }

    .actions button {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    .confirm-btn {
      background-color: #dc3545;
      color: white;
    }

    .cancel-btn {
      background-color: #6c757d;
      color: white;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Confirm Delete</h2>
  <p>Are you sure you want to delete this borrow detail?</p>
  <div class="actions">
    <form action="<%= request.getContextPath() %>/borrow" method="post">
      <input type="hidden" name="action" value="confirmDeleteBorrow">
      <input type="hidden" name="id" value="<%= ((BorrowDetail) request.getAttribute("borrowDetail")).getId() %>">
      <button type="submit" class="confirm-btn">Delete</button>
    </form>
    <form action="<%= request.getContextPath() %>/borrow" method="get">
      <input type="hidden" name="action" value="manageBorrows">
      <button type="submit" class="cancel-btn">Cancel</button>
    </form>
  </div>
</div>
</body>
</html>