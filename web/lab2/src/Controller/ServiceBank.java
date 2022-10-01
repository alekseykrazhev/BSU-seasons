package Controller;

import Model.Account;
import Model.AccountAbstract;
import Model.Bank;
import Model.Client;
import View.BankOperations;

import java.util.ArrayList;

/**
 * Class that works with data of Bank
 * @author Aleksey Krazhevskiy
 */
public class ServiceBank {
    /**
     * Add account to bank
     * @param bank - bank
     * @param account - acc
     */
    public static void addAccount(Bank bank, Account account) {
        if (bank.getListOfAccounts().isEmpty()){
            ArrayList<AccountAbstract> list = new ArrayList<>();
            list.add(account);
            bank.setListOfAccounts(list);
        }
        else {
            bank.getListOfAccounts().add(account);
        }
    }

    /**
     * Add client to bank
     * @param bank - bank
     * @param client - client
     * @return clients
     */
    public static ArrayList<Client> addClient(Bank bank, Client client) {
        if (bank.getListOfClients().isEmpty()){
            ArrayList<Client> list = new ArrayList<>();
            list.add(client);
            bank.setListOfClients(list);
        }
        else {
            bank.getListOfClients().add(client);
        }
        return bank.getListOfClients();
    }

    /**
     * Change amount of money in the bank
     * @param bank - bank
     * @param sign +/-
     */
    public static void changeAmountMoney(Bank bank, int sign) {
        String numberAccount;
        System.out.print("Enter number of account: ");
        numberAccount = BankOperations.inputString();
        for (AccountAbstract account: bank.getListOfAccounts()) {
            if (account.getAccountNumber().equals(numberAccount)){
                if (account.isBlocked()){
                    System.out.println("AccountAbstract is blocked");
                    break;
                }
                else {
                    System.out.print("Enter the amount of money:");
                    account.setMoneyAmount(account.getMoneyAmount() + sign*BankOperations.inputNumber());
                    break;
                }
            }
        }
    }

    /**
     * Unblock bank account
     * @param bank - bank
     * @return accounts
     */
    public static ArrayList<AccountAbstract> unblock(Bank bank) {
        String numberAccount;
        System.out.print("Enter number of account for unblock: ");
        numberAccount = BankOperations.inputString();

        for (AccountAbstract account: bank.getListOfAccounts()) {
            if (account.getAccountNumber().equals(numberAccount)) {
                account.setBlocked(false);
                break;
            }
        }
        return bank.getListOfAccounts();
    }

    /**
     * Block bank account
     * @param bank - bank
     * @return accounts
     */
    public static ArrayList<AccountAbstract> block(Bank bank) {
        String numberAccount;
        System.out.print("Enter number of account for block: ");
        numberAccount = BankOperations.inputString();

        for (AccountAbstract account: bank.getListOfAccounts()) {
            if (account.getAccountNumber().equals(numberAccount)) {
                account.setBlocked(true);
                break;
            }
        }
        return bank.getListOfAccounts();
    }

    /**
     * Counting positive balance of accounts
     * @param bank - bank
     * @return sum
     */
    public static int countPositiveBalance(Bank bank) {
        int sumPositive = 0;
        for (AccountAbstract account : bank.getListOfAccounts()) {
            if (account.getMoneyAmount() > 0) {
                sumPositive += account.getMoneyAmount();
            }
        }
        return sumPositive;
    }

    /**
     * Counts negative balance of accounts
     * @param bank - bank
     * @return sum
     */
    public static int countNegativeBalance(Bank bank) {
        int sumNegative = 0;
        for (AccountAbstract account: bank.getListOfAccounts()) {
            if (account.getMoneyAmount() < 0) {
                sumNegative += account.getMoneyAmount();
            }
        }
        return sumNegative;
    }

    /**
     * Counts all balance
     * @param bank - bank
     * @return sum
     */
    public static int countMoney(Bank bank) {
        int sumAll = 0;
        for (AccountAbstract account : bank.getListOfAccounts()) {
            sumAll += account.getMoneyAmount();
        }
        return sumAll;
    }

    /**
     * Get account of bank
     * @param bank - bank
     * @param numberAccount - number of an account
     * @return acc
     */
    public static ArrayList<AccountAbstract> deleteAccount(Bank bank, String numberAccount) {
        for (AccountAbstract account: bank.getListOfAccounts()) {
            if (account.getAccountNumber().equals(numberAccount)) {
                bank.getListOfAccounts().remove(account);
                break;
            }
        }
        return bank.getListOfAccounts();
    }
}
