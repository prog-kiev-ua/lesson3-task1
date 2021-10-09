package ua.kovalev.exceptions;

public class RemoveStudentException extends Exception{
    public RemoveStudentException() {
        super();
    }

    public RemoveStudentException(String message) {
        super(message);
    }
}
