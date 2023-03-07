package DAO;

import Connector.ConnectionPool;
import Exception.ConnectionPoolException;
import Exception.DAOException;
import Model.WorkPlan;
import Model.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Work Plan DAO implementation
 */
public class WorkPlanDAO implements DAO<WorkPlan> {
    /**
     * Amount of plans
     */
    public static int amount = 2;

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(WorkerDAO.class);

    /**
     * Connection pool instance
     */
    ConnectionPool connectionPool;

    public WorkPlanDAO () {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    /**
     * SQL queries strings
     */
    private final String selectAll = "select * from work_plan";
    private final String insertNewPlan = "insert into work_plan values (#1, #2, #3)";

    @Override
    public void createTable() throws DAOException {}

    @Override
    public void dropTable() throws DAOException {}

    @Override
    public ArrayList<WorkPlan> get(int id) throws DAOException, SQLException {
        return null;
    }

    @Override
    public ArrayList<WorkPlan> getAll() throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(selectAll);
            ResultSet resultSet = statement.executeQuery();

            connectionPool.freeConnection(connection);

            ArrayList<WorkPlan> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new WorkPlan(resultSet.getInt("id"), "WorkPlan",
                        new Worker(resultSet.getInt("id_worker"), "Worker",
                                resultSet.getInt("id_type"), true), resultSet.getInt("id_type")));
                ++amount;
            }

            connectionPool.freeConnection(connection);

            logger.info("All work plans returned");

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
                    workPlan.worker.id)).replace("#3", String.valueOf(workPlan.id_type)));

            statement.executeUpdate();

            connectionPool.freeConnection(connection);

            logger.info("Insert into work_plan is successful");
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
