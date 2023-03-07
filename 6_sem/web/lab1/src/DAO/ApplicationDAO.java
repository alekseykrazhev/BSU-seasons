package DAO;

import Connector.Connector;
import Model.Application;
import Exception.DAOException;
import Model.Dispatcher;
import Model.Tenant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ApplicationDAO implements DAO<Application> {

    private static final Logger logger = LogManager.getLogger(ApplicationDAO.class);

    private final String selectAll = "select * from application";

    private final String selectStatement = "select * from application where date < curdate()";

    private final String getIdType = "select * from application where id = #1";

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    @Override
    public ArrayList<Application> get(int id) throws DAOException, SQLException {
        Connection connection = Connector.getConnection();

        ArrayList<Application> result = new ArrayList<>();

        if (id == -1) {
            PreparedStatement statement = connection.prepareStatement(selectStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(new Application(resultSet.getInt("id"), "Name1",
                        resultSet.getInt("id_type"), new Tenant(resultSet.getInt("tenant_id"), "SomeName"),
                        resultSet.getDate("date"), new Dispatcher(resultSet.getInt("dispatcher_id"), "name")));
            }

            logger.info("Expired applications returned");
        } else {
            PreparedStatement statement = connection.prepareStatement(getIdType.replace("#1",
                    String.valueOf(id)));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(new Application(resultSet.getInt("id"), "Name2",
                        resultSet.getInt("id_type"), new Tenant(resultSet.getInt("tenant_id"), "SomeName"),
                        resultSet.getDate("date"), new Dispatcher(resultSet.getInt("dispatcher_id"), "name")));
            }

            logger.info("Selected applications returned");
        }

        return result;
    }

    @Override
    public ArrayList<Application> getAll() throws DAOException, SQLException {
        Connection connection = Connector.getConnection();

        PreparedStatement statement = connection.prepareStatement(selectAll);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Application> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Application(resultSet.getInt("id"), "Name1",
                    resultSet.getInt("id_type"), new Tenant(resultSet.getInt("tenant_id"), "SomeName"),
                    resultSet.getDate("date"), new Dispatcher(resultSet.getInt("dispatcher_id"), "name")));
        }

        logger.info("All applications returned");

        return result;
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
