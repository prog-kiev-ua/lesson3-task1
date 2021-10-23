package ua.kovalev;

import ua.kovalev.exceptions.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        Student student1 = new Student("Иван", "Иванов", 15, Sex.MALE);
        Student student2 = new Student("Иван", "Иванов", 15, Sex.MALE);
        Student student3 = new Student("Иван", "Иванов", 15, Sex.MALE);
        Student student4 = new Student("Иван", "Иванов", 14, Sex.MALE);

        System.out.println(student1.equals(student2));
        System.out.println(student2.equals(student3));
        System.out.println(student1.equals(student3));
        System.out.println(student1.equals(student4));

        Group group1 = new Group("Физики");
        Group group2 = new Group("Физики");
        try {
            ServiceStudent.addStudentToGroup(student1, group1);
            ServiceStudent.addStudentToGroup(student2, group1);
            ServiceStudent.addStudentToGroup(student3, group1);

            ServiceStudent.addStudentToGroup(student1, group2);
            ServiceStudent.addStudentToGroup(student2, group2);
            ServiceStudent.addStudentToGroup(student3, group2);
        } catch (AddStudentException e) {
            e.printStackTrace();
        }

        System.out.println(group1.equals(group2));

        group1.showEqStudents();

//        try {
//            Student student1 = new Student("Иван", "Иванов", 15, Sex.MALE);
//            Student student2 = new Student("Пётр", "Свиридов", 16, Sex.MALE);
//            Student student3 = new Student("Сергей", "Павлов", 15, Sex.MALE);
//            Group group = new Group("Физики");
//
//            ServiceStudent.addStudentToGroup(student1, group);
//            ServiceStudent.addStudentToGroup(student2, group);
//            ServiceStudent.addStudentToGroup(student3, group);
//
//            System.out.println("Main.main group expected: " + group.toString());
//
//            GroupFileStorage groupFileStorage = new GroupFileStorage();
//
//            groupFileStorage.saveGroupToCSV(group);
//
//            File fileFinded = groupFileStorage.findFileByGroupName(group.getName(), groupFileStorage.getWorkFolder());
//
//            Group groupLoaded = groupFileStorage.loadGroupFromCSV(fileFinded);
//
//            System.out.println("Main.main group loaded: " + groupLoaded.toString());
//
//        } catch (AddStudentException | IOException | SaveGroupToCSVException | NoSuchFolderException |
//                NoSuchGroupException | LoadGroupFromCSVException e) {
//            e.printStackTrace();
//        }
    }
}
