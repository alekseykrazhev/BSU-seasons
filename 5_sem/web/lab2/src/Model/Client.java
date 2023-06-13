package Model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents and stares Client information.
 * @version 1.0
 * @author Aleksey Krazhevskiy
 */
public class Client {

    /**
     * Stares clients name
     */
    private String name;

    /**
     * The list of clients accounts
     */
    private ArrayList<AccountAbstract> listOfClientAccounts;

    /**
     * Name getter
     * @return name of client
     */
    public String getName() { return name; }

    /**
     * Name setter
     * @param name - new clients name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Accounts setter
     * @param newList - new list of clients
     */
    public void SetAccounts(ArrayList<AccountAbstract> newList) {
        this.listOfClientAccounts = newList;
    }

    /**
     * Accounts getter
     * @return list of clients
     */
    public ArrayList<AccountAbstract> getAccounts() { return listOfClientAccounts; }

    /**
     * Class main constructor
     * @param name - clients name
     * @param account - clients account to add
     */
    public Client(String name, AccountAbstract account) {
        this.name = name;
        listOfClientAccounts = new ArrayList<>();
        listOfClientAccounts.add(account);
    }

    /**
     * Method to prints to console
     * @return string implementation
     */
    public String toString() {
        StringBuilder out = new StringBuilder("---------------------------------------------------\n Client: " + getName());
        for (var account: listOfClientAccounts) {
            out.append("\n").append(account).append("\n");
        }
        out.append("---------------------------------------------------\n");
        return out.toString();
    }

    /**
     * Get hash code of an object
     * @return hash code
     */
    @Override
    public int hashCode() { return Objects.hash(name, listOfClientAccounts); }

    /**
     * Equals implementation
     * @param o - object(Client)
     * @return true/false
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(listOfClientAccounts, client.listOfClientAccounts);
    }
}
