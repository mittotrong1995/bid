package server;

public class AuctionProtocol {

    private static final byte ADVERTISE = 0;
    private static final byte LIST = 1;
    private static final byte REGISTER = 2;
    private static final byte BID = 3;
    private static final byte HIGHEST = 4;
    private static final byte HISTORY = 5;
    private static final byte WITHDRAW = 6;
    private static final byte PARTICIPANTS = 7;
    private static final byte MESSAGE = 8;

    public String processInput(String theInput) {
        String theOutput = "";

        if (!theInput.isEmpty()){
            byte action = Byte.parseByte(theInput.substring(0, 1));
            switch(action){
                case 0: theOutput = advertiseAction(theInput);
                        break;
                case 1: theOutput = listAction(theInput);
                        break;
                case 2: theOutput = registerAction(theInput);
                        break;
                case 3: theOutput = bidAction(theInput);
                        break;
                case 4: theOutput = highestAction(theInput);
                        break;
                case 5: theOutput = historyAction(theInput);
                        break;
                case 6: theOutput = withdrawAction(theInput);
                        break;
                case 7: theOutput = participantsAction(theInput);
                        break;
                case 8: theOutput = messageAction(theInput);
                        break;
                default:
                        break;
            }
        }
        return theOutput;
    }

    private String advertiseAction(String in) {
        String out = in;
        return out;
    }

    private String listAction(String in) {
        String out = "Bosko says: FART!";
        return out;
    }

    private String registerAction(String in) {
        String out = "";
        return out;
    }

    private String bidAction(String in) {
        String out = "";
        return out;
    }

    private String highestAction(String in) {
        String out = "";
        return out;
    }

    private String historyAction(String in) {
        String out = "";
        return out;
    }

    private String withdrawAction(String in) {
        String out = "";
        return out;
    }

    private String participantsAction(String in) {
        String out = "";
        return out;
    }

    private String messageAction(String in) {
        String out = in;
        return out;
    }
}
