package Controller;

import Model.AccountAbstract;
import Model.Client;

/**
 * Class to work with data of client
 * @author Aleksey Krazhevskiy
 */
public class ServiceClient {

    /**
     * Add account
     * @param client - client
     * @param account - account
     */
    public static void addAccount(Client client, AccountAbstract account) {
        client.getAccounts().add(account);
    }

    /**
     * Delete account
     * @param client - client
     * @param account - account
     */
    public static void deleteAccount(Client client, AccountAbstract account) {
        client.getAccounts().remove(account);
    }

}
