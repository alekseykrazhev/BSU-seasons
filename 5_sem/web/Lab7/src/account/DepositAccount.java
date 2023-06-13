package account;

public class DepositAccount extends Account {

    /**
     * Constructor with parameters
     * @param accountNumber
     * @param amountMoney
     * @param blocked
     */
    public DepositAccount(String accountNumber, int amountMoney, boolean blocked) {
        super(accountNumber, amountMoney, blocked);
    }

    /**
     * default constructor
     */
    public DepositAccount(){
        super("456", 456, false);
    }

    /**
     * override method toString
     * @return object depositAccount - compartment in string
     */
    @Override
    public String toString() {
        return "\n\n***************\nType: Deposit account\n"+super.toString();
    }
}
