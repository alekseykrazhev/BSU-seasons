package Controller.sorting;

import Model.AccountAbstract;

import java.util.Comparator;

/**
 * Sorts accounts by client name
 * @author Aleksey Krazhevskiy
 */
public class SortedByAccountNumber implements Comparator<AccountAbstract> {
    /**
     * Compare implementation
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return true/false
     */
    @Override
    public int compare(AccountAbstract o1, AccountAbstract o2){
        String owner1 = o1.getAccountNumber();
        String owner2 = o2.getAccountNumber();

        return owner1.compareTo(owner2);
    }
}
