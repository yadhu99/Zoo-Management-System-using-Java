<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Animal</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
</head>
<body>
    <div class="container">
        <h2>Delete Animal</h2>
        <p>Are you sure you want to delete this animal?</p>
        <form action="./DeleteAnimal" method="post" onsubmit="return confirmDelete()">
        <p>Confirm the ID number to Delete</p> 
            <input type="number" id="id" name="animalId" value="${animalId}">
            <div class="input-group">
                <button type="submit">Yes, Delete</button>
                <a href="viewAnimals.jsp">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>