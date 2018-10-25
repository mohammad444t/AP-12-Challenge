package model;

public class Bazaar extends Element {
    private int level;
    private int[][] personsDetails = new int[3][2];

    public Bazaar() {
        this.personsDetails[0][0] = 50;
        this.level = 1;
    }

    @Override
    public double getScore() {
        return Math.pow(5, this.personsDetails[0][1]);
    }

    public void incrementDaysBuilt() {
        for (int i = 0 ; i < 3 ; i++)
            if (this.personsDetails[i][0] != 0)
                this.personsDetails[i][1]++;
    }

    public double getPersonsIncome() {
        double income = 0;
        for (int i = 0 ; i < 3 ; i++)
            income += 100 * this.personsDetails[i][0] * this.personsDetails[i][1];
        return income;
    }

    public int getLevel() {
        this.personsDetails[level - 1][0] = 20;
        return level;
    }

    public void setLevel(int level) {
        this.personsDetails[level - 1][0] = 10;
        this.level = level;
    }
}
