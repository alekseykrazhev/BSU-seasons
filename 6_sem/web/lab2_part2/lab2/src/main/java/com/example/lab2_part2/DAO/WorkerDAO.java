package com.example.lab2_part2.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Worker> criteriaQuery = criteriaBuilder.createQuery(Worker.class);
            Root<Worker> rootClient = criteriaQuery.from(Worker.class);

            Predicate cond = criteriaBuilder.equal(rootClient.get(Worker_.ID_TYPE), id_type);
            Predicate cond1 = criteriaBuilder.equal(rootClient.get(Worker_.BUSY), false);
            criteriaQuery.where(cond, cond1);

            result = entityManager.createQuery(criteriaQuery).getResultList();
            
            // result = entityManager.createNamedQuery("Worker.selectStatement", Worker.class).setParameter("id_type", id_type).getResultList();

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

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Worker> criteriaQuery = criteriaBuilder.createQuery(Worker.class);
            Root<Worker> rootClient = criteriaQuery.from(Worker.class);

            result = entityManager.createQuery(criteriaQuery).getResultList();

            // result = entityManager.createNamedQuery("Worker.selectAll", Worker.class).getResultList();

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
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Worker> update = criteriaBuilder.createCriteriaUpdate(Worker.class);
            Root<Worker> rootBook = update.from(Worker.class);
            update.set(rootBook.get(Worker_.BUSY), true);
            Predicate condition = criteriaBuilder.equal(rootBook.get(Worker_.ID), worker.id);
            update.where(condition);

            transaction.begin();
            entityManager.createQuery(update).executeUpdate();
            // entityManager.createNamedQuery("Worker.updateWorkerBusy")
            //     .setParameter("busy", true)
            //     .setParameter("id", worker.id)
            //     .executeUpdate();
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
