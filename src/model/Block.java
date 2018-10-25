package model;

import java.util.ArrayList;

public class Block {

    private java.util.ArrayList<Element> elements = new ArrayList<Element>();
    private double score = 0;
    private boolean hasDefense = false;
    private int blockSize;

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
}
