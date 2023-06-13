package Controller;

import Model.AccountAbstract;
import Model.Bank;
import Model.Client;

import java.io.*;
import java.util.Scanner;

/**
 * Class to work with files
 * @author Aleksey Krazhevskiy
 */
public class ServiceFile {
    /**
     * Get data from file
     * @param file - file
     * @return data
     */
    public static String readFile(File file) {
        StringBuilder str = new StringBuilder();
        try(Scanner input = new Scanner(new BufferedReader(new FileReader(file)))){
            while(input.hasNext()) {
                str.append(input.nextLine()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.out.println(file.getName() + "not found");
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * Write data to file
     * @param bank - bank
     * @param file - file
     */
    public static void writeFile(Bank bank, File file) {
        String string = "";
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Client client: bank.getListOfClients()) {
                for (AccountAbstract account: client.getAccounts()) {
                    string = account.getAccountNumber() + " " + account.getMoneyAmount() + " " + account.isBlocked();
                }
                pw.println(client.getName() + " " + string);
            }
            System.out.println("The data was successfully written to a file accounts.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
