package ua.kovalev;

import ua.kovalev.exceptions.AddStudentException;
import ua.kovalev.exceptions.NoSuchStudentException;
import ua.kovalev.exceptions.RemoveStudentException;

import java.util.*;

public class Group {
    private int idGradeBookCounter;
    private String name;
    private int maxStudents;
    private List<Student> listStudents;

    public Group() {
        super();
    }

    public Group(String name) {
        super();
        this.name = name;
        this.idGradeBookCounter = 0;
        this.maxStudents = 10;
        listStudents = new ArrayList<>();
    }

    public void addStudent(Student student) throws AddStudentException {
        if (listStudents.size() == maxStudents) {
            throw new AddStudentException("Группа заполнена");
        }
        listStudents.add(student);
        addData(student);
        System.out.println("Студент добавлен в группу: " + student);
    }

    public void removeStudent(int idGradeBook) throws RemoveStudentException {
        if (listStudents.isEmpty()) {
            throw new RemoveStudentException("Группа пустая");
        }

        Optional<Student> student = listStudents.stream().filter(s -> s.getIdGradeBook() == idGradeBook && s.getGroupName().equals(name)).findFirst();
        if (student.isPresent()) {
            listStudents.remove(student.get());
            System.out.println("Студент удалён из группы: " + student.get());
        } else {
            throw new RemoveStudentException(String.format("Студент [%d] не найден\n", idGradeBook));
        }
    }

    public Student findStudent(String surname) throws NoSuchStudentException {
        NoSuchStudentException noSuchStudentException = new NoSuchStudentException("Нет такого студента");
        if (listStudents.isEmpty()) {
            throw noSuchStudentException;
        }

        Optional<Student> student = listStudents.stream().filter(s -> s.getSurname().equals(surname)).findFirst();
        if (!student.isPresent()) throw noSuchStudentException;
        return student.get();
    }

    private void addData(Student student) {
        student.setGroupName(name);
        student.setIdGradeBook(++idGradeBookCounter);
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

    @Override
    public String toString() {
        sortStudentsBySurname();
        return "Group{" +
                "name='" + name + '\'' +
                ", listStudents=" + listStudents +
                '}';
    }

    public void sortStudentsBySurname() {
        Comparator sortBySurnameComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Student) o1).getSurname().compareTo(((Student) o2).getSurname());
            }
        };
        Collections.sort(listStudents, sortBySurnameComparator);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!o.getClass().equals(getClass()))
            return false;

        Group other = (Group) o;
        if (other.idGradeBookCounter != idGradeBookCounter) {
            return false;
        }

        if (other.name == null) {
            if (name != null)
                return false;
        } else if (name == null)
            return false;
        else if (!other.name.equals(name))
            return false;

        if (other.listStudents == null) {
            if (listStudents != null)
                return false;
        } else if (listStudents == null)
            return false;
        else if (!listStudents.equals(other.listStudents)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int number = 31;
        int result = 1;
        result = number * result + idGradeBookCounter;
        result = number * result + ((name != null) ? name.hashCode() : 0);
        result = number * result + ((listStudents != null) ? listStudents.hashCode() : 0);
        return result;
    }

    public void showEqStudents() {
        List<Student> listFinded = new ArrayList<>();
        boolean globalFinded = false;

        for (int i = 0; i < listStudents.size(); i++) {
            for (int j = i + 1; j < listStudents.size(); j++) {
                if (listStudents.get(i).equals(listStudents.get(j))){
                    if(!listFinded.contains(listStudents.get(i))){
                        System.out.println(String.format("студент [%s] не уникальный в базе", listStudents.get(i)));
                        listFinded.add(listStudents.get(i));
                    }
                    globalFinded = true;
                    break;
                }
            }
        }

        if (!globalFinded)
            System.out.println("Все студенты в базе уникальные");

    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
}
