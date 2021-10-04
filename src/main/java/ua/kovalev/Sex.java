package ua.kovalev;

public enum Sex {
    MALE("мужской"), FEMALE("женский");
    private String description;

    private Sex() {
    }

    private Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
