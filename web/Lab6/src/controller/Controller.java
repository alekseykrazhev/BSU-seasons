package controller;

import model.Accounts.Account;
import model.Bank;
import model.Factory.AccountType;
import model.Factory.AccountsFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class Controller {
    static final Logger logger = Logger.getLogger( Controller.class.getName());

    public Bank createBank(String address) {
        Account acc1 = AccountsFactory.newAccount(AccountType.CARD_ACCOUNT);
        Account acc2 = AccountsFactory.newAccount(AccountType.PERSONAL_ACCOUNT);
        Account acc3 = AccountsFactory.newAccount(AccountType.DEPOSIT_ACCOUNT, "789", 500, false);
        return new Bank(new Account[] {acc1, acc2, acc3}, address);
    }


    public static void workProgram() {
        try{
            Account acc1 = AccountsFactory.newAccount(AccountType.DEPOSIT_ACCOUNT, "7987", 200, false);
        }
        catch (Exception ex1){
            System.out.println(ex1.getMessage());
            logger.info(ex1.getMessage());
        }

        try{
            Account acc2 = AccountsFactory.newAccount(AccountType.DEPOSIT_ACCOUNT, "9809", 300, false);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            logger.info(ex.getMessage());
        }

        try{
            Account acc3 = AccountsFactory.newAccount(AccountType.DEPOSIT_ACCOUNT, "9809", 300, false);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            logger.info(ex.getMessage());
        }

        Account acc1 = AccountsFactory.newAccount(AccountType.CARD_ACCOUNT);
        Account acc2 = AccountsFactory.newAccount(AccountType.PERSONAL_ACCOUNT);
        Account acc3 = AccountsFactory.newAccount(AccountType.DEPOSIT_ACCOUNT, "789", 500, false);
        Bank bank = new Bank(new Account[] {acc1, acc2, acc3}, "Tolstogo street");
        System.out.println(bank);
        logger.info(bank.toString());

        SortFind sortFind = new SortFind(bank);
        sortAmountMoney(sortFind);
        findOnAccountNumber(sortFind, "1", "3");
        findOnAmountMoney(sortFind, 100, 500);
    }

    /**
     * Method that shows sort of accounts by amount of money
     */
    public static void sortAmountMoney(SortFind sortFind) {
        sortFind.sortedByAmountMoney();
        CopyOnWriteArrayList<Account> arrayOfAccounts = sortFind.GetBank().GetAccounts();
        System.out.println("\nAccounts sorted by amount of money: ");
        logger.info("\nAccounts sorted by amount of money: ");
        for(Account account: arrayOfAccounts) {
            System.out.println(account);
            logger.info(account.toString());
        }
    }

    /**
     * Method that show accounts that found according to interval of amount of money
     */
    public static void findOnAmountMoney(SortFind sortFind, int min, int max) {
        if(min > max){
            throw new IllegalArgumentException("Min value can't be more than max!!!");
        }
        CopyOnWriteArrayList<Account> arrayOfAccounts = sortFind.findOnAmountMoney(min, max);
        System.out.println("\nAccounts with amount of money: ("+ min + "; "+ max +"):");
        logger.info("\nAccounts with amount of money: ("+ min + "; "+ max +"):");
        for(Account account: arrayOfAccounts) {
            System.out.println(account);
            logger.info(account.toString());
        }
    }

    /**
     * Method that show accounts that found according to interval of account number
     */
    public static void findOnAccountNumber(SortFind sortFind, String min, String max) {
        if(Integer.parseInt(min) > Integer.parseInt(max)){
            throw new IllegalArgumentException("\nMin value can't be more than max!!!");
        }
        CopyOnWriteArrayList<Account> arrayOfAccounts = sortFind.findOnAccountNumber(min, max);
        System.out.println("\nAccounts with number: ("+ min + "; "+ max +"):");
        logger.info("\nAccounts with number: ("+ min + "; "+ max +"):");
        for(Account account: arrayOfAccounts) {
            System.out.println(account);
            logger.info(account.toString());
        }
    }
}