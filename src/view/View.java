package view;

import java.util.Scanner;

public class View {
    private static Scanner input = new Scanner(System.in);

    public String getCommand() {
        return input.nextLine();
    }

    public void logAdd(int ID) {
        System.out.println(ID);
    }

    public void logSeeScore(double score) {
        System.out.println( String.format( "%.2f", score ));
    }

    public void logSeeGills(int gills) {
        System.out.println(gills);
    }

    public void logNotPossible() {
        System.out.println("not possible");
    }

    public void logYield(double score1, double score2) {
        System.out.println( String.format( "%.2f", score1 ) + " " +  String.format( "%.2f", score2 ));
    }
}
