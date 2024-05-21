
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Zoo Management System</title>
    <style>
        /* General styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }

        h2 {
            color: #333333;
            margin-bottom: 20px;
            font-size: 24px;
        }

        p {
            color: #666666;
            margin-bottom: 30px;
        }

        .button-group {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 15px 20px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Form styles */
        form {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Zoo Management System</h2>
        <p>This is a simple Zoo Management System where you can add, view, update, and delete animals.</p>
        
        <div class="button-group">
            <form action="addAnimal.html">
                <button type="submit">Add Animal</button>
            </form>
            <form action="deleteAnimal.jsp">
                <button type="submit">Delete Animal</button>
            </form>
            <form action="updateAnimal.jsp">
                <button type="submit">Update Animal</button>
            </form>
            <form action="viewAnimals.jsp">
                <button type="submit">View Animals</button>
            </form>
        </div>
    </div>
</body>
</html>
    