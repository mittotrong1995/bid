package server;

import adt.Auction;
import adt.Client;
import adt.Item;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

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
            System.out.println(action + "govance");
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
        Client client = new Client(parts[6]);
        (auction.getClients()).add(client);
        auctionList.add((Auction)auction);
        String out = in;
        return out;
    }

    private String listAction(String in) {
        String out = "Bosko says: FART!";
        return out;
    }

    private String registerAction(String in) {
        
        String [] parts = null ;
        parts = in.split(token);

        Auction currentAuction = getAuction(parts[1]);
        Client client= new Client(parts[2]);
        (currentAuction.getClients()).add(client);

        String out = "You have been registerd to auction " + parts[1];
        return out;
    }

    private String bidAction(String in) {
        String [] parts = null ;
        parts = in.split(token);       

        Auction currentAuction = getAuction(parts[1]);
        String out = "";
        if(currentAuction.getHighestBid() < Integer.parseInt(parts[2])){
        currentAuction.setHighestBid(Integer.parseInt(parts[2]));
        Date d = new Date();
            out = "New highest bid has been set for auction " + currentAuction.getAuctionID() + " the bid is:" +  currentAuction.getHighestBid() + "at time: " + d.toGMTString();
            Vector biddingPair = new Vector();
            biddingPair.add(parts[2]);
            biddingPair.add("-");
            biddingPair.add(parts[3]);
            biddingPair.add("-");
            biddingPair.add(d);
            currentAuction.getBiddingHistory().add(biddingPair);
        }
        else
            out = "This is not the highest bid";

        return out;
    }

    private String highestAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        for(int i = 0; i < parts.length; i++)
            System.out.println(parts[i]);
        Auction currentAuction = getAuction(parts[1]);
        String out = "";

        out += ((Vector)currentAuction.getBiddingHistory().get(currentAuction.getBiddingHistory().size() - 1)).get(0) +"-" +  ((Vector)currentAuction.getBiddingHistory().get(currentAuction.getBiddingHistory().size() -1)).get(4);
        System.out.println(out + " ssss");
        return out;
    }

    private String historyAction(String in) {
        String [] parts = null ;
        parts = in.split(token);  
        Auction currentAuction = getAuction(parts[1]);

        String out = "";
        out+= currentAuction.getAuctionID() + currentAuction.getItem().getName() + currentAuction.getItem().getDescription() + currentAuction.getSellerIP() + currentAuction.getItem().getStartingPrize() + token;
        for(int i = 0; i < currentAuction.getBiddingHistory().size(); i++)
        {
            for(int j = 0; j < ((Vector)currentAuction.getBiddingHistory().get(i)).size() - 2;j++ )
                out += ((Vector)currentAuction.getBiddingHistory().get(i)).get(j);
        }
        
        return out;
    }

    private String withdrawAction(String in) {
        String out = "";
        return out;
    }

    private String participantsAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        Auction currentAuction = getAuction(parts[1]);

        String out = "";
        for(int i = 0; i< (currentAuction.getClients()).size();i++)
        {
            out += ((Client)(currentAuction.getClients()).get(i)).getIp() + token;
        }
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

    private Auction getAuction(String s)
    {
        Auction currentAuction = null;
        for(int i = 0; i< auctionList.size(); i++)
        {
            if(((Auction)auctionList.get(i)).getAuctionID() == Integer.parseInt(s))
            {
                currentAuction =((Auction)auctionList.get(i));
                break;
            }
        }

        return currentAuction;
    }
}
