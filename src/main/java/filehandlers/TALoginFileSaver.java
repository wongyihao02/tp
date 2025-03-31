package filehandlers;

import login.TALogin;

import java.io.FileWriter;
import java.io.IOException;

public class TALoginFileSaver implements FileSaver<TALogin> {

    @Override
    public void saveToFile(TALogin object, String directoryPath) {
        String filePath = directoryPath + "/PasswordHolder.txt";
        try (FileWriter hsy = new FileWriter(filePath)) {
            hsy.write(object.getPassWord());
        } catch (IOException e) {
            System.out.println("error while saving password file");
        }
    }
}
