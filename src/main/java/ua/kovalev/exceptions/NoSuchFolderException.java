package ua.kovalev.exceptions;

public class NoSuchFolderException extends Exception{
    public NoSuchFolderException() {
        super();
    }

    public NoSuchFolderException(String message) {
        super(message);
    }
}
