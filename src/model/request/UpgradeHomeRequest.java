package model.request;

public class UpgradeHomeRequest extends Request {
    private int blockID;
    private int floor;
    private int unit;
    private int unitID;


    public UpgradeHomeRequest(int blockID, int unitID, int floor, int unit) {
        this.blockID = blockID;
        this.floor = floor;
        this.unit = unit;
        this.unitID = unitID;
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

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
