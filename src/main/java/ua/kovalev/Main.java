package ua.kovalev;

import ua.kovalev.exceptions.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Student student1 = new Student("Иван", "Иванов", 15, Sex.MALE);
            Student student2 = new Student("Пётр", "Свиридов", 16, Sex.MALE);
            Student student3 = new Student("Сергей", "Павлов", 15, Sex.MALE);
            Group group = new Group("Физики");

            ServiceStudent.addStudentToGroup(student1, group);
            ServiceStudent.addStudentToGroup(student2, group);
            ServiceStudent.addStudentToGroup(student3, group);

            System.out.println("Main.main group expected: " + group.toString());

            GroupFileStorage groupFileStorage = new GroupFileStorage();

            groupFileStorage.saveGroupToCSV(group);

            File fileFinded = groupFileStorage.findFileByGroupName(group.getName(), groupFileStorage.getWorkFolder());

            Group groupLoaded = groupFileStorage.loadGroupFromCSV(fileFinded);

            System.out.println("Main.main group loaded: " + groupLoaded.toString());

        } catch (AddStudentException | IOException | SaveGroupToCSVException | NoSuchFolderException |
                NoSuchGroupException | LoadGroupFromCSVException e) {
            e.printStackTrace();
        }
    }
}
