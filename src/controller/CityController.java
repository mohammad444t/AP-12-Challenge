package controller;

import model.Player;
import model.request.*;
import view.View;

public class CityController {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private boolean isPlayer1Active = true;
    private Player activePlayer = player1;
    private View view = new View();
    private CommandAnalyzer commandAnalyzer;

    public void listenForCommand() {
        boolean isFinished = false;
        while (!isFinished) {
            String command = view.getCommand().trim();
            if (isPlayer1Active == true)
                activePlayer = player1;
            else
                activePlayer = player2;
            Request request = commandAnalyzer.getRequest(command);
            if (request instanceof AddBazaarRequest) {
                addBazaarAction((AddBazaarRequest) request, activePlayer);
            } else if (request instanceof AddBlockRequest) {
                addBlockAction((AddBlockRequest) request, activePlayer);
            } else if (request instanceof AddGilgArmyRequest) {
                addGillgArmyAction((AddGilgArmyRequest) request, activePlayer);
            } else if (request instanceof AddHomeRequest) {
                addHomeAction((AddHomeRequest) request, activePlayer);
            } else if (request instanceof AttackRequest) {
                attackAction((AttackRequest) request, activePlayer);
            } else if (request instanceof DoneRequest) {
                doneAction((DoneRequest) request, activePlayer);
                isPlayer1Active = !isPlayer1Active;
            } else if (request instanceof LootRequest) {
                lootAction((LootRequest) request, activePlayer);
            } else if (request instanceof RemoveBlockRequest) {
                removeBlockAction((RemoveBlockRequest) request, activePlayer);
            } else if (request instanceof RemoveWorkPlaceRequest) {
                removeWorkPlaceAction((RemoveWorkPlaceRequest)request, activePlayer);
            } else if (request instanceof SeeGillsRequest) {
                seeGillsAction((SeeGillsRequest)request, activePlayer);
            } else if (request instanceof SeeScoreRequest) {
                seeScoreActiom((SeeScoreRequest)request, activePlayer);
            } else if (request instanceof UpgaradeBlockRequest) {
                upgradeBlockAction((UpgaradeBlockRequest)request, activePlayer);
            } else if (request instanceof UpgradeHomeRequest) {
                upgradHomeAction((UpgradeHomeRequest)request, activePlayer);
            } else if (request instanceof UpgradeWorkPlaceRequest) {
                upgradeWorkPlaceAction((UpgradeWorkPlaceRequest)request, activePlayer);
            } else if (request instanceof YieldRequest) {
                yieldAction((YieldRequest)request, activePlayer);
                isFinished = true;
            }
        }
    }
}
