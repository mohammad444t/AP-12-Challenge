package model;

public class Defence extends Element {
    private int level = 1;

    public Defence() {
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
