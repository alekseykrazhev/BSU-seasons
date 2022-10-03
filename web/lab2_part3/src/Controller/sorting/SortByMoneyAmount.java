package Controller.sorting;

import Model.AccountAbstract;

import java.util.Comparator;

/**
 * Sorts account by money amount
 * @author Aleksey Krazhevskiy
 */
public class SortByMoneyAmount implements Comparator<AccountAbstract> {
    /**
     * Compare implementation
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return true/false
     */
    @Override
    public int compare(AccountAbstract o1, AccountAbstract o2){
        int money1 = o1.getMoneyAmount();
        int money2 = o2.getMoneyAmount();

        return Integer.compare(money1, money2);
    }
}
