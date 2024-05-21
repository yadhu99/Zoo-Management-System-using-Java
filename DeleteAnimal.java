package com.pu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class DeleteAnimal
 */
public class DeleteAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAnimal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int animalId = Integer.parseInt(request.getParameter("animalId"));
		System.out.println("animalId");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM animals WHERE id = ?")) {

            // Set the ID parameter in the prepared statement
            statement.setInt(1, animalId);
            System.out.println(statement);

            // Execute the delete operation
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                // Animal deleted successfully
                response.sendRedirect("viewAnimals.jsp");
            } else {
                // Animal not found or unable to delete
                response.sendRedirect("error.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            response.sendRedirect("error.jsp");
        }
	}

}
