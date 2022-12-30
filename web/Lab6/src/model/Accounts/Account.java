package model.Accounts;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract class for describing some common methods for different types of accounts
 * @author Vadim Nikolski
 */
public abstract class Account implements Comparable<Account>, Serializable {
    /**The number of account*/
    private String accountNumber;
    /**The amount of money*/
    private int amountMoney;
    /**Account blocking(true - blocked)*/
    private boolean blocked;

    /**
     * default constructor
     */
    public Account() {
        this.accountNumber = null;
        this.amountMoney = 0;
        this.blocked = false;
    }

    /**
     * Constructor with parameters
     * @param accountNumber - number of account
     * @param amountMoney - amount of money
     * @param blocked - is account blocked
     */
    public Account(String accountNumber, int amountMoney, boolean blocked){
        this.accountNumber = accountNumber;
        this.amountMoney = amountMoney;
        this.blocked = blocked;
    }

    /**
     * GetMethod for private field account number
     * @return number of account
     */
    public String GetAccountNumber() {
        return accountNumber;
    }

    /**
     * SetMethod for private field accountNumber
     * @param accountNumber - new value for accountNumber
     */
    public void SetAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * GetMethod for private field amountMoney
     * @return amount of money
     */
    public int GetAmountMoney() {
        return amountMoney;
    }

    /**
     * SetMethod for private field amountMoney
     * @param amountMoney - new value for amountMoney
     */
    public void SetAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    /**
     * GetMethod for private field isBlocked
     * @return is account blocked
     */
    public boolean IsBlocked() {
        return blocked;
    }

    /**
     * SetMethod for private field blocked
     * @param blocked - new value for blocked
     */
    public void SetBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String toString(){
        return "---------------------------------------------" + "\n" +
                "Number of account: " + GetAccountNumber() + "\n" +
                "Amount of account: " + GetAmountMoney() + "\n" +
                "Blocked: " + IsBlocked() + "\n" +
                "---------------------------------------------";
    }

    /**
     * Override method equals
     * @param obj object to compare with
     * @return if equals true, else - false
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass()){
            return false;
        }
        if(this == obj){
            return true;
        }
        Account acc = (Account) obj;
        return (acc.amountMoney == ((Account) obj).amountMoney && Objects.equals(acc.accountNumber, ((Account) obj).accountNumber));
    }

    /**
     * Method that compare two accounts
     * @param o object to compare with
     * @return 0 if is equal, -1 if less, 1 is more
     */
    @Override
    public int compareTo(Account o) {
        return Integer.compare(this.amountMoney, o.amountMoney);
    }
}