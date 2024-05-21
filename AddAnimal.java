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
 * Servlet implementation class AddAnimal
 */
public class AddAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnimal() {
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
		String name = request.getParameter("name");
        String species = request.getParameter("species");
        int age = Integer.parseInt(request.getParameter("age"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO animals (name, species, age) VALUES (?, ?, ?)")) {

            statement.setString(1, name);
            statement.setString(2, species);
            statement.setInt(3, age);

            statement.executeUpdate();
            response.sendRedirect("viewAnimals.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error.html");
        }
	}

}
