package model;

import java.util.ArrayList;

public class Player {
    private int money = 30000;
    private java.util.ArrayList<Block> blocks = new ArrayList<Block>();
    private boolean hasArmy = false;

    public int getMoney() {
        return money;
    }

    public boolean isHasArmy() {
        return hasArmy;
    }

    public void setHasArmy(boolean hasArmy) {
        this.hasArmy = hasArmy;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public double getScore() {
        return 0;
    }
}
