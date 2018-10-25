package model.request;

public class UpgaradeBlockRequest extends Request {
    private int blockID;

    public UpgaradeBlockRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
