package login;

import java.util.HashMap;
import java.util.Map;

public class TALogin {

    private Map<String, String> accounts;

    public TALogin() {
        accounts = new HashMap<String, String>();
    }

    public TALogin(Map<String, String> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(String account, String password) {
        accounts.put(account, password);
    }

    public Map<String, String> getAccounts() {
        return accounts;
    }

    public boolean passwordCorrect(String account, String password) {
        if (!accounts.containsKey(account)) {
            System.out.println("Account does not exist");
            return false;
        }

        String storedPassword = accounts.get(account);

        return storedPassword.equals(password);
    }

    @Override
    public String toString() {
        return accounts.toString();
    }
}
