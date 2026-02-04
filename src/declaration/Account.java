package declaration;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long accountNumber;
    private int pin;
    private int balance;
    private int index = 1;
    private List<String> transactionHistory;

    public Account(long accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
        transactionHistory.add(index++ + ". Deposited: " + amount);
    }

    public boolean withdraw(int amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add(index++ + ". Withdrawn: " + amount);
        return true;
    }

    public void printTransactions() {
        System.out.println("Transaction History:");
        for (String t : transactionHistory) {
            System.out.println(t);
        }
    }
}
