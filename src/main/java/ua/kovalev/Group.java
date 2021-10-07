package ua.kovalev;

import ua.kovalev.exceptions.AddStudentException;
import ua.kovalev.exceptions.NoSuchStudentException;
import ua.kovalev.exceptions.RemoveStudentException;

import java.util.Arrays;
import java.util.Comparator;

public class Group {
    private int idGradeBookCounter;
    private String name;
    private Student[] baseStudents;
    private int countStudents;

    public Group() {
        super();
    }

    public Group(String name) {
        super();
        this.name = name;
        this.idGradeBookCounter = 0;
        this.countStudents = 0;
        this.baseStudents = new Student[10];
    }

    public void addStudent(Student student) throws AddStudentException {
        if (hasFreePlaces()==0){
            throw new AddStudentException("Группа переполнена");
        }
        baseStudents[countStudents] = student;
        addData(student);
        System.out.println("Студент добавлен в группу: " + student);
    }

    public void removeStudent(int idGradeBook) throws RemoveStudentException {
        if (hasFreePlaces()==this.baseStudents.length){
            throw new RemoveStudentException("Группа пустая");
        }
        Student findedStudent = null;
        for (int i = 0; i < countStudents; i++) {
            if(baseStudents[i].getIdGradeBook() == idGradeBook && baseStudents[i].getGroupName().equals(name)){
                findedStudent = baseStudents[i];
                baseStudents[i] = null;
                removeData();
                trimBase();
                break;
            }
        }
        if(findedStudent==null){
            throw new RemoveStudentException(String.format("Студент [%d] не найден\n", idGradeBook));
        }
        System.out.println("Студент удалён из группы: " + findedStudent.toString());
    }

    public Student findStudent(String surname) throws NoSuchStudentException{
        NoSuchStudentException noSuchStudentException = new NoSuchStudentException("Нет такого студента");
        if(hasFreePlaces()==baseStudents.length)
            throw noSuchStudentException;
        Student student = null;
        for (int i = 0; i < countStudents; i++) {
            if(baseStudents[i].getSurname().equals(surname)){
                 student = baseStudents[i];
                 break;
            }
        }
        if(student == null) throw noSuchStudentException;
        return student;
    }

    public int hasFreePlaces(){
        return this.baseStudents.length - this.countStudents;
    }

    private void addData(Student student){
        student.setGroupName(name);
        student.setIdGradeBook(++idGradeBookCounter);
        countStudents++;
    }

    private void removeData(){
        countStudents--;
    }

    public int getIdGradeBookCounter() {
        return idGradeBookCounter;
    }

    public void setIdGradeBookCounter(int idGradeBookCounter) {
        this.idGradeBookCounter = idGradeBookCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getBaseStudents() {
        return baseStudents;
    }

    public void setBaseStudents(Student[] baseStudents) {
        this.baseStudents = baseStudents;
    }

    public int getCountStudents() {
        return countStudents;
    }

    public void setCountStudents(int countStudents) {
        this.countStudents = countStudents;
    }

    private void trimBase(){
        if(hasFreePlaces()==baseStudents.length) return;
        for (int i = 0; i < baseStudents.length; i++) {
            if(baseStudents[i] == null){
                for (int j = i+1; j < baseStudents.length; j++) {
                    if (baseStudents[j] != null){
                        baseStudents[i] = baseStudents[j];
                        baseStudents[j] = null;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        sortStudentsBySurname();
        return "Group{" +
                "name='" + name + '\'' +
                ", baseStudents=" + Arrays.toString(baseStudents) +
                '}';
    }

    private void sortStudentsBySurname(){
        Comparator sortBySurnameComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Student)o1).getSurname().compareTo(((Student)o2).getSurname());
            }
        };
        Arrays.sort(baseStudents, Comparator.nullsLast(sortBySurnameComparator));
    }
}
