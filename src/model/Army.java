package model;

public class Army extends Element {
    private int level;
    private int daysBuilt;

    public Army() {
        persons = 100;
        this.level = 1;
        this.daysBuilt = 1;
    }

    @Override
    public double getScore() {
        return Math.pow(10, daysBuilt);
    }

    public void increment() {
        this.daysBuilt++;
    }

    public int getDaysBuilt() {
        return daysBuilt;
    }

    public void setDaysBuilt(int daysBuilt) {
        this.daysBuilt = daysBuilt;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
