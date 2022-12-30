package controller;

import model.Accounts.Account;
import model.Bank;
import model.Comparators.AmountMoneyComparator;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class SortFind {

    /**
     * private field bank
     */
    private Bank bank;

    /**
     * GetMethod for field bank
     * @return
     */
    public Bank GetBank() {
        return bank;
    }

    /**
     * Constructor
     * @param bank
     */
    public SortFind(Bank bank) {
        this.bank = bank;
    }

    /**
     * Method that sorted array of accounts by amount of money
     */
    public void sortedByAmountMoney() {
        Collections.sort(bank.GetAccounts(), new AmountMoneyComparator());
    }

    /**
     * Method that find account according to amount money interval
     * @param amountMin
     * @param amountMax
     * @return new array
     */
    public CopyOnWriteArrayList<Account> findOnAmountMoney(int amountMin, int amountMax){
        CopyOnWriteArrayList<Account> result = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Account> Accounts = bank.GetAccounts();

        for(Account account: Accounts){
            if(account.GetAmountMoney() >= amountMin && account.GetAmountMoney() <= amountMax){
                result.add(account);
            }
        }
        return result;
    }

    /**
     * Method that find accounts according to number account interval
     * @param numberMin
     * @param numberMax
     * @return new array
     */
    public CopyOnWriteArrayList<Account> findOnAccountNumber(String numberMin, String numberMax){
        CopyOnWriteArrayList<Account> result = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Account> Accounts = bank.GetAccounts();

        for(Account account: Accounts){
            if(Integer.parseInt(account.GetAccountNumber()) >= Integer.parseInt(numberMin) && Integer.parseInt(account.GetAccountNumber()) <= Integer.parseInt(numberMax)){
                result.add(account);
            }
        }
        return result;
    }

}
