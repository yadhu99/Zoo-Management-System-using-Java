<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<%
    // Handle form submission
    if (request.getParameter("submit") != null) {
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        int age = Integer.parseInt(request.getParameter("age"));

        String url = "jdbc:mysql://localhost:3306/zoo_management";
        String username = "root";
        String password = "admin@123";

        // Initialize connection, statement, and result set
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            // Establish connection
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            // Insert new animal data into the database
            String query = "INSERT INTO animals (name, species, age) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, species);
            st.setInt(3, age);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirect after successful insert
        response.sendRedirect("viewAnimals.jsp");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add New Animal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container mt-3">
        <h1>Add New Animal</h1>
        <form method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="species" class="form-label">Species:</label>
                <input type="text" class="form-control" id="species" name="species" required>
            </div>
            <div class="mb-3">
                <label for="age" class="form-label">Age:</label>
                <input type="number" class="form-control" id="age" name="age" required>
            </div>
            <input type="hidden" name="submit" value="true">
            <button type="submit" class="btn btn-primary">Add Animal</button>
        </form>
    </div>
</body>
</html>
