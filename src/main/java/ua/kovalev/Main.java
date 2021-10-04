package ua.kovalev;

import ua.kovalev.exceptions.AddStudentException;
import ua.kovalev.exceptions.NoSuchStudentException;
import ua.kovalev.exceptions.RemoveStudentException;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Иван", "Иванов", 15, Sex.MALE);
        Student student2 = new Student("Пётр", "Свиридов", 16, Sex.MALE);
        Student student3 = new Student("Сергей", "Павлов", 15, Sex.MALE);
        Student student4 = new Student("Лариса", "Лаврова", 15, Sex.FEMALE);
        Student student5 = new Student("Юлия", "Ваисильева", 15, Sex.FEMALE);
        Student student6 = new Student("Василий", "Машков", 15, Sex.MALE);
        Student student7 = new Student("Геннадий", "Милов", 15, Sex.MALE);
        Student student8 = new Student("Кирилл", "Шилов", 15, Sex.MALE);
        Student student9 = new Student("Валентин", "Кузнецов", 15, Sex.MALE);
        Student student10 = new Student("Михаил", "Баранов", 15, Sex.MALE);
        Student student11 = new Student("Евгений", "Овечкин", 15, Sex.MALE);

        Group group = new Group("Физмат");

        try {
            group.addStudent(student1);
            group.addStudent(student2);
            group.addStudent(student3);
            group.addStudent(student4);
            group.addStudent(student5);
            group.addStudent(student6);
            group.addStudent(student7);
            group.addStudent(student8);
            group.addStudent(student9);
            group.addStudent(student10);
            System.out.println(group.toString());
            group.addStudent(student11);
        } catch (AddStudentException e) {
            System.out.println(e.getMessage());
        }

        try {
            group.removeStudent(student11.getIdGradeBook());
        } catch (RemoveStudentException e){
            System.out.println(e.getMessage());
        }

        try {
            group.removeStudent(student1.getIdGradeBook());
            group.removeStudent(student2.getIdGradeBook());
            group.removeStudent(student3.getIdGradeBook());
            group.removeStudent(student4.getIdGradeBook());
            group.removeStudent(student5.getIdGradeBook());
            group.removeStudent(student6.getIdGradeBook());
            group.removeStudent(student7.getIdGradeBook());
            group.removeStudent(student8.getIdGradeBook());
            group.removeStudent(student9.getIdGradeBook());
            group.removeStudent(student10.getIdGradeBook());
            group.removeStudent(student11.getIdGradeBook());
            System.out.println(group.toString());
        } catch (RemoveStudentException e){
            System.out.println(e.getMessage());
        }

        try {
            Student student = group.findStudent("Левин");
            System.out.println(student);
        } catch (NoSuchStudentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Student student = group.findStudent("Машков");
            System.out.println(student);
        } catch (NoSuchStudentException e) {
            System.out.println(e.getMessage());
        }
    }
}
