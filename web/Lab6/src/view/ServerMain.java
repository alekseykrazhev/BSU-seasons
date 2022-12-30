package view;

import controller.Controller;
import model.Bank;
import model.Remote.RemoteBank;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

/**
 * Main class for serves
 */
public class ServerMain {

    static final Logger logger = Logger.getLogger( ServerMain.class.getName());

    /**
     * Main server method
     */
    public static void main(String[] args) {
        try {
            Controller controller = new Controller();
            Bank bank = controller.createBank("Tolstogo 10");

            System.out.println("\nBank on " + bank.getAddress() + " is successfully created!");
            logger.info("\nBank on " + bank.getAddress() + " is successfully created!");

            //export remote object
            RemoteBank remote_bank = (RemoteBank) UnicastRemoteObject.exportObject(bank, 0);
            //create exemp remote objects reestr on local host, that gets requests on this port
            Registry reg = LocateRegistry.createRegistry(12321);
            reg.bind("Tolstogo 10", remote_bank);

        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
