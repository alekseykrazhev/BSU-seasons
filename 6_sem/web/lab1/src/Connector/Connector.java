package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for creating a connection using JDBC
 */
public class Connector {
    /**
     * Logger initialization
     */
    private static final Logger logger = LogManager.getLogger(Connector.class);

    /**
     * Initialize connection
     */
    private static Connection connect = null;

    /**
     * Initialize statement
     */
    private static Statement statement = null;

    /**
     * Initialize resultset
     */
    private static ResultSet resultSet = null;

    /**
     * Method to read from database
     * @throws Exception - connection error
     */
    public static Connection connect() throws Exception {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");

        try {
            // Class.forName(driver);

            connect = DriverManager
                    .getConnection(url, user, password);

            logger.info("Connection created");
            System.out.println("Connection created");

            return connect;

        } catch (Exception e) {
            logger.info(e.getMessage());
            close();
            throw e;
        }
    }

    public static Connection getConnection() {
        if (connect == null) {
            connect = Connector.connect;
        }

        return connect;
    }

    /**
     * Method to write data of a table
     * @param resultSet - resultset
     * @throws SQLException - exception
     */
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    /**
     * Close connection
     */
    public static void close() throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }

            logger.info("Connection closed");
            System.out.println("Connection closed");

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }
}
