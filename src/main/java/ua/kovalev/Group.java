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
        if (hasFreePlaces() == 0) {
            throw new AddStudentException("Группа переполнена");
        }
        baseStudents[countStudents] = student;
        addData(student);
        System.out.println("Студент добавлен в группу: " + student);
    }

    public void removeStudent(int idGradeBook) throws RemoveStudentException {
        if (hasFreePlaces() == this.baseStudents.length) {
            throw new RemoveStudentException("Группа пустая");
        }
        Student findedStudent = null;
        for (int i = 0; i < countStudents; i++) {
            if (baseStudents[i].getIdGradeBook() == idGradeBook && baseStudents[i].getGroupName().equals(name)) {
                findedStudent = baseStudents[i];
                baseStudents[i] = null;
                removeData();
                trimBase();
                break;
            }
        }
        if (findedStudent == null) {
            throw new RemoveStudentException(String.format("Студент [%d] не найден\n", idGradeBook));
        }
        System.out.println("Студент удалён из группы: " + findedStudent.toString());
    }

    public Student findStudent(String surname) throws NoSuchStudentException {
        NoSuchStudentException noSuchStudentException = new NoSuchStudentException("Нет такого студента");
        if (hasFreePlaces() == baseStudents.length)
            throw noSuchStudentException;
        Student student = null;
        for (int i = 0; i < countStudents; i++) {
            if (baseStudents[i].getSurname().equals(surname)) {
                student = baseStudents[i];
                break;
            }
        }
        if (student == null) throw noSuchStudentException;
        return student;
    }

    public int hasFreePlaces() {
        return this.baseStudents.length - this.countStudents;
    }

    private void addData(Student student) {
        student.setGroupName(name);
        student.setIdGradeBook(++idGradeBookCounter);
        countStudents++;
    }

    private void removeData() {
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

    private void trimBase() {
        if (hasFreePlaces() == baseStudents.length) return;
        for (int i = 0; i < baseStudents.length; i++) {
            if (baseStudents[i] == null) {
                for (int j = i + 1; j < baseStudents.length; j++) {
                    if (baseStudents[j] != null) {
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

    public void sortStudentsBySurname() {
        Comparator sortBySurnameComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Student) o1).getSurname().compareTo(((Student) o2).getSurname());
            }
        };
        Arrays.sort(baseStudents, Comparator.nullsLast(sortBySurnameComparator));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!o.getClass().equals(getClass()))
            return false;

        Group other = (Group) o;
        if (other.idGradeBookCounter != idGradeBookCounter || other.countStudents != countStudents) {
            return false;
        }

        if (other.name == null) {
            if (name != null)
                return false;
        } else if (name == null)
            return false;
        else if (!other.name.equals(name))
            return false;

        if (other.baseStudents == null) {
            if (baseStudents != null) {
                return false;
            } else if (baseStudents == null)
                return false;
            else if (!Arrays.equals(other.baseStudents, baseStudents)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int number = 31;
        int result = 1;
        result = number * result + idGradeBookCounter;
        result = number * result + countStudents;
        result = number * result + ((name != null) ? name.hashCode() : 0);
        result = number * result + ((baseStudents != null) ? Arrays.hashCode(baseStudents) : 0);
        return result;
    }

    public void showEqStudents() {
        Student arrayCopy[] = Arrays.copyOfRange(baseStudents, 0, countStudents);
        boolean globalFinded = false;
        for (int i = 0; i < arrayCopy.length; i++) {
            if (arrayCopy[i] == null) continue;
            boolean finded = false;
            for (int j = i + 1; j < arrayCopy.length - 1; j++) {
                if (arrayCopy[j + 1] == null) continue;
                if (arrayCopy[i].equals(arrayCopy[j])) {
                    finded = true;
                    arrayCopy[j] = null;
                }
            }
            if (finded) {
                System.out.println(String.format("студент [%s] не уникальный в базе", arrayCopy[i]));
                arrayCopy[i] = null;
                globalFinded = true;
            }
        }
        if (!globalFinded)
            System.out.println("Все студенты в базе уникальные");
    }
}
