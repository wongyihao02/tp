package filehandlers;

public interface FileSaver<T> {
    void saveToFile(T object, String directoryPath);
}
