package ua.kovalev;

public class Person {
    private String name;
    private String surname;
    private int age;
    private Sex sex;

    public Person() {
        super();
    }

    public Person(String name, String surname, int age, Sex sex) {
        super();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!o.getClass().equals(getClass()))
            return false;

        Person person = (Person) o;

        if (person.name == null) {
            if (name != null)
                return false;
        } else if (name == null)
            return false;
        else if (!person.name.equals(name))
            return false;

        if (person.surname == null) {
            if (surname != null)
                return false;
        } else if (surname == null)
            return false;
        else if (!person.surname.equals(surname))
            return false;

        if (person.sex == null) {
            if (sex != null)
                return false;
        } else if (sex == null)
            return false;
        else if (person.sex != sex)
            return false;

        if (person.age != age) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int number = 31;
        int result = 1;
        result = number * result + ((name != null) ? name.hashCode() : 0);
        result = number * result + ((surname != null) ? surname.hashCode() : 0);
        result = number * result + ((sex != null) ? sex.hashCode() : 0);
        result = number * result + age;
        return result;
    }
}
