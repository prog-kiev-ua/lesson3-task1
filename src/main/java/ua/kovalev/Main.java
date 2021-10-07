package ua.kovalev;

import ua.kovalev.exceptions.AddStudentException;
import ua.kovalev.exceptions.CreateStudentException;
import ua.kovalev.exceptions.NoSuchStudentException;
import ua.kovalev.exceptions.RemoveStudentException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Student student1 = new Student("Иван", "Иванов", 15, Sex.MALE);
        Student student2 = new Student("Пётр", "Свиридов", 16, Sex.MALE);
        Student student3 = new Student("Сергей", "Павлов", 15, Sex.MALE);
        Student student4 = new Student("Лариса", "Лаврова", 15, Sex.FEMALE);
        Student student5 = new Student("Юлия", "Ваисильева", 15, Sex.FEMALE);
        Student student6 = new Student("Василий", "Машков", 15, Sex.MALE);
//        Student student7 = new Student("Геннадий", "Милов", 15, Sex.MALE);
        Student student8 = new Student("Кирилл", "Шилов", 15, Sex.MALE);
//        Student student9 = new Student("Валентин", "Кузнецов", 15, Sex.MALE);
//        Student student10 = new Student("Михаил", "Баранов", 15, Sex.MALE);
//        Student student11 = new Student("Евгений", "Овечкин", 15, Sex.MALE);

        Student student7 = null;
        try {
            student7 = ServiceStudent.create();
        } catch (CreateStudentException e) {
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e);
        }

        System.out.println(student7);

        Group group = new Group("Физмат");

        try {
            ServiceStudent.addStudentToGroup(student1, group);
            ServiceStudent.addStudentToGroup(student2, group);
            ServiceStudent.addStudentToGroup(student3, group);
            ServiceStudent.addStudentToGroup(student4, group);
            ServiceStudent.addStudentToGroup(student5, group);
            ServiceStudent.addStudentToGroup(student6, group);
            if (student7 != null)
                ServiceStudent.addStudentToGroup(student7, group);
            System.out.println(group.toString());
        } catch (AddStudentException e) {
            System.out.println(e.getMessage());
        }
        String studentStr = student1.toCSVString();
        System.out.println("------" + studentStr);
        Student student9 = student1.fromCSVString(studentStr);
        System.out.println(student9);

        try {
            group.removeStudent(student8.getIdGradeBook());
        } catch (RemoveStudentException e){
            System.out.println(e.getMessage());
        }

        try {

            group.removeStudent(student3.getIdGradeBook());
            group.removeStudent(student4.getIdGradeBook());
            group.removeStudent(student5.getIdGradeBook());
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
