package model;

public class Army extends Element {
    private int level = 1;

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

    public Army() {
    }
}
