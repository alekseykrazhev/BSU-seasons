package DAO;

import Connector.ConnectionPool;
import Exception.ConnectionPoolException;
import Exception.DAOException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO interface defining main methods
 * @param <T> type of DAO
 */
public interface DAO<T> {
    ConnectionPool connectionPull = null;

    /**
     * Table creation method
     */
    void createTable() throws DAOException;
    /**
     * Table removing method
     */
    void dropTable() throws DAOException;

    /**
     * Method of getting an object
     * @param id object id
     * @return object of type T
     */
    ArrayList<T> get(int id) throws DAOException, SQLException, ConnectionPoolException;

    /**
     * Method of getting an objects
     * @return object list of type T
     * @throws DAOException
     */
    ArrayList<T> getAll() throws DAOException, SQLException, ConnectionPoolException;

    /**
     * Object insertion method
     * @param t object of type T
     * @throws DAOException
     */
    void insert(T t, int id) throws DAOException, SQLException, ConnectionPoolException;

    /**
     * Object updating method
     * @param t object of type T
     * @param params other params
     * @throws DAOException
     */
    void update(T t, String[] params) throws DAOException, SQLException, ConnectionPoolException;

    /**
     * Object deleting method
     * @param t object of type T
     * @throws DAOException
     */
    void delete(T t) throws DAOException;
}
