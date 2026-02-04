package implementations;

import declaration.Atm;
import declaration.Account;
import java.util.*;

public class User extends Atm {

    private static Map<Long, Account> accounts = new HashMap<>();
    private Account currentAccount = null;

    @Override
    public boolean login(long accountNumber, int pin) {
        Account acc = accounts.get(accountNumber);
        if (acc != null && acc.validatePin(pin)) {
            currentAccount = acc;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        currentAccount = null;
        System.out.println("Logged out successfully.");
    }

    @Override
    public void deposit(int amount) {
        if (!isLoggedIn()) return;

        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        currentAccount.deposit(amount);
        System.out.println("Amount credited successfully");
    }

    @Override
    public void withdrawal(int amount) {
        if (!isLoggedIn()) return;

        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        if (!currentAccount.withdraw(amount)) {
            System.out.println("Insufficient balance");
            return;
        }

        System.out.println("Amount debited successfully");
    }

    @Override
    public void checkBalance() {
        if (!isLoggedIn()) return;
        System.out.println("Current Balance = " + currentAccount.getBalance());
    }

    @Override
    public void transactions() {
        if (!isLoggedIn()) return;
        currentAccount.printTransactions();
    }

    private boolean isLoggedIn() {
        if (currentAccount == null) {
            System.out.println("Please login first.");
            return false;
        }
        return true;
    }

    private static long generateAccountNumber() {
        return 10000000000L + (long)(Math.random() * 90000000000L);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        User atm = new User();

        while (true) {
            System.out.println("\n===== ATM SYSTEM =====");
            System.out.println("1. Create New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\n===== Create Account =====");

                long accNo = generateAccountNumber();
                System.out.println("Generated Account Number: " + accNo);

                System.out.print("Create 4-digit PIN: ");
                int pin = sc.nextInt();

                Account acc = new Account(accNo, pin);
                accounts.put(accNo, acc);
                atm.currentAccount = acc;

                System.out.println("Account created successfully!");
                menu(atm, sc);

            } else if (choice == 2) {
                System.out.println("\n===== Login =====");

                System.out.print("Enter Account Number: ");
                long accNo = sc.nextLong();

                System.out.print("Enter PIN: ");
                int pin = sc.nextInt();

                if (atm.login(accNo, pin)) {
                    System.out.println("Login successful!");
                    menu(atm, sc);
                } else {
                    System.out.println("Invalid account number or PIN");
                }

            } else if (choice == 3) {
                System.out.println("Thank you for using ATM");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
        sc.close();
    }

    // Menu
    private static void menu(User atm, Scanner sc) {

        int choice;
        do {
            System.out.println("\n----- ATM MENU -----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transactions");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    atm.deposit(sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    atm.withdrawal(sc.nextInt());
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    atm.transactions();
                    break;
                case 5:
                    atm.logout();
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);
    }
}
