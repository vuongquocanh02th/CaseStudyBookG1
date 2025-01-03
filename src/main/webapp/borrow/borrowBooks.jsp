<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Books" %>
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

        select, button {
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
    </style>
</head>
<body>
<h2>Borrow Books</h2>
<form action="${pageContext.request.contextPath}/borrow?action=borrowBooks" method="post">
    <label for="bookId">Select Book:</label>
    <select name="bookId" id="bookId">
        <%
            List<Books> books = (List<Books>) request.getAttribute("books");
            if (books != null) {
                for (Books book : books) {
        %>
        <option value="<%= book.getId() %>"><%= book.getBookName() %></option>
        <%
                }
            }
        %>
    </select>
    <button type="submit">Borrow</button>
</form>
</body>
</html>