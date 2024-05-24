package com.example.demo.repository;

import com.example.demo.config.DbConfig;
import com.example.demo.model.Person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private final DbConfig config;

    public PersonRepository(DbConfig config) {
        this.config = config;
    }

    public int save(Person person) {
        // для защиты от SQL-инъекций используем PreparedStatement
        String sql = "INSERT INTO person (name, age) VALUES (?,?)";
        int result = 0;

        try (Connection connection = DriverManager.getConnection(config.url(), config.login(), config.password());
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try(Connection connection = DriverManager.getConnection(config.url(), config.login(), config.password());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person findById(int id) {
        String sql = "SELECT * FROM person WHERE id =?";
        Person person = null;
        try(Connection connection = DriverManager.getConnection(config.url(), config.login(), config.password());
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
}