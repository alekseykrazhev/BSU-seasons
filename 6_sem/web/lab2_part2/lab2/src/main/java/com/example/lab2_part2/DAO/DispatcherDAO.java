package com.example.lab2_part2.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.lab2_part2.Connector.ConnectionPool;
import com.example.lab2_part2.Connector.JPAController;
import com.example.lab2_part2.Exception.ConnectionPoolException;
import com.example.lab2_part2.Exception.DAOException;
import com.example.lab2_part2.Model.Dispatcher;

/**
 * Dispatcher DAO implementation
 */
public class DispatcherDAO implements DAO<Dispatcher> {
    /**
     * Main logger
     */
    private static final Logger logger = LogManager.getLogger(DispatcherDAO.class);

    ConnectionPool connectionPool;

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
    public List<Dispatcher> get(int id_type) throws DAOException, SQLException {
        return null;
    }

    /**
     * Override getAll method
     * @return all
     * @throws DAOException e
     * @throws SQLException e
     */
    @Override
    public List<Dispatcher> getAll() throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = null;
        List<Dispatcher> result = new ArrayList<>();

        try {
            entityManager = JPAController.getEntityManager();

            entityManager = JPAController.getEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Dispatcher> criteriaQuery = criteriaBuilder.createQuery(Dispatcher.class);
            Root<Dispatcher> rootClient = criteriaQuery.from(Dispatcher.class);

            result = entityManager.createQuery(criteriaQuery).getResultList();

            logger.info("All dispatchers returned");

            return result;
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
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
