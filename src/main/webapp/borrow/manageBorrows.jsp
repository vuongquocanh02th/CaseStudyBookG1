<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Borrow" %>
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
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            border: 1px solid #dee2e6;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e9ecef;
        }

        .container {
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Manage Borrows</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Customer ID</th>
        <th>Borrow Date</th>
        <th>Return Date</th>
    </tr>
    <%
        List<Borrow> borrows = (List<Borrow>) request.getAttribute("borrows");
        if (borrows != null) {
            for (Borrow borrow : borrows) {
    %>
    <tr>
        <td><%= borrow.getId() %></td>
        <td><%= borrow.getCustomerId() %></td>
        <td><%= borrow.getBorrowDate() %></td>
        <td><%= borrow.getReturnDate() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>