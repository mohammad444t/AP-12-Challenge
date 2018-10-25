package model.request;

public class UpgradeHomeRequest extends Request {
    private int blockID;
    private int floor;
    private int unit;


    public UpgradeHomeRequest(int blockID, int floor, int unit) {
        this.blockID = blockID;
        this.floor = floor;
        this.unit = unit;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
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
