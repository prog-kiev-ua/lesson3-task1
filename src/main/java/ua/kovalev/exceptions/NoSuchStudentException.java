package ua.kovalev.exceptions;

public class NoSuchStudentException extends Exception{
    public NoSuchStudentException() {
        super();
    }

    public NoSuchStudentException(String message) {
        super(message);
    }

}
