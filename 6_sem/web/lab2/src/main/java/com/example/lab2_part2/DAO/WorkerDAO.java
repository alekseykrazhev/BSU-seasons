package com.example.lab2_part2.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.lab2_part2.Connector.ConnectionPool;
import com.example.lab2_part2.Connector.JPAController;
import com.example.lab2_part2.Exception.ConnectionPoolException;
import com.example.lab2_part2.Exception.DAOException;
import com.example.lab2_part2.Model.Worker;

/**
 * Worker DAO class implementation
 */
public class WorkerDAO implements DAO<Worker> {
    /**
     * Main logger
     */
    private static final Logger logger = LogManager.getLogger(WorkerDAO.class);

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
    public List<Worker> get(int id_type) throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = null;
        List<Worker> result = new ArrayList<>();

        try {
            entityManager = JPAController.getEntityManager();

            result = entityManager.createNamedQuery("Worker.selectStatement", Worker.class).setParameter("id_type", id_type).getResultList();

            logger.info("Selected workers returned");

            return result;
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage(), e);
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public List<Worker> getAll() throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = null;
        List<Worker> result = new ArrayList<>();

        try {
            entityManager = JPAController.getEntityManager();

            result = entityManager.createNamedQuery("Worker.selectAll", Worker.class).getResultList();

            logger.info("All workers returned");

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
    public void insert(Worker worker, int id) throws DAOException {

    }

    @Override
    public void update(Worker worker, String[] params) throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = JPAController.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.createNamedQuery("Worker.updateWorkerBusy")
                .setParameter("busy", true)
                .setParameter("id", worker.id)
                .executeUpdate();
            transaction.commit();

            logger.info("Worker set busy");
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage(), e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    @Override
    public void delete(Worker worker) throws DAOException {

    }
}
