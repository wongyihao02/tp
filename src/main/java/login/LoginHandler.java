package login;

import util.UI;

public class LoginHandler {
    private final UI ui;
    private final TALogin passwordHolder;

    public LoginHandler(UI ui, TALogin passwordHolder) {
        this.ui = ui;
        this.passwordHolder = passwordHolder;
    }

    public void handleLogin() {
        boolean run = true;

        if (isDefaultPassword(passwordHolder.getPassWord())) {
            ui.printCreatePasswordMenu();
            while (run) {
                String input = ui.getUserCommand();
                assert input != null : "Error: User input should not be null";
                if (input.isEmpty()) {
                    System.out.println("Error: Password requires at least one character");
                    ui.printDottedLine();
                } else {
                    passwordHolder.setPassWord(input);
                    run = false;
                    System.out.println("Password created successfully");
                    ui.printDottedLine();
                }
            }
        } else {
            while (run) {
                String input = ui.getUserCommand();
                assert input != null : "Error: User input should not be null";

                if (passwordHolder.passwordCorrect(input)) {
                    System.out.println("Login Successful");
                    ui.printDottedLine();
                    run = false;
                } else {
                    System.out.println("Login Failed: Incorrect password");
                    ui.printDottedLine();
                }
            }
        }
    }

    private boolean isDefaultPassword(String password) {
        return password.equals("12341234 This is a stand in password for your" +
                " account 12341234 JDNfjndsl jlijfwjfnwjuhun JFBDJBwe7r43rbf jWUEFWUE4RI3B4NKBEifu oiuJWBEFKBLJB");
    }
}
