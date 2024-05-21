package dao;


import model.Animal;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        String query = "SELECT id, name, species, age FROM animals";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setName(rs.getString("name"));
                animal.setSpecies(rs.getString("species"));
                animal.setAge(rs.getInt("age"));
                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals;
    }
}

