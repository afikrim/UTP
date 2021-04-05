import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static List<Bank> banks = new ArrayList<>();
    private static boolean running = false;

    private static Bank currentBank = null;
    private static Account currentAccount = null;
    private static boolean isLoggedIn = false;

    private static Bank getBank(String prefix) {
        Bank temp = null;
        for (Bank bank: banks) {
            if (bank.getPrefix().equals(prefix))
                temp = bank;
        }

        return temp;
    }

    private static Account getAccount(Bank bank, String accountNumber) {
        Account temp = null;
        for (Account account: bank.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber))
                temp = account;
        }

        return temp;
    }

    private static void menu() {
        System.out.println("================================");
        System.out.println("1. Withdraw");
        System.out.println("2. Top Up");
        System.out.println("3. Transfer");
        System.out.println("4. Update PIN");
        System.out.println("5. Check Balance");
        System.out.println("0. Exit");
        System.out.println("================================");
        System.out.print("Enter your choice: ");
        int pil = in.nextInt();
        switchMenu(pil);
    }

    private static void switchMenu(int index) {
        System.out.println("================================");
        switch (index) {
            case 1:
                // TODO: Tarik UANG
                withdraw();
                break;
            case 2:
                // TODO: TopUp
                topUp();
                break;
            case 3:
                // TODO: Transfer
                transfer();
                break;
            case 4:
                // TODO: Ganti PIN
                updatePin();
                break;
            case 5:
                // TODO: get balance
                System.out.println("Your balance is, Rp " + currentAccount.getBalance());
                break;
            case 0:
                isLoggedIn = false;
                break;
            default:
                System.out.println("Sorry, this option is not available yet.");
        }
        System.out.println("================================");
    }

    private static void printBanks() {
        System.out.println("================================");
        int i = 0;
        for (Bank bank : banks) {
            System.out.println((i + 1) + ". " + bank.getName() + " [" + bank.getPrefix() + "]");

            i += 1;
        }
        System.out.println("================================");
    }

    private static void login() {
        System.out.println("================================");
        System.out.print("Credential: ");
        String credential = in.next();

        String prefix = credential.substring(0, 3);
        String accountNumber = credential.replace(prefix, "");

        currentBank = getBank(prefix);
        currentAccount = getAccount(currentBank, accountNumber);

        if (currentAccount == null) {
            System.out.println("Account not found!");
            return;
        }

        if (currentAccount.getBlocked()) {
            System.out.println("Your account is blocked.");
            return;
        }

        int attempts = 1;
        while (!currentAccount.getBlocked()) {
            System.out.print("PIN: ");
            String pin = in.next();

            if (currentAccount.getPin().equals(pin)) {
                isLoggedIn = true;
                break;
            } else if (attempts == 3) {
                System.out.println("Too many attempts. Your account is blocked.");
                currentAccount.setBlocked(true);
            }

            attempts += 1;
        }
        System.out.println("================================");
    }

    private static void withdraw() {
        System.out.print("Enter the amount you want to withdraw: ");
        double amount = in.nextDouble();

        double remain = currentAccount.getBalance() - amount;
        if (remain < 50000) {
            System.out.println("You don't have enough balance.");
            return;
        }

        currentAccount.reduceBalance(amount);
        System.out.println("Successfully withdraw your balance");
    }

    private static void topUp() {
        System.out.print("Enter the amount you want to topup: ");
        double amount = in.nextDouble();

        currentAccount.increaseBalance(amount);
        System.out.println("Successfully topup your balance");
    }

    private static void transfer() {
        double admin = 0;

        System.out.print("Enter destination number: ");
        String destNumber = in.next();

        String destPrefix = destNumber.substring(0, 3);
        String destAccountNumber = destNumber.replace(destPrefix, "");

        Bank destBank = getBank(destPrefix);
        Account destAccount = getAccount(destBank, destAccountNumber);

        if (destAccount == null) {
            System.out.println("Account not found!");
            return;
        }

        if (!destBank.equals(currentBank)) {
            admin = 6500;
        }

        System.out.print("Enter the amount you want to transfer: ");
        double amount = in.nextDouble();

        double remain = currentAccount.getBalance() - amount - admin;
        if (remain < 50000) {
            System.out.println("You don't have enough balance.");
            return;
        }

        currentAccount.reduceBalance(amount + admin);
        destAccount.increaseBalance(amount);
        System.out.println("Successfully transfer your money to: " + destAccount.getName());
    }

    private static void updatePin() {
        System.out.print("Enter new pin: ");
        String newPin = in.next();

        System.out.print("Enter it again: ");
        String confirmNewPin = in.next();

        if (!newPin.equals(confirmNewPin)) {
            System.out.println("Pin not match.");
            return;
        }

        currentAccount.setPin(newPin);
        System.out.println("Successfully set a new pin");
    }

    private static void initBanks() {
        Bank ACB = new Bank("ACB", "003", Account.initAccount());
        Bank IBN = new Bank("IBN", "005", Account.initAccount());

        banks = new ArrayList<>(Arrays.asList(ACB, IBN));
    }

    public static void main(String[] args) {
        running = true;

        // TODO: Init customer and bank
        initBanks();

        while (running) {
            if (isLoggedIn)
                menu();
            else
                login();
        }
    }
}
