import java.util.Scanner;
import java.util.ArrayList;

public class ALL { public static void main(String[] args) {new CityController().listenForCommand();}}


class View {
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

class Player {
    private int money = 30000;
    private java.util.ArrayList<Block> blocks = new ArrayList<Block>();
    private boolean hasArmy = false;
    private double attackScore = 0;

    public int getMoney() {
        return money;
    }

    public boolean isHasArmy() {
        return hasArmy;
    }

    public void setHasArmy(boolean hasArmy) {
        this.hasArmy = hasArmy;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getAttackScore() {
        return attackScore;
    }

    public void setAttackScore(double attackScore) {
        this.attackScore = attackScore;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public double getScore() {
        double score = 0;
        for (Block block : this.blocks)
            if (block != null) {
                score += block.getScore();
            }
        return score;
    }
}

class Home extends Element {
    private int floor;
    private int unit;

    public Home(int floor, int unit) {
        this.floor = floor;
        this.unit = unit;
    }

    @Override
    public double getScore() {
        return 0;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}

abstract class Element {
    public abstract double getScore();
}

class Defence extends Element {
    private int level;
    private int[][] personsDetails = new int[5][2];

    public Defence() {
        this.personsDetails[0][0] = 30;
        this.level = 1;
    }

    @Override
    public double getScore() {
        return Math.pow(15, this.personsDetails[0][1]);
    }

    public double getPersonsIncome() {
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
        this.level = level;
    }
}

class Block {

    private java.util.ArrayList<Element> elements = new ArrayList<Element>();
    private boolean hasDefence = false;
    private double basicPoint = 1;
    private int blockSize;
    private int totalPersons = 0;
    private int totalUnemployedPersons = 0;

    public boolean isHasDefence() {
        return hasDefence;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public double getBasicPoint() {
        return basicPoint;
    }

    public void setBasicPoint(double basicPoint) {
        this.basicPoint = basicPoint;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public int getTotalUnemployedPersons() {
        return totalUnemployedPersons;
    }

    public void setTotalUnemployedPersons(int totalUnemployedPersons) {
        this.totalUnemployedPersons = totalUnemployedPersons;
    }

    public void setHasDefence(boolean hasDefence) {
        this.hasDefence = hasDefence;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public double getScore() {
        double score = 0;
        for (Element element : this.elements)
            if(element instanceof Home) {
                Home home = (Home)element;
                double persons = this.basicPoint * home.getFloor() * home.getUnit() * 5;
                double units = home.getUnit() * home.getFloor() * (2 + 5 * this.basicPoint);
                double floors = home.getFloor() * (3 + home.getUnit() * (2 + 5 * this.basicPoint) +
                        home.getUnit() * 5 * this.basicPoint * 2);
                double homePoint = 10 + floors + units * 2 + persons * 3;
                score += homePoint + floors + units + persons;
            }
            else {
                if (element.getScore() == 1) {
                    if (element instanceof Army)
                        score += 10;
                    else if (element instanceof Defence)
                        score += 15;
                    else if (element instanceof Bazaar)
                        score += 5;
                }
                else
                    if (element != null)
                        score += element.getScore();
            }
        return score;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getPersons() {
        return 0;
    }

    public double geUnemployedPersons() {
        return 0;
    }
}

class Bazaar extends Element {
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

class Army extends Element {
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

class YieldRequest extends Request {

    public YieldRequest() {
    }
}

class UpgradeWorkPlaceRequest extends Request {
    private int blockID;
    private int unitID;

    public UpgradeWorkPlaceRequest(int blockID, int unitID) {
        this.blockID = blockID;
        this.unitID = unitID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}

class UpgradeHomeRequest extends Request {
    private int blockID;
    private int floor;
    private int unit;
    private int unitID;


    public UpgradeHomeRequest(int blockID, int unitID, int floor, int unit) {
        this.blockID = blockID;
        this.floor = floor;
        this.unit = unit;
        this.unitID = unitID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}

class UpgradeBlockRequest extends Request {
    private int blockID;

    public UpgradeBlockRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}

class SeeScoreRequest extends Request {

    public SeeScoreRequest() {
    }
}

class SeeGillsRequest extends Request {

    public SeeGillsRequest() {
    }
}

class Request {

    public Request() {
    }

}

class RemoveWorkPlaceRequest extends Request {
    private int blockID;
    private int unitID;

    public RemoveWorkPlaceRequest(int blockID, int unitID) {
        this.blockID = blockID;
        this.unitID = unitID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}

class RemoveBlockRequest extends Request {
    private int blockID;

    public RemoveBlockRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}

class LootRequest extends Request {
    private int blockID;

    public LootRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}

class DoneRequest extends Request {
    public DoneRequest() {
    }

}

class BadRequest extends Request {
    public BadRequest() {
    }
}

class AttackRequest extends Request {

    private int blockID;

    public AttackRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}

class AddHomeRequest extends Request {
    private int blockID;
    private int floorNumbers;
    private int unitNumbers;

    public AddHomeRequest(int blockID, int floorNumbers, int unitNumbers) {
        this.blockID = blockID;
        this.floorNumbers = floorNumbers;
        this.unitNumbers = unitNumbers;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getFloorNumbers() {
        return floorNumbers;
    }

    public void setFloorNumbers(int floorNumbers) {
        this.floorNumbers = floorNumbers;
    }

    public int getUnitNumbers() {
        return unitNumbers;
    }

    public void setUnitNumbers(int unitNumbers) {
        this.unitNumbers = unitNumbers;
    }
}

class AddGilgArmyRequest extends Request {
    private int blockID;
    private String type;

    public AddGilgArmyRequest(int blockID, String type) {
        this.blockID = blockID;
        this.type = type;
    }

    public AddGilgArmyRequest(String type) {
        this.type = type;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class AddBlockRequest extends Request {

    public AddBlockRequest() {
    }


}

class AddBazaarRequest extends Request {
    private int blockID;

    public AddBazaarRequest(int blockID) {
        this.blockID = blockID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}

class CommandAnalyzer {

    private final String ADD_BAZAAR_REQUEST = "add bazaar ([1-9]|[1-9][0-9])";
    private final String ADD_BLOCK_REQUEST = "add block";
    private final String ADD_ARMY_REQUEST = "add army ([1-9]|[1-9][0-9])";
    private final String ADD_DEFENCE_REQUEST = "add defense ([1-9]|[1-9][0-9])";
    private final String ADD_HOME_REQUEST = "add home ([1-9]|[1-9][0-9]) [3-6] [1-4]";
    private final String ATTACK_REQUEST = "attack ([1-9]|[1-9][0-9])";
    private final String DONE_REQUEST = "done";
    private final String LOOT_REQUEST = "loot ([1-9]|[1-9][0-9])";
    private final String REMOVE_BLOCK_REQUEST = "remove ([1-9]|[1-9][0-9])";
    private final String REMOVE_WORK_PLACE_REQUEST = "remove ([1-9]|[1-9][0-9]) ([1-9]|[1-9][0-9])";
    private final String SEE_GILLS_REQUEST = "see gills";
    private final String SEE_SCORE_REQUEST = "see score";
    private final String UPGRADE_BLOCK_REQUEST = "upgrade ([1-9]|[1-9][0-9])";
    private final String UPGRADE_HOME_REQUEST_FLOOR = "upgrade ([1-9]|[1-9][0-9]) ([1-9]|[1-9][0-9]) floor";
    private final String UPGRADE_HOME_REQUEST_UNIT = "upgrade ([1-9]|[1-9][0-9]) ([1-9]|[1-9][0-9]) unit";
    private final String UPGRADE_HOME_REQUEST_FLOOR_UNIT = "upgrade ([1-9]|[1-9][0-9]) ([1-9]|[1-9][0-9]) floor unit";
    private final String UPGRADE_WORK_PLACE_REQUEST = "upgrade ([1-9]|[1-9][0-9]) ([1-9]|[1-9][0-9])";
    private final String YIELD_REQUEST = "yield";

    public Request getRequest (String command){
        if (command.matches(ADD_BAZAAR_REQUEST)){
            String[] params = command.split(" ");
            return new AddBazaarRequest(Integer.parseInt(params[2]));
        } else if (command.matches(ADD_BLOCK_REQUEST)){
            return new AddBlockRequest();
        } else if (command.matches(ADD_ARMY_REQUEST)) {
            String[] params = command.split(" ");
            return new AddGilgArmyRequest(Integer.parseInt(params[2]) , "army");
        }else if (command.matches(ADD_DEFENCE_REQUEST)){
            String[] params = command.split(" ");
            return new AddGilgArmyRequest(Integer.parseInt(params[2]) , "defense");
        }
        else if (command.matches(ADD_HOME_REQUEST)){
            String[] params = command.split(" ");
            return new AddHomeRequest(Integer.parseInt(params[2]) , Integer.parseInt(params[3]) ,
                    Integer.parseInt(params[4]));
        } else if (command.matches(ATTACK_REQUEST)){
            String[] params = command.split(" ");
            return new AttackRequest(Integer.parseInt(params[1]));
        } else if (command.matches(DONE_REQUEST)){
            return new DoneRequest();
        } else if (command.matches(LOOT_REQUEST)){
            String[] params = command.split(" ");
            return new LootRequest(Integer.parseInt(params[1]));
        } else if (command.matches(REMOVE_BLOCK_REQUEST)) {
            String[] params = command.split(" ");
            return new RemoveBlockRequest(Integer.parseInt(params[1]));
        } else if (command.matches(REMOVE_WORK_PLACE_REQUEST)){
            String[] params = command.split(" ");
            return new RemoveWorkPlaceRequest(Integer.parseInt(params[1]) , Integer.parseInt(params[2]));
        } else if (command.matches(SEE_GILLS_REQUEST)){
            return new SeeGillsRequest();
        } else if (command.matches(SEE_SCORE_REQUEST)){
            return new SeeScoreRequest();
        } else if (command.matches(UPGRADE_BLOCK_REQUEST)) {
            String[] params = command.split(" ");
            return new UpgradeBlockRequest(Integer.parseInt(params[1]));
        } else if (command.matches(UPGRADE_HOME_REQUEST_FLOOR)){
            String[] params = command.split(" ");
            return new UpgradeHomeRequest(Integer.parseInt(params[1]) , Integer.parseInt(params[2]) , 1 , 0);
        } else if (command.matches(UPGRADE_HOME_REQUEST_UNIT)){
            String[] params = command.split(" ");
            return new UpgradeHomeRequest(Integer.parseInt(params[1]) , Integer.parseInt(params[2]) , 0 , 1);
        } else if (command.matches(UPGRADE_HOME_REQUEST_FLOOR_UNIT)){
            String[] params = command.split(" ");
            return new UpgradeHomeRequest(Integer.parseInt(params[1]) , Integer.parseInt(params[2]) , 1 , 1);
        } else if (command.matches(UPGRADE_WORK_PLACE_REQUEST)){
            String[] params = command.split(" ");
            return new UpgradeWorkPlaceRequest(Integer.parseInt(params[1]) , Integer.parseInt(params[2]));
        } else if (command.matches(YIELD_REQUEST)){
            return new YieldRequest();
        } else {
            return new BadRequest();
        }



    }



}

class CityController {
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
                if (isPlayer1Active)
                    yieldAction((YieldRequest)request, activePlayer, player2);
                else
                    yieldAction((YieldRequest)request, activePlayer, player1);
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
            else if (activePlayer.isHasArmy()) {
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
                activePlayer.setHasArmy(true);
                Element newElement = new Army();
                elements.add(newElement);
                block.setTotalUnemployedPersons(block.getTotalUnemployedPersons() - 100);
                block.setElements(elements);
                blocks.set(ID-1,block);
                activePlayer.setMoney( activePlayer.getMoney() - 15000);
                activePlayer.setBlocks(blocks);
                view.logAdd(elements.size());
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
            else if (blocks.get(ID-1).isHasDefence()) {
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
                blocks.get(ID-1).setHasDefence(true);
                Element newElement = new Defence();
                elements.add(newElement);
                block.setTotalUnemployedPersons( block.getTotalUnemployedPersons() - 30);
                block.setElements(elements);
                blocks.set(ID-1,block);
                activePlayer.setMoney( activePlayer.getMoney() - 10000);
                view.logAdd(elements.size());
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
    public void yieldAction (YieldRequest request, Player activePlayer, Player deactivePlayer) {
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
        double score1 = activePlayer.getScore();
        double score2 = deactivePlayer.getScore();


        view.logYield(score1, score2);
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
        } else if (blocks.get(blockID - 1).getElements().get(unitID-1) == null) {
            view.logNotPossible();
        } else if (blocks.get(blockID - 1).getElements().get(unitID-1) instanceof Home) {
            Home home =(Home) blocks.get(blockID - 1).getElements().get(unitID-1);
            Block block = blocks.get(blockID-1);
            if (isUnit == 0 && isFloor == 1) {
                int price = home.getUnit() * 50 + 300;
                if (price > activePlayer.getMoney())
                    view.logNotPossible();
                else  {
                    home.setFloor(home.getFloor()+1);
                    block.setTotalPersons(block.getTotalPersons() + 5 * home.getUnit());
                    block.setTotalUnemployedPersons(block.getTotalUnemployedPersons() + 5 * home.getUnit());
                    activePlayer.setMoney( activePlayer.getMoney() - price );
                }
            }

            if (isUnit == 1 && isFloor == 1) {
                int price = home.getFloor()*50 + (home.getUnit()+1) * 50 + 300;
                if (price > activePlayer.getMoney())
                    view.logNotPossible();
                else {
                    home.setFloor(home.getFloor() + 1);
                    home.setUnit(home.getUnit() + 1);
                    block.setTotalPersons(block.getTotalPersons() + 5 * (home.getUnit()+(home.getFloor()-1)));
                    block.setTotalUnemployedPersons(block.getTotalUnemployedPersons() +  5 * (home.getUnit()+(home.getFloor()-1)));
                    activePlayer.setMoney( activePlayer.getMoney() - price );
                }
            }

            if (isUnit == 1 && isFloor == 0) {
                int price = home.getFloor()*50;
                if (price > activePlayer.getMoney())
                    view.logNotPossible();
                else {
                    home.setUnit(home.getUnit() + 1);
                    block.setTotalPersons(block.getTotalPersons() + 5 * home.getFloor());
                    block.setTotalUnemployedPersons(block.getTotalUnemployedPersons() + 5 * home.getFloor());
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
