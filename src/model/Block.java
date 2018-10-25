package model;

import java.util.ArrayList;

public class Block {

    private java.util.ArrayList<Element> elements = new ArrayList<Element>();
    private boolean hasDefence = false;
    private double basicPoint = 1;
    private int blockSize;
    private int totalPersons = 0;
    private int totalUnemplyedPersons = 0;

    public boolean isHasDefence() {
        return hasDefence;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public double getBasicPoint() {
        return basicPoint;
    }

    public void setBasicPoint(double basicPoint) {
        this.basicPoint = basicPoint;
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
        double score = 0;
        for (Element element : this.elements)
            if(element instanceof Home) {
                Home home = (Home)element;
                double persons = this.basicPoint * home.getFloor() * home.getUnit() * 5;
                double units = home.getUnit() * home.getFloor() * (2 + 5 * this.basicPoint);
                double floors = home.getFloor() * (3 + home.getUnit() * (2 + 5 * this.basicPoint) +
                        home.getFloor() * home.getUnit() * 5 * this.basicPoint * 3);
                double homePoint = 10 + floors + units * 2 + persons * 3;
                score += homePoint + floors + units + persons;
            }
            else
                score += element.getScore() * this.basicPoint;
        return score;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

}
