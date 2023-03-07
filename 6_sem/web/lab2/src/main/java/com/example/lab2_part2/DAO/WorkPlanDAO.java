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
import com.example.lab2_part2.Model.WorkPlan;

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

    @Override
    public void createTable() throws DAOException {}

    @Override
    public void dropTable() throws DAOException {}

    @Override
    public ArrayList<WorkPlan> get(int id) throws DAOException, SQLException {
        return null;
    }

    @Override
    public List<WorkPlan> getAll() throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = null;
        List<WorkPlan> result = new ArrayList<>();

        try {
            entityManager = JPAController.getEntityManager();

            result = entityManager.createNamedQuery("WorkPlan.selectAll", WorkPlan.class).getResultList();

            logger.info("All work plans returned");

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
    public void insert(WorkPlan workPlan, int id) throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = JPAController.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            WorkPlan wp = new WorkPlan();
            transaction.begin();
            // entityManager.createNamedQuery("WorkPlan.insertNewPlan")
            //     .setParameter("name", "name")
            //     .setParameter("id_worker", workPlan.worker)
            //     .setParameter("id_type", workPlan.id_type)
            //     .executeUpdate();
            wp.name = "name";
            wp.id_type = workPlan.id_type;
            wp.worker = workPlan.worker;
            entityManager.persist(wp);
            transaction.commit();

            logger.info("Insert into work_plan is successful");
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
    public void update(WorkPlan workPlan, String[] params) throws DAOException {

    }

    @Override
    public void delete(WorkPlan workPlan) throws DAOException {

    }
}
