package Model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents work of a bank
 * @author Aleksey Krazhevskiy
 * @version 1.0
 */
public class Bank {

    /**
     * Accounts list
     */
    private ArrayList<AccountAbstract> listOfAccounts;

    /**
     * Clients list
     */
    private ArrayList<Client> listOfClients;

    /**
     * Main (default) constructor
     */
    public Bank() {
        listOfAccounts = new ArrayList<>();
        listOfClients = new ArrayList<>();
    }

    /**
     * Accounts list setter
     * @param list - list to set
     */
    public void setListOfAccounts (ArrayList<AccountAbstract> list) { this.listOfAccounts = list; }

    /**
     * Clients list setter
     * @param list - list to set
     */
    public void setListOfClients (ArrayList<Client> list) {  this.listOfClients = list;  }

    /**
     * Accounts list getter
     * @return - list of accounts
     */
    public ArrayList<AccountAbstract> getListOfAccounts () { return listOfAccounts; }

    /**
     * Clients list getter
     * @return list of clients
     */
    public ArrayList<Client> getListOfClients() { return listOfClients; }

    /**
     * Equals operation implementation
     * @param o - Bank
     * @return - true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(listOfAccounts, bank.listOfAccounts) &&
                Objects.equals(listOfClients, bank.listOfClients);
    }

    /**
     * Get bank hash code
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(listOfAccounts, listOfClients);
    }

    /**
     * To string implementation
     * @return string
     */
    @Override
    public String toString() {
        return "Bank{" +
                "listAccountsBank=" + listOfAccounts +
                ", listClients=" + listOfClients +
                '}';
    }
}
