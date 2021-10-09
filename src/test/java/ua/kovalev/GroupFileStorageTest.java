package ua.kovalev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ua.kovalev.exceptions.*;
import ua.kovalev.executables.TestNoSuchFolderExceptionExecutable;
import ua.kovalev.executables.TestNoSuchGroupExceptionExecutable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class GroupFileStorageTest {
    private File workFolder;

    @BeforeAll
    void setUp() {
        workFolder = new File("группы_тест");
        if (!workFolder.exists())
            assertTrue(workFolder.mkdir());
    }

    @AfterAll
    void tearDown() {
        if (workFolder.exists())
            workFolder.delete();
    }

    @Test
    void findFileByGroupName() {
        String groupName = "МАТЕМАТИКИ";
        GroupFileStorage groupFileStorage = new GroupFileStorage(workFolder);
        File file = new File(groupFileStorage.getWorkFolder(), groupName);

        try {
            if (!file.exists())
                assertTrue(file.createNewFile());
            assertNotNull(groupFileStorage.findFileByGroupName(groupName, workFolder));
            file.delete();
        } catch (NoSuchFolderException | NoSuchGroupException | IOException e) {
            e.printStackTrace();
        } finally {
            if (file.exists())
                file.delete();
        }
    }

    @Test
    void ifNotFolderThrowException() {
        assertThrows(NoSuchFolderException.class, new TestNoSuchFolderExceptionExecutable());
    }

    @Test
    void ifNotGroupThrowException() {
        assertThrows(NoSuchGroupException.class, new TestNoSuchGroupExceptionExecutable(workFolder, "ФИЗИКИ"));
    }

    @Test
    void saveGroupToCSV() {
        GroupFileStorage groupFileStorage = new GroupFileStorage(workFolder);

        Group group = new Group("МАТЕМАТИКИ");
        Student student1 = new Student("Иван", "Иванов", 17, Sex.MALE);
        String lineExpected = "Иванов;Иван;17;MALE;МАТЕМАТИКИ;1";
        File fileFinded = null;
        try {
            ServiceStudent.addStudentToGroup(student1, group);
            groupFileStorage.saveGroupToCSV(group);
            fileFinded = groupFileStorage.findFileByGroupName(group.getName(), groupFileStorage.getWorkFolder());
            assertNotNull(fileFinded);

            try (BufferedReader isr = new BufferedReader(new FileReader(fileFinded))) {
                String line = isr.readLine();
                assertNotNull(line);
                assertTrue(line.equals(lineExpected));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (AddStudentException | NoSuchFolderException | NoSuchGroupException | IOException | SaveGroupToCSVException e) {
            e.printStackTrace();
        } finally {
            if (fileFinded != null && fileFinded.exists())
                fileFinded.delete();
        }

    }

    @Test
    void loadGroupFromCSV() {
        GroupFileStorage groupFileStorage = new GroupFileStorage(workFolder);
        Group groupExpected = new Group("МАТЕМАТИКИ");
        Student student1 = new Student("Иван", "Иванов", 17, Sex.MALE);
        Student student2 = new Student("Пётр", "Свиридов", 16, Sex.MALE);
        File fileFinded = null;
        try {
            ServiceStudent.addStudentToGroup(student1, groupExpected);
            ServiceStudent.addStudentToGroup(student2, groupExpected);
            groupFileStorage.saveGroupToCSV(groupExpected);
            fileFinded = groupFileStorage.findFileByGroupName(groupExpected.getName(), groupFileStorage.getWorkFolder());
            assertNotNull(fileFinded);

            Group groupLoaded = groupFileStorage.loadGroupFromCSV(fileFinded);
            assertNotNull(groupLoaded);
            groupExpected.sortStudentsBySurname();
            groupLoaded.sortStudentsBySurname();

            assertTrue(groupLoaded.getIdGradeBookCounter() == groupExpected.getIdGradeBookCounter() &&
                    groupExpected.getCountStudents() == groupLoaded.getCountStudents() &&
                    groupExpected.getName().equals(groupLoaded.getName()));

            Student[] studentsExpected = groupExpected.getBaseStudents();
            Student[] studentsLoaded = groupLoaded.getBaseStudents();

            for (int i = 0; i < groupExpected.getCountStudents(); i++) {
                assertTrue(studentsExpected[i].toCSVString().equals(studentsLoaded[i].toCSVString()));
            }


        } catch (AddStudentException | NoSuchFolderException | NoSuchGroupException | IOException | SaveGroupToCSVException | LoadGroupFromCSVException e) {
            e.printStackTrace();
        } finally {
            if (fileFinded != null && fileFinded.exists())
                fileFinded.delete();
        }
    }
}