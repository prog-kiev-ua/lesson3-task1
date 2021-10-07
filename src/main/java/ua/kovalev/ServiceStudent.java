package ua.kovalev;

import ua.kovalev.exceptions.AddStudentException;
import ua.kovalev.exceptions.CreateStudentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceStudent {

    public static Student create() throws CreateStudentException, IOException {
        Student student = new Student();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // -- фамилия -- //
            System.out.println("Создание студента. Чтобы выйти из режима создания введите exit");
            System.out.println("Введите фамилию:");
            String surName = reader.readLine();
            if(surName.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");
            student.setSurname(surName);

            // -- имя -- //
            System.out.println("Введите имя:");
            String name = reader.readLine();
            if(name.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");
            student.setName(name);

            // -- возраст -- //
            boolean exit = false;
            while (!exit){
                try{
                    System.out.println("Введите возраст. Для выхода введите exit:");
                    String res = reader.readLine();
                    if(res.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");

                    int age = Integer.parseInt(res);
                    if(age < 12 || age > 100){
                        throw new IllegalArgumentException();
                    }
                    exit = true;
                }catch (NumberFormatException ex){
                    System.out.println("Введите число!");
                }catch (IllegalArgumentException ex) {
                    System.out.println("Не корректное число. Введите повторно!");
                }
            }
            // -- пол -- //
            exit = false;
            while (!exit){
                try{
                    System.out.println("Введите пол, где 0 - муж., 1 - жен. Для выхода введите exit:");
                    String res = reader.readLine();
                    if(res.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");

                    int sex = Integer.parseInt(res);
                    if(sex == 0 || sex == 1) {
                        switch (sex) {
                            case 0:
                                student.setSex(Sex.MALE);
                                break;
                            case 1:
                                student.setSex(Sex.FEMALE);
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                    }
                    else{
                        throw new IllegalArgumentException();
                    }
                    exit = true;
                }catch (NumberFormatException ex){
                    System.out.println("Введите число!");
                }catch (IllegalArgumentException ex) {
                    System.out.println("Не корректное число. Введите повторно!");
                }
            }
        }
        catch(IOException ex){
            throw ex;
        }

        return student;
    }

    public static void addStudentToGroup(Student student, Group group) throws AddStudentException {
        group.addStudent(student);
    }
}
