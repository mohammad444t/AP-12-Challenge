package controller;


import model.request.*;

public class CommandAnalyzer {

    private final String ADD_BAZAAR_REQUEST = "add bazaar [0-9]";
    private final String ADD_BLOCK_REQUEST = "add block";
    private final String ADD_ARMY_REQUEST = "add army [0-9]";
    private final String ADD_DEFENCE_REQUEST = "add defense [0-9]";
    private final String ADD_HOME_REQUEST = "add home [0-9] [3-6] [1-4]";
    private final String ATTACK_REQUEST = "attack [0-9]";
    private final String DONE_REQUEST = "done";
    private final String LOOT_REQUEST = "loot [0-9]";
    private final String REMOVE_BLOCK_REQUEST = "remove [0-9]";
    private final String REMOVE_WORK_PLACE_REQUEST = "remove [0-9] [0-9]";
    private final String SEE_GILLS_REQUEST = "see gills";
    private final String SEE_SCORE_REQUEST = "see score";
    private final String UPGRADE_BLOCK_REQUEST = "upgrade [0-9]";
    private final String UPGRADE_HOME_REQUEST_FLOOR = "upgrade [0-9] [0-9] floor";
    private final String UPGRADE_HOME_REQUEST_UNIT = "upgrade [0-9] [0-9] unit";
    private final String UPGRADE_HOME_REQUEST_FLOOR_UNIT = "upgrade [0-9] [0-9] floor unit";
    private final String UPGRADE_WORK_PLACE_REQUEST = "upgrade [0-9] [0-9]";
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
            return new AddGilgArmyRequest(Integer.parseInt(params[2]) , "army");
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
            return new UpgaradeBlockRequest(Integer.parseInt(params[1]));
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
