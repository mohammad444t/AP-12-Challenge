package model;

public class Defence extends Element {
    private int level;
    private int daysBuilt;

    public Defence() {
        this.level = 1;
        this.daysBuilt = 0;
    }

    public void increment() {
        this.daysBuilt++;
    }

    @Override
    public double getScore() {
        return 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
