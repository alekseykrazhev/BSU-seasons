package Controller;

import Controller.sorting.SortByMoneyAmount;
import Controller.sorting.SortedByAccountNumber;
import Model.Account;
import Model.AccountAbstract;
import Model.Bank;
import Model.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BankUIController implements Initializable {

    private Bank bank;

    private String selectedMenuItem;

    @FXML
    private Text txOut1;

    @FXML
    private Text txOut2;

    @FXML
    private Text txOut3;

    @FXML
    private Button btn;

    @FXML
    private TextField txIn1;

    @FXML
    private TextField txIn2;

    @FXML
    private TextField txIn3;

    @FXML
    private TextArea largeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bank = AccountsLoader.initializationAccounts();
        selectedMenuItem = "";
    }

    @FXML
    private void close() {
        setAllInvisible();
        File file = new File("src/File/accounts.txt");
        ServiceFile.writeFile(bank, file);
        txOut1.setVisible(true);
        txOut1.setText("Work Completed.");
        btn.setVisible(false);
    }

    @FXML
    private void addNewClientText() {
        setAllVisible();
        selectedMenuItem = "addNewClient";

        txOut1.setText("Enter client name:");
        txOut2.setText("Enter account number:");
        txOut3.setText("Enter amount of money:");
        btn.setVisible(true);
    }

    @FXML
    private void addNewAccountClientText() {
        setAllVisible();
        selectedMenuItem = "addAccountClient";

        txOut1.setText("Enter owner:");
        txOut2.setText("Enter account number:");
        txOut3.setText("Enter amount of money:");
        btn.setVisible(true);
    }

    @FXML
    private void deleteAccountText() {
        selectedMenuItem = "deleteAccount";
        setAllInvisible();
        txOut1.setVisible(true);
        txOut1.setText("Enter account number to delete:");
        txIn1.setVisible(true);
        btn.setVisible(true);
    }

    @FXML
    private void countAllMoneyText() {
        selectedMenuItem = "";

        setAllInvisible();
        txOut1.setVisible(true);
        txOut1.setText("All money: " + ServiceBank.countMoney(bank));
        btn.setVisible(false);
    }

    @FXML
    private void countPosNegText() {
        setAllInvisible();
        btn.setVisible(false);
        txOut1.setVisible(true);
        txOut2.setVisible(true);
        txOut1.setText("Positive balance: " + ServiceBank.countPositiveBalance(bank));
        txOut2.setText("Negative balance: " + ServiceBank.countNegativeBalance(bank));
    }

    @FXML
    private void blockAccountText() {
        selectedMenuItem = "blockAccount";
        setAllInvisible();
        txOut1.setVisible(true);
        txOut1.setText("Enter number of acc to block:");
        txIn1.setVisible(true);
        btn.setVisible(true);
    }

    @FXML
    private void unblockAccountText() {
        selectedMenuItem = "unblockAccount";
        setAllInvisible();
        txOut1.setVisible(true);
        txOut1.setText("Enter number of acc to unblock:");
        txIn1.setVisible(true);
        btn.setVisible(true);
    }

    @FXML
    private void viewAllAccounts() {
        setAllInvisible();
        btn.setVisible(false);
        StringBuilder ans = new StringBuilder();
        largeText.setVisible(true);
        for (AccountAbstract account : bank.getListOfAccounts()) {
            ans.append(account);
        }
        largeText.setText(ans.toString());
    }

    @FXML
    private void viewAllClients() {
        setAllInvisible();
        btn.setVisible(false);
        largeText.setVisible(true);
        StringBuilder ans = new StringBuilder();
        for (Client client: bank.getListOfClients()) {
            ans.append(client);
        }
        largeText.setText(ans.toString());
    }

    @FXML
    private void sortByMoney() {
        setAllInvisible();
        btn.setVisible(false);
        largeText.setVisible(true);
        StringBuilder ans = new StringBuilder();

        bank.getListOfAccounts().sort(new SortByMoneyAmount());

        for (AccountAbstract account: bank.getListOfAccounts()) {
            ans.append(account);
        }
        largeText.setText(ans.toString());
    }

    @FXML
    private void sortByAccount() {
        setAllInvisible();
        btn.setVisible(false);
        largeText.setVisible(true);
        StringBuilder ans = new StringBuilder();

        bank.getListOfAccounts().sort(new SortedByAccountNumber());

        for (AccountAbstract account: bank.getListOfAccounts()) {
            ans.append(account);
        }
        largeText.setText(ans.toString());
    }

    @FXML
    private void putMoney() {
        selectedMenuItem = "putMoney";
        setAllInvisible();
        btn.setVisible(true);
        txIn1.setVisible(true);
        txIn2.setVisible(true);
        txOut1.setVisible(true);
        txOut2.setVisible(true);
        txOut1.setText("Enter account number:");
        txOut2.setText("Enter amount of money:");
    }

    @FXML
    private void getMoney() {
        selectedMenuItem = "getMoney";
        setAllInvisible();
        btn.setVisible(true);
        txIn1.setVisible(true);
        txIn2.setVisible(true);
        txOut1.setVisible(true);
        txOut2.setVisible(true);
        txOut1.setText("Enter account number:");
        txOut2.setText("Enter amount of money:");
    }

    @FXML
    private void buttonAddClient() {
        switch (selectedMenuItem) {
            case "addNewClient": {
                Client client;
                Account account = new Account();
                String nameClient;

                nameClient = txIn1.getText();
                account.setAccountNumber(txIn2.getText());
                account.setMoneyAmount(Integer.parseInt(txIn3.getText()));
                account.setBlocked(false);

                ServiceBank.addAccount(bank, account);
                client = new Client(nameClient, account);
                ServiceBank.addClient(bank, client);
                break;
            }
            case "addAccountClient": {
                Account account = new Account();
                String nameClient;

                nameClient = txIn1.getText();
                account.setAccountNumber(txIn2.getText());
                account.setMoneyAmount(Integer.parseInt(txIn3.getText()));
                account.setBlocked(false);

                ServiceBank.addAccount(bank, account);
                for (Client client: bank.getListOfClients()) {
                    if (client.getName().equals(nameClient)){
                        ServiceClient.addAccount(client, account);
                        return;
                    }
                }
                break;
            }
            case "deleteAccount": {
                String numberAccount = txIn1.getText();
                ServiceBank.deleteAccount(bank, numberAccount);
                for (Client client: bank.getListOfClients()){
                    for (AccountAbstract account: client.getAccounts()) {
                        if (account.getAccountNumber().equals(numberAccount)){
                            ServiceClient.deleteAccount(client, account);
                            break;
                        }
                    }
                }
                break;
            }
            case "blockAccount": {
                String numberAccount;
                numberAccount = txIn1.getText();
                System.out.println(numberAccount);

                ServiceBank.block(bank, numberAccount);
                break;
            }
            case "unblockAccount": {
                String numberAccount;
                numberAccount = txIn1.getText();

                ServiceBank.unblock(bank, numberAccount);
                break;
            }
            case "putMoney": {
                ServiceBank.changeAmountMoney(bank, 1, txIn1.getText(), Integer.parseInt(txIn2.getText()));
                break;
            }
            case "getMoney": {
                ServiceBank.changeAmountMoney(bank, -1, txIn1.getText(), Integer.parseInt(txIn2.getText()));
                break;
            }
            default: {
                largeText.setVisible(true);
                largeText.setText("Error occured, try again...");
                break;
            }
        }
    }

    private void setAllVisible() {
        txIn1.setVisible(true);
        txIn2.setVisible(true);
        txIn3.setVisible(true);
        txOut1.setVisible(true);
        txOut2.setVisible(true);
        txOut3.setVisible(true);
        largeText.setVisible(false);
    }

    private void setAllInvisible() {
        txIn1.setVisible(false);
        txIn2.setVisible(false);
        txIn3.setVisible(false);
        txOut1.setVisible(false);
        txOut2.setVisible(false);
        txOut3.setVisible(false);
        largeText.setVisible(false);
    }
}
