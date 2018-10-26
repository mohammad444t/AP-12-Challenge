package model.request;

public class AddGilgArmyRequest extends Request {
    private int blockID;
    private String type;

    public AddGilgArmyRequest(int blockID, String type) {
        this.blockID = blockID;
        this.type = type;
    }

    public AddGilgArmyRequest(String type) {
        this.type = type;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
