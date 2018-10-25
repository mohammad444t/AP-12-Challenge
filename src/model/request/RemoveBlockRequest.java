package model.request;

public class RemoveBlockRequest extends Request {
    private int blockID;

    public RemoveBlockRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
