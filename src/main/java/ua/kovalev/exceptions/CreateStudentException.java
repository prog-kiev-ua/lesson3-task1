package ua.kovalev.exceptions;

public class CreateStudentException extends Exception{
    public CreateStudentException() {
        super();
    }

    public CreateStudentException(String message) {
        super(message);
    }
}
