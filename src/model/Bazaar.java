package model;

public class Bazaar extends Element {
    private int level;
    private int daysBuilt;

    public Bazaar() {
        persons = 50;
        this.level = 1;
        this.daysBuilt = 1;
    }

    public void incrementDaysBuilt() {
        this.daysBuilt++;
    }

    @Override
    public double getScore() {
        return Math.pow(10, daysBuilt);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
