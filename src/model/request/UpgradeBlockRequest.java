package model.request;

public class UpgradeBlockRequest extends Request {
    private int blockID;

    public UpgradeBlockRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
