package ua.kovalev.exceptions;

public class NoSuchGroupException extends Exception{
    public NoSuchGroupException() {
        super();
    }

    public NoSuchGroupException(String message) {
        super(message);
    }
}
