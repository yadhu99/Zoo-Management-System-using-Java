<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%
    // Database connection parameters
    String url = "jdbc:mysql://localhost:3306/zoo_management";
    String username = "root";
    String password = "admin@123";

    // Initialize connection, statement, and result set
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    List<Map<String, String>> animals = new ArrayList<>();
    List<Map<String, String>> filteredAnimals = new ArrayList<>();

    String searchSpecies = request.getParameter("species");

    try {
        // Establish connection
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);

        // Execute query
        String query = "SELECT * FROM animals";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        // Process result set
        while (resultSet.next()) {
            Map<String, String> animal = new HashMap<>();
            animal.put("id", resultSet.getString("id"));
            animal.put("name", resultSet.getString("name"));
            animal.put("species", resultSet.getString("species"));
            animal.put("age", resultSet.getString("age"));
            animals.add(animal);

            // Filter animals based on the searched species
            if (searchSpecies != null && !searchSpecies.isEmpty()) {
                if (searchSpecies.equalsIgnoreCase(resultSet.getString("species"))) {
                    filteredAnimals.add(animal);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Animal List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            padding: 5px 10px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 4px;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>List of Animals</h1>
    <form method="get">
        <label for="species">Enter species:</label>
        <input type="text" id="species" name="species" value="<%= searchSpecies != null ? searchSpecies : "" %>">
        <button type="submit" class="btn">Search</button>
    </form>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Species</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        <% 
        List<Map<String, String>> displayAnimals = searchSpecies != null && !searchSpecies.isEmpty() ? filteredAnimals : animals;
        for (Map<String, String> animal : displayAnimals) { 
        %>
        <tr>
            <td><%= animal.get("id") %></td>
            <td><%= animal.get("name") %></td>
            <td><%= animal.get("species") %></td>
            <td><%= animal.get("age") %></td>
            <td>
                <a href="updateAnimal.jsp?animalId=<%= animal.get("id") %>" class="btn">Update</a>
                <a href="deleteAnimal.jsp?animalId=<%= animal.get("id") %>" class="btn" onclick="return confirm('Are you sure you want to delete this animal?')">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
