package model.request;

public class AttackRequest extends Request {

    private int blockID;

    public AttackRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
