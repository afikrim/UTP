import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account {
    private String name;
    private double balance;
    private String accountNumber;
    private String pin;
    private boolean isBlocked;

    public Account() {
    }

    public Account(String name, double balance, String accountNumber, String pin) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.isBlocked = false;
    }

    public static List<Account> initAccount() {
        Account A = new Account("A", 1000000, "123456", "123456");
        Account B = new Account("B", 2000000, "234567", "123456");
        Account C = new Account("C", 50000, "345678", "123456");

        return new ArrayList<>(Arrays.asList(A, B, C));
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void reduceBalance(double amount) {
        balance -= amount;
    }

    public void increaseBalance(double amount) {
        balance += amount;
    }
}
