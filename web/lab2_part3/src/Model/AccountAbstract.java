package Model;

import java.util.Objects;

/**
 * Class to store info about account
 * @version 1.0
 * @author Aleksey Krazhevskiy
 */
public abstract class AccountAbstract {
    /**
     * Account number
     */
    protected String accountNumber;

    /**
     * Amount of money
     */
    protected int moneyAmount;

    /**
     * Blocked or not
     */
    protected boolean isBlocked;

    /**
     * Default constructor
     */
    public AccountAbstract() {
        accountNumber = null;
        moneyAmount = 0;
        isBlocked = false;
    }

    /**
     * Main constructor
     * @param accountNumber - number of accounts
     * @param moneyAmount - amount of money
     * @param blocked - is blocked?
     */
    public AccountAbstract(String accountNumber, int moneyAmount, boolean blocked){
        this.accountNumber = accountNumber;
        this.moneyAmount = moneyAmount;
        this.isBlocked = blocked;
    }

    /**
     * Getter for account number
     * @return acc num
     */
    public abstract String getAccountNumber();

    /**
     * Setter for account number
     * @param accountNumber - acc num
     */
    public abstract void setAccountNumber(String accountNumber);

    /**
     * Getter for money amount
     * @return sum
     */
    public abstract int getMoneyAmount();

    /**
     * Setter for money amount
     * @param amountMoney - sum
     */
    public abstract void setMoneyAmount(int amountMoney);

    /**
     * Is blocked?
     * @return true/false
     */
    public abstract boolean isBlocked();

    /**
     * Set account blocked?
     * @param blocked - true/false
     */
    public abstract void setBlocked(boolean blocked);

    /**
     * To string implementation
     * @return string
     */
    @Override
    public String toString(){
        return "-----------------------------------------\n" + "Account number: " + getAccountNumber() + "\n" +
                "Accounts amount: " + getMoneyAmount() + "\n" +
                "Is blocked: " + isBlocked() + "\n" +
                "-----------------------------------------\n";
    }

    /**
     * Equals implementation
     * @param o - object
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountAbstract account = (AccountAbstract) o;
        return moneyAmount == account.moneyAmount &&
                isBlocked == account.isBlocked &&
                Objects.equals(accountNumber, account.accountNumber);
    }

    /**
     * Get hash code
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, moneyAmount, isBlocked);
    }
}
