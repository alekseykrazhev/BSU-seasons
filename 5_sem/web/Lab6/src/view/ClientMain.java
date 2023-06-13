package view;

import model.Accounts.CardAccount;
import model.Remote.RemoteBank;
import java.util.logging.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Main client class
 */
public class ClientMain {

    static final Logger logger = Logger.getLogger( ClientMain.class.getName());

    private static RemoteBank remote_bank;

    /**
     * Main client method.
     */
    public static void main(String[] args) throws RemoteException {

        Registry reg;
        try {
            //get registry remote objects
            reg = LocateRegistry.getRegistry("localhost", 12321);
            //find by name
            remote_bank = (RemoteBank) reg.lookup("Tolstogo 10");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

        logger.info(remote_bank.getAddress());
        logger.info("Cost of this bank: " + remote_bank.CostBank());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter option:");
            System.out.println("1. Add account\n2.List accounts\n3.Amount of accounts\n4.Get cost of bank\n5.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter acc number:");
                    String num = sc.next();
                    System.out.println("Enter amount of money:");
                    int money = sc.nextInt();
                    remote_bank.addAccountToBank(new CardAccount(num, money, false));
                    break;
                case 2:
                    System.out.println(remote_bank.GetAccounts());
                    break;
                case 3:
                    System.out.println(remote_bank.getAmountOfAccounts());
                    break;
                case 4:
                    System.out.println(remote_bank.CostBank());
                    break;
                case 5:
                    return;
            }
        }
    }
}
