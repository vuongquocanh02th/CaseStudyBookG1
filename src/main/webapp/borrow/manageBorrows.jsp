<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.BorrowDetail" %>
<%@ page import="model.Customer" %>
<%@ page import="model.Books" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Borrows</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
            font-size: 24px;
            color: #343a40;

        }

        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #dee2e6;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .actions {
            display: flex;
            gap: 10px;
        }

        .actions form {
            display: inline;
        }

        .actions button {
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .edit-btn {
            background-color: #ffc107;
            color: white;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .message {
            width: 80%;
            margin: 20px auto;
            padding: 10px;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            text-align: center;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary" style="
        background-color: #3498db;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        color: white;
        font-size: 1rem;">
    Back to Home
</a>
<h2>Manage Borrows</h2>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<div class="message"><%= message %></div>
<%
    }
%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Customer ID</th>
        <th>Customer Name</th>
        <th>Book Name</th>
        <th>Borrow Date</th>
        <th>Return Date</th>
        <th>Return Status</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<BorrowDetail> borrowDetails = (List<BorrowDetail>) request.getAttribute("borrowDetails");
        if (borrowDetails != null) {
            for (BorrowDetail borrowDetail : borrowDetails) {
                Customer customer = borrowDetail.getCustomer();
                Books book = borrowDetail.getBook();
    %>
    <tr>
        <td><%= borrowDetail.getId() %></td>
        <td><%= customer.getId() %></td>
        <td><%= customer.getName() %></td>
        <td><%= book.getBookName() %></td>
        <td><%= borrowDetail.getBorrowDate() %></td>
        <td><%= borrowDetail.getReturnDate() %></td>
        <td><%= borrowDetail.getReturnStatus() %></td>
        <td class="actions">
            <form action="<%= request.getContextPath() %>/borrow" method="get">
                <input type="hidden" name="action" value="editBorrow">
                <input type="hidden" name="id" value="<%= borrowDetail.getId() %>">
                <button type="submit" class="edit-btn">Edit</button>
            </form>
            <form action="<%= request.getContextPath() %>/borrow?action=deleteBorrow" method=get>
                <input type="hidden" name="action" value="deleteBorrow">
                <input type="hidden" name="id" value="<%= borrowDetail.getId() %>">
                <button type="submit" class="delete-btn">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>