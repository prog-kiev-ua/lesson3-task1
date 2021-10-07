package ua.kovalev;

public interface CSVConverter {
    public String toCSVString();
    public Student fromCSVString (String str);
}
