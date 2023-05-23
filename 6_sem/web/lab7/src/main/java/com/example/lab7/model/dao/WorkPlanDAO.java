package com.example.lab7.model.dao;

import com.example.lab7.Controller.configuration.ConnectionPool;
import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import com.example.lab7.model.entity.Worker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import com.example.lab7.model.entity.WorkPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Work Plan DAO implementation
 */
public class WorkPlanDAO implements DAO<WorkPlan> {
    /**
     * Amount of plans
     */
    public static int amount = 2;

    /**
     * Connection pool instance
     */
    ConnectionPool connectionPool;

    private final String selectAll = "select * from work_plan";
    private final String insertNewPlan = "insert into work_plan values (#1, #2, #3, #4)";

    public WorkPlanDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    @Override
    public void createTable() throws DAOException {}

    @Override
    public void dropTable() throws DAOException {}

    @Override
    public ArrayList<WorkPlan> get(int id) throws DAOException, SQLException {
        return null;
    }

    @Override
    public List<WorkPlan> getAll() throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(selectAll);
            ResultSet resultSet = statement.executeQuery();

            connectionPool.freeConnection(connection);

            ArrayList<WorkPlan> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new WorkPlan(resultSet.getInt("id"), resultSet.getString("name"),
                        new Worker(resultSet.getInt("id_worker"), "Worker",
                                resultSet.getInt("id_type"), true), resultSet.getInt("id_type")));
                ++amount;
            }

            connectionPool.freeConnection(connection);

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void insert(WorkPlan workPlan, int id) throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(insertNewPlan.replace(
                            "#1", String.valueOf(workPlan.id)).replace("#2", String.valueOf(
                            workPlan.worker.id)).replace("#3", String.valueOf(workPlan.id_type))
                    .replace("#4", "name"));

            statement.executeUpdate();

            connectionPool.freeConnection(connection);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void update(WorkPlan workPlan, String[] params) throws DAOException {

    }

    @Override
    public void delete(WorkPlan workPlan) throws DAOException {

    }
}
