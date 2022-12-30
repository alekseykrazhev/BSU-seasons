package model;

import model.Accounts.Account;
import model.Remote.RemoteBank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bank implements RemoteBank {

    /**
     * all accounts in bank
     */
    private final CopyOnWriteArrayList<Account> accounts;

    /**
     * address of bank
     */
    private final String address;

    /**
     * GetMethod for bank address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * GetMethod for amount of accounts
     * @return amount of accounts
     */
    public int getAmountOfAccounts() {
        return accounts.size();
    }

    /**
     * GetMethod for array of accounts
     * @return array of accounts
     */
    public CopyOnWriteArrayList<Account> GetAccounts() {
        return accounts;
    }

    /**
     * default constructor
     */
    public Bank() {
        accounts = new CopyOnWriteArrayList<>();
        address = "Tolstogo 10";
    }

    /**
     * Constructor with parameters
     * @param account - array of accounts
     * @param add - address of bank
     */
    public Bank(Account[] account, String add) {
        accounts = new CopyOnWriteArrayList<>();
        Collections.addAll(accounts, account);
        address = add;
    }

    /**
     * Constructor with parameters
     * @param account - Arraylist of accounts
     * @param add - address of bank
     */
    public Bank(ArrayList<Account> account, String add) {
        accounts = new CopyOnWriteArrayList<>();
        accounts.addAll(account);
        address = add;
    }
    /**
     * add new account to bank
     * @param account - new account
     */
    public void addAccountToBank(Account account){
        accounts.add(account);
    }

    /**
     * Override method toString for object taxis
     * @return object in string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("_____________BANK_____________\n\nAddress: " + address);
        for(Account account: accounts){
            result.append(account).append("\n");
        }
        return result.toString();
    }

    /**
     * Method that find sum of amountMoney of all accounts in bank
     * @return sum
     */
    public double CostBank() {
        double sum = 0;
        for(Account account: accounts){
            sum += account.GetAmountMoney();
        }
        return sum;
    }

}
