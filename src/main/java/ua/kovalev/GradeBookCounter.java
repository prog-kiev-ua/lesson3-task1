package ua.kovalev;

public class GradeBookCounter {
    private int currentGradeBookId;

    public GradeBookCounter() {
        super();
        currentGradeBookId = 0;
    }

    public int next(){
        return ++this.currentGradeBookId;
    }
}
