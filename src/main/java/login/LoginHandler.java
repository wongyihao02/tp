package login;

import util.UI;

public class LoginHandler {
    private final UI ui;
    private final TALogin passwordHolder;

    public LoginHandler(UI ui, TALogin passwordHolder) {
        this.ui = ui;
        this.passwordHolder = passwordHolder;
    }

    public boolean handleLogin() {
        int numberOfTries = 5;

        if (isDefaultPassword(passwordHolder.getPassWord())) {
            ui.printCreatePasswordMenu();

            String input = ui.getUserCommand();
            assert input != null : "Error: User input should not be null";

            while (!isPasswordStrong(input)) {
                System.out.println("Error: Password requires at least 8 characters, " +
                        "using at least 1 uppercase letter, 1 lowercase letter, and 1 number");
                System.out.println("Please enter new password");
                ui.printDottedLine();
                input = ui.getUserCommand();
            }
            passwordHolder.setPassWord(input);
            System.out.println("Password created successfully");
            ui.printDottedLine();
            return true;
        }

        while (numberOfTries > 0) {
            String input = ui.getUserCommand();
            assert input != null : "Error: User input should not be null";

            if (passwordHolder.passwordCorrect(input)) {
                System.out.println("Login Successful");
                ui.printDottedLine();
                return true;
            } else {
                System.out.println("Login Failed: Incorrect password");
                System.out.printf("You have %d attempts left\n", numberOfTries);
                ui.printDottedLine();
                numberOfTries--;
            }
        }
        System.out.println("Login Failed, software will be closed");
        return false;
    }

    private static boolean isPasswordStrong(String input) {
        boolean passwordMoreThan8Chars = input.length() >= 8;
        boolean passwordContainsUppercaseLetters = input.matches(".*[A-Z].*");
        boolean passwordContainsLowercaseLetters = input.matches(".*[a-z].*");
        boolean passwordContainsNumbers = input.matches(".*[0-9].*");
        return passwordContainsNumbers && passwordMoreThan8Chars
                && passwordContainsUppercaseLetters && passwordContainsLowercaseLetters;
    }

    private boolean isDefaultPassword(String password) {
        return password.equals("password");
    }
}
