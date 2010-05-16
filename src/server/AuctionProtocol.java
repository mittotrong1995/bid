package server;

import adt.Auction;
import adt.Item;
import java.util.LinkedList;
import java.util.List;

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
    private static final byte TABLE = 9;
    private static List auctionList = new LinkedList();
    private static String token = "#@";

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
                case 9: theOutput = tableAction(theInput);
                        break;
                default:
                        break;
            }
        }
        return theOutput;
    }

    public static List getAuctionList() {
        return auctionList;
    }

    private String advertiseAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        for(int i = 0; i < parts.length; i++)
            System.out.println(parts[i]);
        Item item = new Item(parts[4],parts[5],Double.parseDouble(parts[2]));
        int id = 1;
        if(auctionList.size() > 0){
            id = ((Auction)auctionList.get(auctionList.size() - 1)).getAuctionID()  + 1;}

        Auction auction = new Auction(id,Integer.parseInt(parts[3]),Byte.parseByte(parts[1]),Double.parseDouble(parts[2]),parts[6],item);
        auctionList.add((Auction)auction);
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

    private String tableAction(String theInput) {
        String out = "9";
        for(int i = 0;i < auctionList.size(); i++)
            out += token + ((Auction)(auctionList.get(i))).getAuctionID() + token + ((Auction)(auctionList.get(i))).getItem().getName() + token + ((Auction)(auctionList.get(i))).getItem().getStartingPrize() + token + ((Auction)(auctionList.get(i))).getSellerIP() + token + true;
        return out;
    }
}
