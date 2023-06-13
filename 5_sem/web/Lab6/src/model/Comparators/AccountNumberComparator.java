package model.Comparators;

import model.Accounts.Account;

import java.util.Comparator;

/**
 * Comparator class for sorting by number of account
 */
public class AccountNumberComparator implements Comparator<Account> {

    @Override
    public int compare(Account obj1, Account obj2) {
        return Integer.compare(Integer.parseInt(obj1.GetAccountNumber()), Integer.parseInt(obj2.GetAccountNumber()));
    }

}
