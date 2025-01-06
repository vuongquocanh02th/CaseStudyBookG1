<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<h1>Edit Category</h1>
<form action="categories?action=edit" method="post">
    <input type="hidden" name="id" value="${category.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${category.name}" required><br>
    <button type="submit">Update Category</button>
</form>
</body>
</html>