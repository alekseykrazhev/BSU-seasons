package Model;

/**
 * Account implementation class
 * @author Aleksey Krazhevskiy
 * @version 1.0
 */
public class Account extends AccountAbstract {

    /**
     * Is acc vip or not
     */
    private boolean vip;

    /**
     * Default constructor
     */
    public Account(){
        super();
        this.vip = true;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public int getMoneyAmount() {
        return moneyAmount;
    }

    @Override
    public void setMoneyAmount(int amountMoney) {
        this.moneyAmount = amountMoney;
    }

    @Override
    public boolean isBlocked() {
        return isBlocked;
    }

    @Override
    public void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }

    /**
     * Main constructor
     * @param accountNumber number of account
     * @param moneyAmount amount of money
     * @param blocked is blocked?
     * @param vip vip?
     */
    public Account(String accountNumber, int moneyAmount, boolean blocked, boolean vip) {
        super(accountNumber, moneyAmount, blocked);
        this.vip = vip;
    }

}
