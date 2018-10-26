package model;

public class Army extends Element {
    private int level;
    private int[][] personsDetails = new int[5][2];

    public Army() {
        this.personsDetails[0][0] = 100;
        this.level = 1;
    }

    @Override
    public double getScore() {
        return Math.pow(10, this.personsDetails[0][1]);
    }

    public double getPersonsIncome(){
        double income = 0;
        for (int i = 0 ; i < 5 ; i++)
            income += 100 * this.personsDetails[i][0] * this.personsDetails[i][1];
        return income;
    }

    public void incrementDaysBuilt() {
        for (int i = 0 ; i < 5 ; i++)
            if (this.personsDetails[i][0] != 0)
                this.personsDetails[i][1]++;
    }

    public int[][] getPersonsDetails() {
        return personsDetails;
    }

    public void setPersonsDetails(int[][] personsDetails) {
        this.personsDetails = personsDetails;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.personsDetails[level - 1][0] = 10;
        this.level = level;
    }
}
