package Controller;

import Connector.ConnectionPool;
import DAO.ApplicationDAO;
import DAO.WorkPlanDAO;
import DAO.WorkerDAO;
import Exception.DAOException;
import Model.WorkPlan;
import Model.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller implementation
 */
public class Controller {

    static int id_count = 0;

    /**
     * Main logger
     */
    private static final Logger logger = LogManager.getLogger(Controller.class);

    /**
     * Show work
     * @throws Exception e
     * @throws DAOException e
     */
    public static void doWork() throws Exception, DAOException {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
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
                    ApplicationDAO app = new ApplicationDAO();
                    System.out.println(app.getAll() + "\n");
                    logger.info("First option successful");
                }
                case 2 -> {
                    WorkerDAO workerDAO = new WorkerDAO();
                    System.out.println("Enter work type ID:");
                    int id_type = scanner.nextInt();
                    System.out.println(workerDAO.get(id_type) + "\n");
                    logger.info("Second option successful");
                }
                case 3 -> {
                    ApplicationDAO app = new ApplicationDAO();
                    System.out.println(app.get(-1) + "\n");
                    logger.info("Third option successful");
                }
                case 4 -> {
                    ApplicationDAO apps = new ApplicationDAO();
                    System.out.println(apps.getAll() + "\n");

                    System.out.println("Select application ID:\n");
                    int app_id = scanner.nextInt();

                    WorkerDAO workers = new WorkerDAO();
                    ArrayList<Worker> workers1= workers.get(app_id);

                    if (workers1.isEmpty()) {
                        System.out.println("No available workers for this job, closing application.");
                        continue;
                    }
                    System.out.println("Available workers:\n");
                    System.out.println(workers1 + "\n");

                    workers.update(workers1.get(0), null);

                    WorkPlanDAO planDAO = new WorkPlanDAO();
                    WorkPlan workPlan = new WorkPlan(id_count, "NewPlan",
                            workers1.get(0), app_id);
                    planDAO.insert(workPlan, app_id);
                    ++id_count;

                    logger.info("Fourth option successful");
                }
                case 5 -> {
                    WorkPlanDAO workPlanDAO = new WorkPlanDAO();
                    System.out.println(workPlanDAO.getAll() + "\n");
                    logger.info("Fifth option successful");
                }
                case 6 -> {
                    WorkerDAO workers = new WorkerDAO();
                    System.out.println(workers.getAll());
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
    }
}
