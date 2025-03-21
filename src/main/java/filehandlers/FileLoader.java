package filehandlers;

public interface FileLoader<T> {
    T loadFromFile(String filePath);
}
