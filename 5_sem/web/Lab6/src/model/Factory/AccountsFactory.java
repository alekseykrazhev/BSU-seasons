package model.Factory;

import model.Accounts.Account;
import model.Accounts.CardAccount;
import model.Accounts.DepositAccount;
import model.Accounts.PersonalAccount;

public class AccountsFactory {

    /**
     * Constructor with 1 parameter
     * @param type - type of account
     * @return new object
     */
    public static Account newAccount(AccountType type) {
        return switch (type) {
            case CARD_ACCOUNT -> new CardAccount();
            case DEPOSIT_ACCOUNT -> new DepositAccount();
            case PERSONAL_ACCOUNT -> new PersonalAccount();
        };
    }

    /**
     * Constructor with many parameters
     */
    public static Account newAccount(AccountType type, String accountNumber, int amountMoney, boolean blocked) {
        return switch (type) {
            case CARD_ACCOUNT -> new CardAccount(accountNumber, amountMoney, blocked);
            case DEPOSIT_ACCOUNT -> new DepositAccount(accountNumber, amountMoney, blocked);
            case PERSONAL_ACCOUNT -> new PersonalAccount(accountNumber, amountMoney, blocked);
        };
    }
}