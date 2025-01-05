<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Category </title>
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background: linear-gradient(to bottom, rgba(255, 69, 0, 0.8), rgba(255, 255, 255, 1));
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px 30px;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            text-align: center;
        }

        h2 {
            color: #d84315;
            font-size: 2.5em;
            margin-bottom: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-size: 1.2em;
            color: #555;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }

        button {
            background-color: #e64a19;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #bf360c;
        }

        .message {
            padding: 10px;
            background-color: #f57c00;
            color: white;
            font-size: 1em;
            border-radius: 5px;
            margin-bottom: 20px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .return-button {
            background-color: #ff7043;
            margin-top: 10px;
        }

        .return-button:hover {
            background-color: #d84315;
        }

        .tet-banner {
            text-align: center;
            margin-bottom: 20px;
        }

        .tet-banner img {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<div class="tet-banner">
    <img src="data:image/jpeg;base64,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" alt="Tết Nguyên Đán Banner">
</div>
<div class="form-container">
    <h2>🎋 Add Category </h2>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/categories" method="post">
        <input type="hidden" name="action" value="addCategory">

        <label for="name">Category Name:</label>
        <input type="text" id="name" name="name" required>

        <button type="submit">Add Category</button>
    </form>

    <form action="categories" method="get">
        <input type="hidden" name="action" value="listCategories">
        <button type="submit" class="return-button">Back to Category List</button>
    </form>
</div>
</body>
</html>
