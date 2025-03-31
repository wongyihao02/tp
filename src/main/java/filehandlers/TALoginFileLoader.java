package filehandlers;

import login.TALogin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TALoginFileLoader implements FileLoader<TALogin> {

    public TALogin loadFromFile(String filePath) {
        String passWord = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            if (line != null) {
                passWord = line;
            } else {
                passWord = "password";
            }


        } catch (IOException e) {
            System.err.println("Error loading password file: " + e.getMessage());
        }

        return new TALogin(passWord);
    }
}
