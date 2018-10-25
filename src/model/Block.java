package model;

import java.util.ArrayList;

public class Block {

    private java.util.ArrayList<Element> elements = new ArrayList<Element>();
    private double score = 0;
    private boolean hasDefence = false;
    private int blockSize;
    private int totalPersons = 0;
    private int totalUnemplyedPersons = 0;

    public boolean isHasDefence() {
        return hasDefence;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public int getTotalUnemplyedPersons() {
        return totalUnemplyedPersons;
    }

    public void setTotalUnemplyedPersons(int totalUnemplyedPersons) {
        this.totalUnemplyedPersons = totalUnemplyedPersons;
    }

    public void setHasDefence(boolean hasDefence) {
        this.hasDefence = hasDefence;
    }

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

<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
    public int getPersons() {
        return 0;
    }

    public double geUnemployedPersons() {
        return 0;
    }
=======

>>>>>>> Stashed changes
>>>>>>> Stashed changes
}
