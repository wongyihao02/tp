package filehandlers;

import accounts.TAAccountList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;

public class AccountListSaver implements FileSaver<TAAccountList> {

    @Override
    public void saveToFile(TAAccountList object, String directoryPath) {
        String filePath = directoryPath + "/AccountList.txt";
        try (FileWriter hsy = new FileWriter(filePath)) {
            Map<String, String> accounts = object.getAccounts();

            for (Map.Entry<String, String> entry : accounts.entrySet()) {
                hsy.write(entry.getKey() + " //@@// " + entry.getValue() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("error while saving AccountList file");
        }
    }
}
