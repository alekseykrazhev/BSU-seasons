package View;

import Controller.AccountsLoader;
import Model.Bank;

/**
 *  Class that starts all the work with bank application.
 *  Creates Bank instance and starts main menu.
 * @author Aleksey Krazhevskiy
 * @version 1.0
 */
public class Main {
    /**
     * Main method that starts all the work of the program.
     * @param args - console arguments
     */
    public static void main(String[] args) {
        Bank bank;
        bank = AccountsLoader.initializationAccounts();
        BankOperations.menu(bank);
    }
}