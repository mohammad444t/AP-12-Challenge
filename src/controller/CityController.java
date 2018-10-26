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
    private CommandAnalyzer commandAnalyzer = new CommandAnalyzer();

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
                seeScoreAction((SeeScoreRequest)request, activePlayer);
            } else if (request instanceof UpgradeBlockRequest) {
                upgradeBlockAction((UpgradeBlockRequest)request, activePlayer);
            } else if (request instanceof UpgradeHomeRequest) {
                upgradeHomeAction((UpgradeHomeRequest)request, activePlayer);
            } else if (request instanceof UpgradeWorkPlaceRequest) {
                upgradeWorkPlaceAction((UpgradeWorkPlaceRequest)request, activePlayer);
            } else if (request instanceof YieldRequest) {
                yieldAction((YieldRequest)request, activePlayer);
                isFinished = true;
            } else if (request instanceof BadRequest) {
                badAction();
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
        else if (blocks.get(ID-1).getTotalUnemployedPersons() < 50) {
            view.logNotPossible();
        }
        else {
            Block block = blocks.get(ID-1);
            ArrayList<Element> elements = blocks.get(ID-1).getElements();
            Element newElement = new Bazaar(

            );
            elements.add(newElement);
            block.setTotalUnemployedPersons(block.getTotalUnemployedPersons() - 50);
            block.setBasicPoint(block.getBasicPoint()*1.2);
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
            else if (blocks.get(ID-1).isHasDefence()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
                view.logNotPossible();
            }

            else if (blocks.get(ID-1).getTotalUnemployedPersons() < 100) {
                view.logNotPossible();
            }
            else {
                Block block = blocks.get(ID-1);
                ArrayList<Element> elements = block.getElements();
                block.setHasDefence(true);
                Element newElement = new Defence();
                elements.add(newElement);
                block.setTotalUnemployedPersons(block.getTotalUnemployedPersons() - 100);
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
            else if (ID > blocks.size()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1) == null) {
                view.logNotPossible();
            }
            else if (activePlayer.isHasArmy()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1).getBlockSize() <= blocks.get(ID-1).getElements().size()) {
                view.logNotPossible();
            }
            else if (blocks.get(ID-1).getTotalUnemployedPersons() < 30) {
                view.logNotPossible();
            }

            else {
                Block block = blocks.get(ID-1);
                ArrayList<Element> elements = block.getElements();
                activePlayer.setHasArmy(true);
                Element newElement = new Army();
                elements.add(newElement);
                block.setTotalUnemployedPersons( block.getTotalUnemployedPersons() - 30);
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
        int price = (numOfUnit * numOfFloor)*100 +numOfFloor*300 + 700;

        if (activePlayer.getMoney()<price)
        {
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
        else {
            Block block = blocks.get(ID - 1);
            ArrayList<Element> elements = block.getElements();
            Element newElement = new Home(numOfFloor, numOfUnit);
            elements.add(newElement);
            block.setTotalPersons( block.getTotalPersons() + 5*numOfFloor*numOfUnit);
            block.setTotalUnemployedPersons( block.getTotalUnemployedPersons() + 5*numOfFloor*numOfUnit);
            block.setElements(elements);
            blocks.set(ID - 1, block);
            activePlayer.setMoney(activePlayer.getMoney() - price);
            activePlayer.setBlocks(blocks);
            view.logAdd(elements.size());
        }
        
    }


    public void doneAction (DoneRequest request, Player activePlayer) {
        int money = activePlayer.getMoney();
        ArrayList<Block> blocks = activePlayer.getBlocks();
        for (Block block : blocks) {
            if (block != null) {
                money += 100 * block.getTotalUnemployedPersons();
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


    public int pow (int base , int power){
        int toReturn = 1;
        for (int i = 0 ; i < power ; i++){
            toReturn = toReturn * base;
        }
        return toReturn;
    }

    public void removeBlockAction ( RemoveBlockRequest request, Player activePlayer) {
        ArrayList<Block> blocks = activePlayer.getBlocks();
        int ID = request.getBlockID();
        if (ID > blocks.size()) {
            view.logNotPossible();
        }
        else if (blocks.get(ID-1) == null) {
            view.logNotPossible();
        }
        else if (ID == blocks.size() ) {
            blocks.remove(blocks.size()-1);
            activePlayer.setMoney(activePlayer.getMoney() + 500);
            activePlayer.setBlocks(blocks);
        }
        else {
            blocks.set(ID-1, null);
            activePlayer.setMoney(activePlayer.getMoney() + 500);
            activePlayer.setBlocks(blocks);
        }
    }

    public void removeWorkPlaceAction (RemoveWorkPlaceRequest request, Player activePlayer) {

        ArrayList<Block> blocks = activePlayer.getBlocks();

        int blockID = request.getBlockID();
        int unitID = request.getUnitID();
        if (blockID > blocks.size()) {
            view.logNotPossible();
        }
        else if (blocks.get(blockID-1) == null) {
            view.logNotPossible();
        }
        else {
            Block block = blocks.get(blockID-1);
            ArrayList<Element> elements = block.getElements();
            if (unitID > elements.size()) {
                view.logNotPossible();
            }
            else if (elements.get(unitID-1) == null) {
                view.logNotPossible();
            }
            else if (elements.get(unitID-1) instanceof Home) {
                view.logNotPossible();
            }
            else if (elements.get(unitID-1) instanceof Army || elements.get(unitID-1) instanceof Defence) {
                if (elements.get(unitID-1) instanceof Defence) {
                    block.setHasDefence(false);

                }
                else if (elements.get(unitID-1) instanceof Army) {
                    activePlayer.setHasArmy(false);
                }
                if (unitID == blocks.size() ) {
                    elements.remove(blocks.size()-1);
                    activePlayer.setMoney(activePlayer.getMoney() + 10000);
                    block.setElements(elements);
                    blocks.set(blockID-1, block);
                    activePlayer.setBlocks(blocks);
                }
                else {
                    elements.set(unitID-1, null);
                    activePlayer.setMoney(activePlayer.getMoney() + 10000);
                    block.setElements(elements);
                    blocks.set(blockID-1, block);
                    activePlayer.setBlocks(blocks);
                }
            }

            else if (elements.get(unitID-1) instanceof Bazaar) {
                if (activePlayer.getMoney() < 1000) {
                    view.logNotPossible();
                }
                else if (unitID == blocks.size()) {
                    block.setBasicPoint(block.getBasicPoint()/(1+(((Bazaar) elements.get(unitID-1)).getLevel()*0.2)));
                    elements.remove(blocks.size()-1);
                    activePlayer.setMoney(activePlayer.getMoney() - 1000);
                    block.setElements(elements);
                    blocks.set(blockID-1, block);
                    activePlayer.setBlocks(blocks);
                }
                else {
                    block.setBasicPoint(block.getBasicPoint()/(1+(((Bazaar) elements.get(unitID-1)).getLevel()*0.2)));
                    elements.set(unitID-1, null);
                    activePlayer.setMoney(activePlayer.getMoney() - 1000);
                    block.setElements(elements);
                    blocks.set(blockID-1, block);
                    activePlayer.setBlocks(blocks);
                }
            }
        }
    }

    public void attackAction (AttackRequest request, Player activePlayer, Player deactivePlayer) {
        int ID = request.getBlockID();
        ArrayList<Block> deactiveBlocks = deactivePlayer.getBlocks();
        ArrayList<Block> activeBlocks = activePlayer.getBlocks();

        if (ID > deactiveBlocks.size()) {
            view.logNotPossible();
        }
        else if (deactiveBlocks.get(ID-1) == null) {
            view.logNotPossible();
        }
        else {
            boolean isHaveDefence = deactiveBlocks.get(ID-1).isHasDefence();
            int defenceLevel = 0;
            boolean isHaveArmy = activePlayer.isHasArmy();
            int armyLevel = 0 ;

            for (Element element : deactiveBlocks.get(ID-1).getElements()) {
                if (element instanceof Defence) {
                   defenceLevel = ((Defence) element).getLevel();
                }
            }
            for (Block block : activeBlocks) {
                for (Element element: block.getElements()) {
                    if (element instanceof Army) {
                        armyLevel = ((Army) element).getLevel();
                        break;
                    }
                }
            }
            if ( !isHaveArmy) {
                view.logNotPossible();
            }
            //
            else if (!(isHaveDefence)||defenceLevel < armyLevel)
            {
                if (ID == deactiveBlocks.size()) {
                    deactiveBlocks.remove(deactiveBlocks.size() - 1);
                    deactivePlayer.setBlocks(deactiveBlocks);
                } else {
                    deactiveBlocks.set(ID - 1, null);
                    deactivePlayer.setBlocks(deactiveBlocks);
                }
            }
            else {
                view.logNotPossible();
            }
        }

    }
    public void lootAction(LootRequest request, Player activePlayer, Player deactivePlayer) {
        int ID = request.getBlockID();
        ArrayList<Block> deactiveBlocks = deactivePlayer.getBlocks();
        ArrayList<Block> activeBlocks = activePlayer.getBlocks();

        if (ID > deactiveBlocks.size()) {
            view.logNotPossible();
        }
        else if (deactiveBlocks.get(ID-1) == null) {
            view.logNotPossible();
        }
        if (deactiveBlocks.get(ID-1).isHasDefence() || !activePlayer.isHasArmy()) {
            view.logNotPossible();
        }
        else {
            Block block = deactiveBlocks.get(ID-1);
            int numberOfElements=0;
            for (Element element : block.getElements()) {
                if (element != null) {
                    numberOfElements++;
                }
            }
            activePlayer.setMoney( activePlayer.getMoney() + (500*numberOfElements));
        }

    }
    public void seeGillsAction (SeeGillsRequest request, Player activePlayer) {
        int money = activePlayer.getMoney();
        view.logSeeGills(money);
    }

    public void seeScoreAction (SeeScoreRequest request, Player activePlayer) {
        double score = activePlayer.getScore();

        view.logSeeScore(score);
    }
    public void yieldAction (YieldRequest request, Player activePlayer) {
        double score1=player1.getScore();
        double score2=player2.getScore();


        view.logYield(score1,score2);
    }

    public void upgradeHomeAction ( UpgradeHomeRequest request, Player activePlayer) {
        ArrayList<Block> blocks = activePlayer.getBlocks();
        int isUnit = request.getUnit();
        int isFloor = request.getFloor();
        int blockID = request.getBlockID();
        int unitID = request.getUnitID();

        if (blockID > blocks.size()) {
            view.logNotPossible();
        } else if (blocks.get(blockID - 1) == null) {
            view.logNotPossible();
        } else if (unitID > blocks.get(blockID - 1).getElements().size()) {
            view.logNotPossible();
        } else if (blocks.get(blockID - 1).getElements().get(unitID) == null) {
            view.logNotPossible();
        } else if (blocks.get(blockID - 1).getElements().get(unitID) instanceof Home) {
            Home home =(Home) blocks.get(blockID - 1).getElements().get(unitID);
            if (isUnit == 0 && isFloor == 1) {
                int price = home.getUnit() * 50 + 300;
                if (price < activePlayer.getMoney())
                    view.logNotPossible();
                else  {
                    home.setFloor(home.getFloor()+1);
                    activePlayer.setMoney( activePlayer.getMoney() - price );
                }
            }

            if (isUnit == 1 && isFloor == 1) {
                int price = home.getFloor()*50 + (home.getUnit()+1) * 50 + 300;
                if (price < activePlayer.getMoney())
                    view.logNotPossible();
                else {
                    home.setFloor(home.getFloor() + 1);
                    home.setUnit(home.getUnit() + 1);
                    activePlayer.setMoney( activePlayer.getMoney() - price );
                }
            }

            if (isUnit == 1 && isFloor == 0) {
                int price = home.getFloor()*50;
                if (price < activePlayer.getMoney())
                    view.logNotPossible();
                else {
                    home.setUnit(home.getUnit() + 1);
                    activePlayer.setMoney( activePlayer.getMoney() - price);
                }
            }
        }
    }

    public void badAction() {
        view.logNotPossible();
    }
    public void upgradeBlockAction (UpgradeBlockRequest request , Player activePlayer){
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
                if (elementToUpdate instanceof Army){
                    Army army = (Army)elementToUpdate;
                    int armyLevel = army.getLevel();
                    if (armyLevel > 4 || activePlayer.getMoney() < 20000){
                        view.logNotPossible();
                    } else {
                        int unemployedPersons = blockToUpdate.getTotalUnemployedPersons();
                        if (unemployedPersons < 10){
                            view.logNotPossible();
                        } else {
                            blockToUpdate.setTotalUnemployedPersons( unemployedPersons - 10 );
                            int money = activePlayer.getMoney();
                            activePlayer.setMoney(money - 20000);
                            army.setLevel(armyLevel + 1);
                            elements.set(request.getUnitID() - 1 , army);
                            blockToUpdate.setElements(elements);
                            blocks.set(request.getBlockID() - 1 , blockToUpdate);
                            activePlayer.setBlocks(blocks);

                        }

                    }
                } else if (elementToUpdate instanceof Defence){
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
                } else if (elementToUpdate instanceof Bazaar){
                    Bazaar bazaar = (Bazaar) elementToUpdate;
                    int bazaarLevel = bazaar.getLevel();
                    if (bazaarLevel > 2 || activePlayer.getMoney() < (bazaarLevel - 1) * 5000){
                        view.logNotPossible();
                    } else {
                        int unemployedPersons = blockToUpdate.getTotalUnemployedPersons();
                        if (unemployedPersons < 20){
                            view.logNotPossible();
                        } else {
                            blockToUpdate.setTotalUnemployedPersons( unemployedPersons - 20 );
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
}
