package model.Remote;

import model.Accounts.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Remote bank interface
 */
public interface RemoteBank extends Remote {

    String getAddress() throws RemoteException;

    int getAmountOfAccounts() throws RemoteException;

    CopyOnWriteArrayList<Account> GetAccounts() throws RemoteException;

    void addAccountToBank(Account account) throws RemoteException;

    double CostBank() throws RemoteException;

}
