package com.example.lab7.model.dao;

import com.example.lab7.Controller.configuration.ConnectionPool;
import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import com.example.lab7.model.entity.Dispatcher;
import com.example.lab7.model.entity.Tenant;
import jakarta.persistence.EntityManager;
import com.example.lab7.model.entity.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.ResultSet;
import java.util.List;


public class ApplicationDAO implements DAO<Application> {

    ConnectionPool connectionPool;

    private final String selectAll = "select * from application";

    private final String selectStatement = "select * from application where date < curdate()";

    private final String getIdType = "select * from application where id = #1";

    public ApplicationDAO () {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    @Override
    public List<Application> get(int id) throws DAOException, SQLException {
        try {
            Connection connection = connectionPool.getConnection();
            ArrayList<Application> result = new ArrayList<>();

            if (id == -1) {
                PreparedStatement statement = connection.prepareStatement(selectStatement);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    result.add(new Application(resultSet.getInt("id"), "Name1",
                            resultSet.getInt("id_type"), new Tenant(resultSet.getInt("tenant_id"), "SomeName"),
                            resultSet.getDate("date"), new Dispatcher(resultSet.getInt("dispatcher_id"), "name")));
                }

            } else {
                PreparedStatement statement = connection.prepareStatement(getIdType.replace("#1",
                        String.valueOf(id)));
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    result.add(new Application(resultSet.getInt("id"), "Name2",
                            resultSet.getInt("id_type"), new Tenant(resultSet.getInt("tenant_id"), "SomeName"),
                            resultSet.getDate("date"), new Dispatcher(resultSet.getInt("dispatcher_id"), "name")));
                }

                connectionPool.freeConnection(connection);
            }

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<Application> getAll() throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(selectAll);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Application> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Application(resultSet.getInt("id"), "Name1",
                        resultSet.getInt("id_type"), new Tenant(resultSet.getInt("tenant_id"), "SomeName"),
                        resultSet.getDate("date"), new Dispatcher(resultSet.getInt("dispatcher_id"), "name")));
            }

            connectionPool.freeConnection(connection);

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void insert(Application application, int id) throws DAOException {

    }

    @Override
    public void update(Application application, String[] params) throws DAOException {

    }

    @Override
    public void delete(Application application) throws DAOException {

    }
}
