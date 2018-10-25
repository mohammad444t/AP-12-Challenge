package controller;

import model.Player;
import view.View;

public class CityController {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private boolean isPlayer1Active = true;
    private Player activePlayer = player1;
    private View view = new View();

    public void listenForCommand() {
        boolean isFinished = false;
        while (!isFinished) {
            String command = view.getCommand().trim();
            if (isPlayer1Active == true && )

        }
    }
}
