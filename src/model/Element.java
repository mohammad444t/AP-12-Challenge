package model;

public abstract class Element {
    protected int persons = 0;

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public abstract double getScore();
}
