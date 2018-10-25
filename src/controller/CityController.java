package controller;

import model.*;
import model.request.*;
import view.View;

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
                if (isPlayer1Active)
                    attackAction((AttackRequest) request, activePlayer, player2);
                else
                    attackAction((AttackRequest) request, activePlayer, player1);
            } else if (request instanceof DoneRequest) {
                doneAction((DoneRequest) request, activePlayer);
                isPlayer1Active = !isPlayer1Active;
            } else if (request instanceof LootRequest) {
                if (isPlayer1Active)
                    lootAction((LootRequest) request, activePlayer, player2);
                else
                    lootAction((LootRequest) request, activePlayer, player1);
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
            } else if (request instanceof BadRequest) {
                badAction((BadRequest)request);
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

    public void doneAction (DoneRequest request, Player activePlayer) {
        int money = activePlayer.getMoney();
        ArrayList<Block> blocks = activePlayer.getBlocks();
        for (Block block : blocks) {
            if (block != null) {
                money += 100 * block.getTotalUnemplyedPersons();
                ArrayList<Element> elements = block.getElements();
                for (Element element : elements) {
                    if (element instanceof Army) {
                        ((Army) element).incrementDaysBuilt();
                        money += ((Army) element).getPersonsIncome();
                    }
                    else if (element instanceof Defence) {
                        ((Defence) element).incrementDaysBuilt();
                        money += ((Defence) element).getPersonsIncome();
                    }
                    else if (element instanceof Bazaar) {
                        ((Bazaar) element).incrementDaysBuilt();
                        money += ((Bazaar) element).getPersonsIncome();
                    }
                }
            }
        }
        activePlayer.setBlocks(blocks);
        activePlayer.setMoney(money);
    }
}
