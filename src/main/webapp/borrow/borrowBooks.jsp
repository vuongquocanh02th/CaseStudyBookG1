<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Books" %>
<%@ page import="model.Customer" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrow Books</title>
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

        form {
            width: 50%;
            margin: 30px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #495057;
        }

        select, input, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .container {
            padding: 20px;
            text-align: center;
        }

        .message {
            text-align: center;
            color: green;
            font-size: 18px;
            margin-bottom: 20px;
        }
    </style>
    <script>
        function updateCustomerName() {
            var customerId = document.getElementById("customerId").value;
            var customerName = document.getElementById("customerName");
            var customers = JSON.parse('<%= new com.google.gson.Gson().toJson((List<Customer>) request.getAttribute("customers")) %>');
            for (var i = 0; i < customers.length; i++) {
                if (customers[i].id == customerId) {
                    customerName.value = customers[i].name;
                    break;
                }
            }
        }

        function updateBookName() {
            var bookId = document.getElementById("bookId").value;
            var bookName = document.getElementById("bookName");
            var books = JSON.parse('<%= new com.google.gson.Gson().toJson((List<Books>) request.getAttribute("books")) %>');
            for (var i = 0; i < books.length; i++) {
                if (books[i].id == bookId) {
                    bookName.value = books[i].bookName;
                    break;
                }
            }
        }
    </script>
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
<h2>Borrow Books</h2>
<% String message = (String) request.getAttribute("message"); %>
<% if (message != null) { %>
<div class="message"><%= message %></div>
<% } %>
<form action="${pageContext.request.contextPath}/borrow?action=borrowBooks" method="post">
    <label for="customerId">Select Customer:</label>
    <select name="customerId" id="customerId" onchange="updateCustomerName()">
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
            if (customers != null) {
                for (Customer customer : customers) {
        %>
        <option value="<%= customer.getId() %>"><%= customer.getId() %></option>
        <%
                }
            }
        %>
    </select>
    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" readonly>
    <label for="bookId">Select Book:</label>
    <select name="bookId" id="bookId" onchange="updateBookName()">
        <%
            List<Books> books = (List<Books>) request.getAttribute("books");
            if (books != null) {
                for (Books book : books) {
        %>
        <option value="<%= book.getId() %>"><%= book.getId() %></option>
        <%
                }
            }
        %>
    </select>
    <label for="bookName">Book Name:</label>
    <input type="text" id="bookName" readonly>
    <label for="borrowDate">Borrow Date:</label>
    <input type="date" name="borrowDate" id="borrowDate" required>
    <label for="returnDate">Return Date:</label>
    <input type="date" name="returnDate" id="returnDate" required>
    <button type="submit">Borrow</button>
</form>

</body>
</html>