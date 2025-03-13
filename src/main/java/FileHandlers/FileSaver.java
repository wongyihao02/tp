package FileHandlers;

public interface FileSaver<T> {
    void saveToFile(T object, String directoryPath);
}