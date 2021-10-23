package ua.kovalev;

public class Student extends Person implements CSVConverter {
    private int idGradeBook;
    private String groupName;

    public Student(String name, String surname, int age, Sex sex) {
        super(name, surname, age, sex);
    }

    public Student(String name, String surname, int age, Sex sex, int idGradeBook, String groupName) {
        super(name, surname, age, sex);
        this.idGradeBook = idGradeBook;
        this.groupName = groupName;
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

    @Override
    public String toCSVString() {
        return String.format("%s;%s;%d;%s;%s;%d", this.getSurname(), this.getName(), this.getAge(), this.getSex().name(), this.getGroupName(), this.getIdGradeBook());
    }

    @Override
    public Student fromCSVString(String str) {
        String[] array = str.split(";");
        return new Student(array[1], array[0], new Integer(array[2]), Sex.valueOf(array[3]), new Integer(array[5]), array[4]);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;

        if (!getClass().equals(o.getClass())) {
            return false;
        }

        Student other = (Student) o;

        if (other.idGradeBook != idGradeBook) {
            return false;
        }

        if (groupName == null) {
            if (other.groupName == null) {
                return false;
            }
        } else if (other.groupName == null)
            return false;
        else if (!groupName.equals(other.groupName))
            return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        final int number = 31;
        int result = 1;

        result = number * result + idGradeBook;
        result = number * result + ((groupName != null) ? groupName.hashCode() : 0);
        result = number * result + super.hashCode();

        return result;
    }
}
