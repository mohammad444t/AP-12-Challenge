package model;

public class Home extends Element {
    private int floor;
    private int unit;

    public Home(int floor, int unit) {
        this.floor = floor;
        this.unit = unit;
    }

    @Override
    public double getScore() {
        return 0;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
