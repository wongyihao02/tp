package filehandlers;

import accounts.TAAccountList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccountListLoader implements FileLoader<TAAccountList> {

    public TAAccountList loadFromFile(String filePath) {
        TAAccountList list = new TAAccountList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("//@@//");
                String accountName = splitted[0].trim().replaceAll("\\s+", " ");
                String password = splitted[1].trim().replaceAll("\\s+", " ");
                list.addAccount(accountName, password);
            }
        } catch (IOException e) {
            System.err.println("Error loading AttendanceFile: " + e.getMessage());
        }

        return list;
    }
}
