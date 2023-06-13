package model.Comparators;

import model.Accounts.Account;

import java.util.Comparator;

/**
 * Comparator class for sorting by amount of money
 */
public class AmountMoneyComparator implements Comparator<Account> {

    @Override
    public int compare(Account obj1, Account obj2) {
        return Integer.compare(obj1.GetAmountMoney(), obj2.GetAmountMoney());
    }

}
