package filehandlers;

import login.TALogin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class TALoginFileSaver implements FileSaver<TALogin> {

    @Override
    public void saveToFile(TALogin object, String directoryPath) {
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
