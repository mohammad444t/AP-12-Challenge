package model;

public class Bazaar extends Element {
    private int level = 1;

    public Bazaar() {
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
