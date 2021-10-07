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

            System.out.println("Создание студента. Чтобы выйти из режима создания введите exit");

            // -- фамилия -- //
            student.setSurname(getSurname(reader));

            // -- имя -- //
            student.setName(getName(reader));

            // -- возраст -- //
            student.setAge(getAge(reader));

            // -- пол -- //
            int sex = getSex(reader);
            switch (sex){
                case 0:
                    student.setSex(Sex.MALE);
                    break;
                case 1:
                    student.setSex(Sex.FEMALE);
                    break;
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

    private static String getSurname(BufferedReader reader) throws IOException, CreateStudentException{
        System.out.println("Введите фамилию:");
        String surName = reader.readLine();
        if(surName.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");
        return surName;
    }

    private static String getName(BufferedReader reader) throws IOException, CreateStudentException{
        System.out.println("Введите имя:");
        String name = reader.readLine();
        if(name.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");
        return name;
    }

    private static int getAge(BufferedReader reader) throws IOException, CreateStudentException{
        boolean exit = false;
        int age = 0;
        while (!exit){
            try{
                System.out.println("Введите возраст. Для выхода введите exit:");
                String res = reader.readLine();
                if(res.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");

                age = Integer.parseInt(res);
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
        return age;
    }

    private static int getSex(BufferedReader reader) throws IOException, CreateStudentException{
        boolean exit = false;
        int sex = -1;
        while (!exit){
            try{
                System.out.println("Введите пол, где 0 - муж., 1 - жен. Для выхода введите exit:");
                String res = reader.readLine();
                if(res.equals("exit")) throw new CreateStudentException("Создание студента отменено пользователем");

                sex = Integer.parseInt(res);
                if(sex!=0 && sex!=1)
                    throw new IllegalArgumentException();
                exit = true;
            }catch (NumberFormatException ex){
                System.out.println("Введите число!");
            }catch (IllegalArgumentException ex) {
                System.out.println("Не корректное число. Введите повторно!");
            }
        }
        return sex;
    }
}
