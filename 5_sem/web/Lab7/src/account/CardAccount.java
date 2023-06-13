package account;

public class CardAccount extends Account {

    /**
     * Constructor with parameters
     * @param accountNumber
     * @param amountMoney
     * @param blocked
     */
    public CardAccount(String accountNumber, int amountMoney, boolean blocked) {
        super(accountNumber, amountMoney, blocked);
    }

    /**
     * default constructor
     */
    public CardAccount(){
        super();
    }

    /**
     * override method toString
     * @return object cardAccount - compartment in string
     */
    @Override
    public String toString() {
        return "\n\n***************\nType: Card account\n"+super.toString();
    }
}

