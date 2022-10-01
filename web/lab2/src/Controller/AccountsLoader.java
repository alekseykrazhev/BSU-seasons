package Controller;

import Model.Account;
import Model.Bank;
import Model.Client;

import java.io.File;

/**
 * Class that works with Account class
 * @author Aleksey Krazhevskiy
 */
public class AccountsLoader {

    /**
     * Initializes saved data
     * @return bank data
     */
    public static Bank initializationAccounts() {
        Bank bank = new Bank();
        File file = new File("src/File/accounts.txt");
        String string = ServiceFile.readFile(file);
        String[] str = string.split("\\s");

        for (int i = 0; i < str.length; i += 4){

            Account account = new Account(str[i + 1], Integer.parseInt(str[i + 2]), Boolean.parseBoolean(str[i + 3]), true);
            Client client = new Client(str[i], account);

            ServiceBank.addAccount(bank, account);
            ServiceBank.addClient(bank, client);
        }
        return bank;
    }
}
