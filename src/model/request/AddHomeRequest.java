package model.request;

public class AddHomeRequest extends Request {
    private int blockID;
    private int floorNumbers;
    private int unitNumbers;

    public AddHomeRequest(int blockID, int floorNumbers, int unitNumbers) {
        this.blockID = blockID;
        this.floorNumbers = floorNumbers;
        this.unitNumbers = unitNumbers;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getFloorNumbers() {
        return floorNumbers;
    }

    public void setFloorNumbers(int floorNumbers) {
        this.floorNumbers = floorNumbers;
    }

    public int getUnitNumbers() {
        return unitNumbers;
    }

    public void setUnitNumbers(int unitNumbers) {
        this.unitNumbers = unitNumbers;
    }
}
