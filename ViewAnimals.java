package com.pu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Animal;
import util.DBUtil;
import dao.AnimalDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewAnimals
 */
@WebServlet("/viewAnimals")
public class ViewAnimals extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAnimals() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Animal> animals = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM animals");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setName(resultSet.getString("name"));
                animal.setSpecies(resultSet.getString("species"));
                animal.setAge(resultSet.getInt("age"));
                animals.add(animal);
            }

            request.setAttribute("animals", animals);
            request.getRequestDispatcher("viewAnimals.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error.html");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AnimalDAO animalDAO = new AnimalDAO();
        List<Animal> animals = animalDAO.getAllAnimals();

		doGet(request, response);
		
	}

}
