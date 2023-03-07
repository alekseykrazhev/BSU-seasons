package View;

import Controller.Controller;
import Exception.DAOException;

/**
 * Main class
 */
public class Main {
    /**
     * Main method to show functionality
     * @param args - comm. line args
     * @throws Exception e
     * @throws DAOException daoe
     */
    public static void main(String[] args) throws Exception, DAOException {
        Controller.doWork();
    }

}