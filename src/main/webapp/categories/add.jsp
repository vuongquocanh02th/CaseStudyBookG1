<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Category</title>
</head>
<body>
<h1>Add New Category</h1>
<form action="categories?action=add" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <button type="submit">Add Category</button>
</form>
</body>
</html>