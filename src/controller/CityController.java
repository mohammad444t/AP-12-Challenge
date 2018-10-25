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
            } else if (request instanceof BadRequest) {
                badAction((BadRequest)request);
            }
        }




    }


    public void addBazaarAction (AddBazaarRequest request, Player activePlayer) {
        ArrayList<Block> blocks = activePlayer.getBlocks();
        int ID = request.getBlockID();

        if ( activePlayer.getMoney()<6000) {
            view.logNotPossible();
        }
        else if (ID > blocks.size()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1) == null) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1).getTotalUnemplyedPersons() < 50) {
            view.logNotPossible();
        }
        else {
            Block block = blocks.get(ID-1);
            ArrayList<Element> elements = blocks.get(ID-1).getElements();
            Element newElement = new Bazaar();
            elements.add(newElement);
            block.setTotalUnemplyedPersons(block.getTotalUnemplyedPersons() - 50);
            blocks.get(ID-1).setElements(elements);
            blocks.set(ID-1,block);
            activePlayer.setBlocks(blocks);
            activePlayer.setMoney( activePlayer.getMoney() - 6000);
            view.logAdd(elements.size());
        }
    }

    public void addBlockAction(AddBlockRequest request, Player activePlayer) {
        if ( activePlayer.getMoney()<1000) {
            view.logNotPossible();
        }

        else {
            ArrayList<Block> blocks = activePlayer.getBlocks();
            Block addedBlock = new Block();
            addedBlock.setBlockSize(15);
            blocks.add(addedBlock);
            activePlayer.setBlocks(blocks);
            activePlayer.setMoney( activePlayer.getMoney() - 1000);
            view.logAdd(blocks.indexOf(addedBlock) + 1);
        }
    }

    public void addGillgArmyAction (AddGilgArmyRequest request, Player activePlayer) {
        String type = request.getType();
        ArrayList<Block> blocks = activePlayer.getBlocks();
        if (type.toLowerCase().equals("army")) {
            int ID = request.getBlockID();


            if ( activePlayer.getMoney()<15000) {
                view.logNotPossible();
            }

            else if (ID > blocks.size()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1) == null) {
                view.logNotPossible();
            }
            else if (!blocks.get(ID-1).isHasDefence()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
                view.logNotPossible();
            }

            else if (blocks.get(ID-1).getTotalUnemplyedPersons() < 100) {
                view.logNotPossible();
            }
            else {
                Block block = blocks.get(ID-1);
                ArrayList<Element> elements = block.getElements();
                block.setHasDefence(true);
                Element newElement = new Defence();
                elements.add(newElement);
                block.setTotalUnemplyedPersons(block.getTotalUnemplyedPersons() - 100);
                block.setElements(elements);
                blocks.set(ID-1,block);
                activePlayer.setMoney( activePlayer.getMoney() - 15000);
                activePlayer.setBlocks(blocks);
            }
        }
        else {
            int ID = request.getBlockID();

            if (activePlayer.getMoney()<10000)
            {
                view.logNotPossible();
            }
            if (ID > blocks.size()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1) == null) {
                view.logNotPossible();
            }
            else if (!activePlayer.isHasArmy()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1).getTotalUnemplyedPersons() < 30) {
                view.logNotPossible();
            }

            else {
                Block block = blocks.get(ID-1);
                ArrayList<Element> elements = block.getElements();
                activePlayer.setHasArmy(true);
                Element newElement = new Army();
                elements.add(newElement);
                block.setTotalUnemplyedPersons( block.getTotalUnemplyedPersons() - 30);
                block.setElements(elements);
                blocks.set(ID-1,block);
                activePlayer.setMoney( activePlayer.getMoney() - 10000);

                activePlayer.setBlocks(blocks);
            }

        }
    }

    public void addHomeAction(AddHomeRequest request, Player activePlayer) {

        ArrayList<Block> blocks = activePlayer.getBlocks();
        int numOfFloor = request.getFloorNumbers();
        int numOfUnit = request.getUnitNumbers();
        int ID = request.getBlockID();
        int price = (numOfUnit + numOfFloor)*100 +numOfFloor*300 + 700;

        if (activePlayer.getMoney()<10000)
        {
            view.logNotPossible();
        }
        if (ID > blocks.size()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1) == null) {
            view.logNotPossible();
        }
        else if (!activePlayer.isHasArmy()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
            view.logNotPossible();
        }
        else {
            Block block = blocks.get(ID - 1);
            ArrayList<Element> elements = block.getElements();
            Element newElement = new Home(numOfFloor, numOfUnit);
            elements.add(newElement);
            block.setTotalPersons( block.getTotalPersons() + 5*numOfFloor*numOfUnit);
            block.setTotalUnemplyedPersons( block.getTotalUnemplyedPersons() + 5*numOfFloor*numOfUnit);
            block.setElements(elements);
            blocks.set(ID - 1, block);
            activePlayer.setMoney(activePlayer.getMoney() - price);
            activePlayer.setBlocks(blocks);
        }
        
    }


    public void doneAction (DoneRequest request, Player activePlayer) {

    }

}
