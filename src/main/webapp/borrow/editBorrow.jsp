<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="model.BorrowDetail" %>
<%@ page import="model.Customer" %>
<%@ page import="model.Books" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Borrow</title>
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

        input, select, button {
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
    </style>
    <script>
        function updateCustomerName() {
            var customerId = document.getElementById("customerId").value;
            var customerName = document.getElementById("customerName");
            var customers = JSON.parse('<%= new Gson().toJson((List<Customer>) request.getAttribute("customers")) %>');
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
            var books = JSON.parse('<%= new Gson().toJson((List<Books>) request.getAttribute("books")) %>');
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
<h2>Edit Borrow</h2>
<%
    BorrowDetail borrowDetail = (BorrowDetail) request.getAttribute("borrowDetail");
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    List<Books> books = (List<Books>) request.getAttribute("books");
%>
<form action="${pageContext.request.contextPath}/borrow?action=updateBorrow" method="post">
    <input type="hidden" name="id" value="<%= borrowDetail.getId() %>">
    <label for="customerId">Customer ID:</label>
    <select name="customerId" id="customerId" onchange="updateCustomerName()" required>
        <%
            for (Customer customer : customers) {
        %>
        <option value="<%= customer.getId() %>" <%= customer.getId() == borrowDetail.getCustomer().getId() ? "selected" : "" %>><%= customer.getId() %></option>
        <%
            }
        %>
    </select>
    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" value="<%= borrowDetail.getCustomer().getName() %>" readonly>
    <label for="bookId">Book ID:</label>
    <select name="bookId" id="bookId" onchange="updateBookName()" required>
        <%
            for (Books book : books) {
        %>
        <option value="<%= book.getId() %>" <%= book.getId() == borrowDetail.getBook().getId() ? "selected" : "" %>><%= book.getId() %></option>
        <%
            }
        %>
    </select>
    <label for="bookName">Book Name:</label>
    <input type="text" id="bookName" value="<%= borrowDetail.getBook().getBookName() %>" readonly>
    <label for="borrowDate">Borrow Date:</label>
    <input type="date" name="borrowDate" id="borrowDate" value="<%= borrowDetail.getBorrowDate() %>" required>
    <label for="returnDate">Return Date:</label>
    <input type="date" name="returnDate" id="returnDate" value="<%= borrowDetail.getReturnDate() %>" required>
    <label for="returnStatus">Return Status:</label>
    <select name="returnStatus" id="returnStatus" required>
        <option style="color: yellow" value="Pending" <%= "Pending".equals(borrowDetail.getReturnStatus()) ? "selected" : "" %>>Pending</option>
        <option style="color: green" value="Returned" <%= "Returned".equals(borrowDetail.getReturnStatus()) ? "selected" : "" %>>Returned</option>
        <option style="color: red" value="Overdue" <%= "Overdue".equals(borrowDetail.getReturnStatus()) ? "selected" : "" %>>Overdue</option>
    </select>
    <button type="submit">Update</button>
</form>
</body>
</html>