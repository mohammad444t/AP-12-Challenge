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

        if (ID > blocks.size()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
            view.logNotPossible();
        }
        else {
            ArrayList<Element> elements = blocks.get(ID-1).getElements();
            Element newElement = new Bazaar(

            );
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

    }
    public void upgradeBlockAction (UpgaradeBlockRequest request , Player activePlayer){
        ArrayList<Block> blocks = activePlayer.getBlocks();
        if (request.getBlockID() > blocks.size() ){
            view.logNotPossible();
        } else if (blocks.get(request.getBlockID() - 1) == null){
            view.logNotPossible();
        }
        else {

            int blockSize = blocks.get(request.getBlockID() - 1).getBlockSize();
            int blockLevel = (blockSize - 10) / 5;
            if (blockLevel > 2 || pow(500 , blockLevel) > activePlayer.getMoney()){
                view.logNotPossible();
            } else{
                activePlayer.setMoney(activePlayer.getMoney() - pow(500 , blockLevel));
                blockSize += 5;
                Block blockToUpdate = blocks.get(request.getBlockID() - 1);
                blockToUpdate.setBlockSize(blockSize);
                blocks.set(request.getBlockID() - 1 , blockToUpdate);
                activePlayer.setBlocks(blocks);
            }
        }
    }

    public void upgradeWorkPlaceAction (UpgradeWorkPlaceRequest request , Player activePlayer){
        ArrayList<Block> blocks = activePlayer.getBlocks();
        if (request.getBlockID() > blocks.size() ){
            view.logNotPossible();
        } else if (blocks.get(request.getBlockID() - 1) == null){
            view.logNotPossible();
        } else{
            Block blockToUpdate = blocks.get(request.getBlockID() - 1);
            ArrayList<Element> elements = blockToUpdate.getElements();
            if (request.getUnitID() >  elements.size()){
                view.logNotPossible();
            } else if (elements.get(request.getUnitID() - 1) == null){
                view.logNotPossible();
            } else{
                Element elementToUpdate = elements.get(request.getUnitID() - 1);
                if (elementToUpdate instanceof model.Army){
                    Army army = (Army)elementToUpdate;
                    int armyLevel = army.getLevel();
                    if (armyLevel > 4 || activePlayer.getMoney() < 20000){
                        view.logNotPossible();
                    } else {
                        int unemployedPersons = blockToUpdate.getTotalUnemplyedPersons();
                        if (unemployedPersons < 10){
                            view.logNotPossible();
                        } else {
                            blockToUpdate.setTotalUnemplyedPersons( unemployedPersons - 10 );
                            int money = activePlayer.getMoney();
                            activePlayer.setMoney(money - 20000);
                            army.setLevel(armyLevel + 1);
                            elements.set(request.getUnitID() - 1 , army);
                            blockToUpdate.setElements(elements);
                            blocks.set(request.getBlockID() - 1 , blockToUpdate);
                            activePlayer.setBlocks(blocks);

                        }

                    }
                } else if (elementToUpdate instanceof model.Defence){
                    Defence defence = (Defence) elementToUpdate;
                    int defenceLevel = defence.getLevel();
                    if (defenceLevel > 4 || activePlayer.getMoney() < 5000){
                        view.logNotPossible();
                    } else {
                        int money = activePlayer.getMoney();
                        activePlayer.setMoney(money - 5000);
                        defence.setLevel(defenceLevel + 1);
                        elements.set(request.getUnitID() - 1 , defence);
                        blockToUpdate.setElements(elements);
                        blocks.set(request.getBlockID() - 1 , blockToUpdate);
                        activePlayer.setBlocks(blocks);
                    }
                } else if (elementToUpdate instanceof model.Bazaar){
                    Bazaar bazaar = (Bazaar) elementToUpdate;
                    int bazaarLevel = bazaar.getLevel();
                    if (bazaarLevel > 2 || activePlayer.getMoney() < (bazaarLevel - 1) * 5000){
                        view.logNotPossible();
                    } else {
                        int unemployedPersons = blockToUpdate.getTotalUnemplyedPersons();
                        if (unemployedPersons < 20){
                            view.logNotPossible();
                        } else {
                            blockToUpdate.setTotalUnemplyedPersons( unemployedPersons - 20 );
                            int money = activePlayer.getMoney();
                            activePlayer.setMoney(money - (bazaarLevel - 1) * 5000);
                            if (bazaarLevel == 1){
                                blockToUpdate.setBasicPoint(blockToUpdate.getBasicPoint() * 1.4 / 1.2);
                            } else if (bazaarLevel == 2){
                                blockToUpdate.setBasicPoint(blockToUpdate.getBasicPoint() * 1.6 / 1.4);
                            }
                            bazaar.setLevel(bazaarLevel + 1);
                            elements.set(request.getUnitID() - 1 , bazaar);
                            blockToUpdate.setElements(elements);
                            blocks.set(request.getBlockID() - 1 , blockToUpdate);
                            activePlayer.setBlocks(blocks);
                        }
                    }
                }
            }
        }


    }
    public int pow (int base , int power){
        int toReturn = 1;
        for (int i = 0 ; i < power ; i++){
            toReturn = toReturn * base;
        }
        return toReturn;
    }
}
