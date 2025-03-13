package FileHandlers;

public interface FileLoader<T> {
    T loadFromFile(String filePath);
}