package ua.kovalev;

public class Student extends Person{
    private int idGradeBook;
    private String groupName;

    public Student(String name, String surname, int age, Sex sex) {
        super(name, surname, age, sex);
    }

    public Student() {
        super();
    }

    public int getIdGradeBook() {
        return idGradeBook;
    }

    public void setIdGradeBook(int idGradeBook) {
        this.idGradeBook = idGradeBook;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "ua.kovalev.Student{ " + super.toString() +
                ", idGradeBook=" + idGradeBook +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
