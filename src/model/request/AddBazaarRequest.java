package model.request;

public class AddBazaarRequest extends Request {
    private int blockID;

    public AddBazaarRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
