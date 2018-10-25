package model.request;

public class UpgradeWorkPlaceRequest extends Request {
    private int blockID;
    private int unitID;

    public UpgradeWorkPlaceRequest(int blockID, int unitID) {
        this.blockID = blockID;
        this.unitID = unitID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
