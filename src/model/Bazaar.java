package model;

public class Bazaar extends Element {
    private int level;
    private int daysBuilt;

    public Bazaar() {
        this.level = 1;
        this.daysBuilt = 1;
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
