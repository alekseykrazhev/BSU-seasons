package DAO;

import Connector.ConnectionPool;
import Exception.ConnectionPoolException;
import Exception.DAOException;
import Model.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Worker DAO class implementation
 */
public class WorkerDAO implements DAO<Worker> {
    /**
     * Main logger
     */
    private static final Logger logger = LogManager.getLogger(WorkerDAO.class);

    /**
     * String sql queries
     */
    private final String selectStatement = "select * from worker where id_type = #1 and busy = false";
    private final String updateBusy = "update worker set busy=true where id = #1";
    private final String allWorkers = "select * from worker";

    ConnectionPool connectionPool;

    public WorkerDAO () {
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
    public ArrayList<Worker> get(int id_type) throws DAOException, SQLException, ConnectionPoolException {
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

            logger.info("Selected workers returned");

            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Worker> getAll() throws DAOException, SQLException, ConnectionPoolException {
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

            logger.info("All workers returned");

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

            logger.info("Worker set busy");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Worker worker) throws DAOException {

    }
}
