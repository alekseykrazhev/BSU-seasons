package DAO;

import Connector.ConnectionPool;
import Exception.ConnectionPoolException;
import Exception.DAOException;
import Model.Dispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Dispatcher DAO implementation
 */
public class DispatcherDAO implements DAO<Dispatcher> {

    /**
     * Main logger
     */
    private static final Logger logger = LogManager.getLogger(DispatcherDAO.class);

    /**
     * String sql queries
     */
    private final String selectAll = "select * from dispatcher";

    ConnectionPool connectionPool;

    // private final String addWorkerOrCancel = "select * from worker where busy=false and id_type=#1";


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
    public ArrayList<Dispatcher> get(int id_type) throws DAOException, SQLException {
        return null;
    }

    /**
     * Override getAll method
     * @return all
     * @throws DAOException e
     * @throws SQLException e
     */
    @Override
    public ArrayList<Dispatcher> getAll() throws DAOException, SQLException, ConnectionPoolException {
        try {
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(selectAll);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Dispatcher> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Dispatcher(resultSet.getInt("id"), "Name"));
            }

            connectionPool.freeConnection(connection);

            logger.info("All dispatchers returned");

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
