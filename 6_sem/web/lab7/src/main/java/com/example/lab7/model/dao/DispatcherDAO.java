package com.example.lab7.model.dao;

import com.example.lab7.Controller.configuration.ConnectionPool;
import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import jakarta.persistence.EntityManager;
import com.example.lab7.model.entity.Dispatcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Dispatcher DAO implementation
 */
public class DispatcherDAO implements DAO<Dispatcher> {
    /**
     * Main logger
     */

    ConnectionPool connectionPool;

    private final String selectAll = "select * from dispatcher";

    public DispatcherDAO () throws Exception {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    @Override
    public List<Dispatcher> get(int id_type) throws DAOException, SQLException {
        return null;
    }

    /**
     * Override getAll method
     * @return all
     * @throws DAOException e
     * @throws SQLException e
     */
    @Override
    public List<Dispatcher> getAll() throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(selectAll);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Dispatcher> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Dispatcher(resultSet.getInt("id"), "Name"));
            }

            connectionPool.freeConnection(connection);

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void insert(Dispatcher dispatcher, int id) throws DAOException {

    }

    @Override
    public void update(Dispatcher dispatcher, String[] params) throws DAOException {

    }

    @Override
    public void delete(Dispatcher dispatcher) throws DAOException {

    }
}
