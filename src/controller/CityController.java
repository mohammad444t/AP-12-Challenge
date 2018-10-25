package controller;

import model.Bazaar;
import model.Element;
import model.Player;
import model.request.*;
import view.View;
import model.Block;

import java.util.ArrayList;


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


    public void addBazaarAction (AddBazaarRequest request, Player activePlayer) {
        ArrayList<Block> blocks = activePlayer.getBlocks();
        int ID = request.getBlockID();

        if (ID > blocks.size()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
            view.logNotPossible();
        }
        else {
            ArrayList<Element> elements = blocks.get(ID-1).getElements();
            Element newElement = new Bazaar();
            elements.add(newElement);
            blocks.get(ID-1).setElements(elements);
            activePlayer.setBlocks(blocks);
            view.logAdd(elements.size());
        }
    }

    public void addBlockAction(AddBlockRequest request, Player activePlayer) {
        ArrayList<Block> blocks = activePlayer.getBlocks();
        Block addedBlock = new Block();
        addedBlock.setBlockSize(15);
        blocks.add(addedBlock);
        activePlayer.setBlocks(blocks);
        view.logAdd(blocks.indexOf(addedBlock)+1);
    }

    public void addGillgArmyAction (AddGilgArmyRequest request, Player activePlayer) {
        String type = request.getType();
        if (type.toLowerCase().equals("defence")) {

        }
        else {

        }


    }
}
