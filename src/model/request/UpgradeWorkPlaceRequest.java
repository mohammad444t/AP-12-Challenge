package model.request;

public class UpgradeWorkPlaceRequest extends Request {
    private int blockID;

    public UpgradeWorkPlaceRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
