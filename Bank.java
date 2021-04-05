import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private String prefix;
    private List<Account> accounts = new ArrayList<>();

    public Bank() {
    }

    public Bank(String name, String prefix, List<Account> customers) {
        this.name = name;
        this.prefix = prefix;
        this.accounts = customers;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
