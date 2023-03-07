package com.example.lab2_part2.Controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.lab2_part2.Connector.ConnectionPool;
import com.example.lab2_part2.DAO.ApplicationDAO;
import com.example.lab2_part2.DAO.DispatcherDAO;
import com.example.lab2_part2.DAO.TenantDAO;
import com.example.lab2_part2.DAO.WorkPlanDAO;
import com.example.lab2_part2.DAO.WorkerDAO;
import com.example.lab2_part2.Exception.DAOException;
import com.example.lab2_part2.Model.WorkPlan;
import com.example.lab2_part2.Model.Worker;

/**
 * Controller implementation
 */
public class Controller {

    /**
     * Main logger
     */
    private static final Logger logger = LogManager.getLogger(Controller.class);

    /**
     * DAO instances
     */
    static ApplicationDAO applicationDAO;
    static DispatcherDAO dispatcherDAO;
    static TenantDAO tenantDAO;
    static WorkerDAO workerDAO;
    static WorkPlanDAO workPlanDAO;

    /**
     * Method to initialize DAO entities
     * @throws Exception
     */
    private static void initDAO() throws Exception {
        applicationDAO = new ApplicationDAO();
        dispatcherDAO = new DispatcherDAO();
        tenantDAO = new TenantDAO();
        workerDAO = new WorkerDAO();
        workPlanDAO = new WorkPlanDAO();
    }

    /**
     * Show work
     * @throws Exception e
     * @throws DAOException e
     */
    public static void doWork() throws Exception, DAOException {
        initDAO();
        logger.info("DAOs init successful.");
        int option = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("""
                        Choose option:
                        1: List all applications;
                        2: List workers for a job;
                        3: List expired applications;
                        4: Select a worker or decline application;
                        5: List all work plans;
                        6: List all workers;
                        7: Exit""");

                option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println(applicationDAO.getAll() + "\n");
                        logger.info("First option successful");
                    }
                    case 2 -> {
                        System.out.println("Enter work type ID:");
                        int id_type = scanner.nextInt();
                        System.out.println(workerDAO.get(id_type) + "\n");
                        logger.info("Second option successful");
                    }
                    case 3 -> {
                        System.out.println(applicationDAO.get(-1) + "\n");
                        logger.info("Third option successful");
                    }
                    case 4 -> {
                        System.out.println(applicationDAO.getAll() + "\n");

                        System.out.println("Select application ID:\n");
                        int app_id = scanner.nextInt();

                        List<Worker> workers1= workerDAO.get(app_id);

                        if (workers1.isEmpty()) {
                            System.out.println("No available workers for this job, closing application.");
                            logger.info("No available workers for this job, closing application.");
                            continue;
                        }
                        System.out.println("Available workers:\n");
                        System.out.println(workers1 + "\n");

                        workerDAO.update(workers1.get(0), null);

                        WorkPlan workPlan = new WorkPlan(WorkPlanDAO.amount + 1, "NewPlan",
                                workers1.get(0), app_id);
                        workPlanDAO.insert(workPlan, app_id);

                        logger.info("Fourth option successful");
                    }
                    case 5 -> {
                        System.out.println(workPlanDAO.getAll() + "\n");
                        logger.info("Fifth option successful");
                    }
                    case 6 -> {
                        System.out.println(workerDAO.getAll());
                        logger.info("Option six");
                    }
                    case 7 -> {
                        ConnectionPool.closeConnections();
                        logger.info("Connection closed");
                        System.out.println("Good-bye!");
                        return;
                    }
                    default -> {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
