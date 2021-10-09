package ua.kovalev.exceptions;

public class AddStudentException extends Exception{
    public AddStudentException() {
        super();
    }

    public AddStudentException(String message) {
        super(message);
    }
}
