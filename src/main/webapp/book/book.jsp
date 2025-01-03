<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book List</title>
    <style>
        /* Basic Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            padding: 20px;
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            margin-bottom: 20px;
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
        }

        thead {
            background-color: #2980b9;
            color: white;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            font-size: 1rem;
            font-weight: 600;
        }

        td {
            font-size: 0.95rem;
            color: #555;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .action-buttons {
            display: flex;
            justify-content: flex-start;
            gap: 10px;
        }

        .action-buttons a {
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
            color: white;
            background-color: #3498db;
            font-size: 0.9rem;
            text-align: center;
        }

        .action-buttons a:hover {
            background-color: #2980b9;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            table {
                font-size: 0.9rem;
            }

            th, td {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Book List</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Book Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Genre</th>
            <th>Publisher</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.bookName}</td>
                <td>${book.description}</td>
                <td>${book.status}</td>
                <td>${book.genre.name}</td>
                <td>${book.publisher.name}</td>
                <td>${book.category.name}</td>
                <td class="action-buttons">
                    <a href="#">Edit</a>
                    <a href="#">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
