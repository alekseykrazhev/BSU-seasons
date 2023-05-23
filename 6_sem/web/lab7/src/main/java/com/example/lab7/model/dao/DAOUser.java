package com.example.lab7.model.dao;

import com.example.lab7.Controller.configuration.ConnectionPool;
import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import com.example.lab7.model.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOUser implements DAO<User> {
    ConnectionPool connectionPool;

    private static final String INSERT_USER_QUERY = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

    private static final String SELECT_USER_BY_USERNAME_QUERY = "SELECT * FROM user WHERE username = ?";

    public boolean createUser(User user) {
        try (Connection connection = connectionPool.getConnection();

             PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

    public User loginUser(String username, String password) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_QUERY)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedUsername = resultSet.getString("username");
                    String storedPassword = resultSet.getString("password");
                    String storedRole = resultSet.getString("role");

                    if (password.equals(storedPassword)) {
                        return new User(storedUsername, storedPassword, storedRole);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    @Override
    public List<User> get(int id) throws DAOException, SQLException, ConnectionPoolException {
        return null;
    }

    @Override
    public List<User> getAll() throws DAOException, SQLException, ConnectionPoolException {
        return null;
    }

    @Override
    public void insert(User user, int id) throws DAOException, SQLException, ConnectionPoolException {

    }

    @Override
    public void update(User user, String[] params) throws DAOException, SQLException, ConnectionPoolException {

    }

    @Override
    public void delete(User user) throws DAOException {

    }
}