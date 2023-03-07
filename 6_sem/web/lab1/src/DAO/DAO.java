package DAO;

import Connector.Connector;
import Exception.DAOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * DAO interface defining main methods
 * @param <T> type of DAO
 */
public interface DAO<T> {
    Connector connection = null;

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
    ArrayList<T> get(int id) throws DAOException, SQLException;

    /**
     * Method of getting an objects
     * @return object list of type T
     * @throws DAOException
     */
    ArrayList<T> getAll() throws DAOException, SQLException;

    /**
     * Object insertion method
     * @param t object of type T
     * @throws DAOException
     */
    void insert(T t, int id) throws DAOException, SQLException;

    /**
     * Object updating method
     * @param t object of type T
     * @param params other params
     * @throws DAOException
     */
    void update(T t, String[] params) throws DAOException, SQLException;

    /**
     * Object deleting method
     * @param t object of type T
     * @throws DAOException
     */
    void delete(T t) throws DAOException;
}
