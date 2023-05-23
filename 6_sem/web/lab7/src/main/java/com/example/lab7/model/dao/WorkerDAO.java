package com.example.lab7.model.dao;

import com.example.lab7.Controller.configuration.ConnectionPool;
import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import com.example.lab7.model.entity.Worker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

/**
 * Worker DAO class implementation
 */
public class WorkerDAO implements DAO<Worker> {

    ConnectionPool connectionPool;

    private final String selectStatement = "select * from worker where id_type = #1 and busy = false";
    private final String updateBusy = "update worker set busy=true where id = #1";
    private final String allWorkers = "select * from worker";

    public WorkerDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    /**
     * Override get method
     * @param id_type object id
     * @return workers
     * @throws DAOException e
     * @throws SQLException e
     */
    @Override
    public List<Worker> get(int id_type) throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(selectStatement.replace("#1", String.valueOf(id_type)));
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Worker> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Worker(resultSet.getInt("id"), "Name2",
                        resultSet.getInt("id_type"), resultSet.getBoolean("busy")));
            }

            connectionPool.freeConnection(connection);

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<Worker> getAll() throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(allWorkers);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Worker> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Worker(resultSet.getInt("id"), "Name3",
                        resultSet.getInt("id_type"), resultSet.getBoolean("busy")));
            }

            connectionPool.freeConnection(connection);

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void insert(Worker worker, int id) throws DAOException {

    }

    @Override
    public void update(Worker worker, String[] params) throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(updateBusy.replace("#1",
                    String.valueOf(worker.id)));

            statement.executeUpdate();

            connectionPool.freeConnection(connection);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Worker worker) throws DAOException {

    }
}
