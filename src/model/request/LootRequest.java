package model.request;

public class LootRequest extends Request {
    private int blockID;

    public LootRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
