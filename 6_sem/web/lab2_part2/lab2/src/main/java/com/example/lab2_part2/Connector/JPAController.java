package com.example.lab2_part2.Connector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Java Persistence Api controller class
 */
public class JPAController {
    /**
     * Persistence name
     */
    private static final String persistenceName = "com.application.zhkh";

    /**
     * Create entity manager object
     */
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);

    /**
     * Get method for entity manager
     * @return entity manager object
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Close entity manager
     */
    public static void close() {
        entityManagerFactory.close();
    }
}
