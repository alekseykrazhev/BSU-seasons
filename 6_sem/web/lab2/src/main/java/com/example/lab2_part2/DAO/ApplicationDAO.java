package com.example.lab2_part2.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.lab2_part2.Connector.ConnectionPool;
import com.example.lab2_part2.Connector.JPAController;
import com.example.lab2_part2.Exception.ConnectionPoolException;
import com.example.lab2_part2.Exception.DAOException;
import com.example.lab2_part2.Model.Application;

public class ApplicationDAO implements DAO<Application> {

    private static final Logger logger = LogManager.getLogger(ApplicationDAO.class);

    ConnectionPool connectionPool;

    public ApplicationDAO () {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    @Override
    public List<Application> get(int id) throws DAOException, SQLException {
        EntityManager entityManager = null;
        List<Application> result = new ArrayList<>();

        try {
            entityManager = JPAController.getEntityManager();

            if (id == -1) {
                Calendar cal = Calendar.getInstance();
                result = entityManager.createNamedQuery("Application.selectStatement", Application.class)
                    .setParameter("curdate", cal.getTime())
                    .getResultList();
                logger.info("Expired applications returned");
            } 
            else {
                result = entityManager.createNamedQuery("Application.getIdType", Application.class)
                    .setParameter("id", id)
                    .getResultList();

                logger.info("Selected applications returned");
            }

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
    public List<Application> getAll() throws DAOException, SQLException, ConnectionPoolException {
        EntityManager entityManager = null;
        List<Application> result = new ArrayList<>();

        try {
            entityManager = JPAController.getEntityManager();

            result = entityManager.createNamedQuery("Application.selectAll", Application.class).getResultList();

            logger.info("All applications returned");

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
    public void insert(Application application, int id) throws DAOException {

    }

    @Override
    public void update(Application application, String[] params) throws DAOException {

    }

    @Override
    public void delete(Application application) throws DAOException {

    }
}
