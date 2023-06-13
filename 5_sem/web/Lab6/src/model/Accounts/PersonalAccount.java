package model.Accounts;

public class PersonalAccount extends Account {

    /**
     * Constructor with parameters
     */
    public PersonalAccount(String accountNumber, int amountMoney, boolean blocked) {
        super(accountNumber, amountMoney, blocked);
    }

    /**
     * default constructor
     */
    public PersonalAccount(){
        super("123", 123, false);
    }

    /**
     * override method toString
     * @return object personalAccount - compartment in string
     */
    @Override
    public String toString() {
        return "\n\n***************\nType: Personal account\n"+super.toString();
    }
}
