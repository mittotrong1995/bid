package server;

import adt.Auction;
import adt.Client;
import adt.Item;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static final byte NOTIFY_ALL = 11;
    private static final byte DISCONNECT = 13;
    private static List auctionList = new LinkedList();
    private static String token = "#@";

    public static List getAuctionList() {
        return auctionList;
    }

    public String advertiseAction(String in,List<TCPServerThread> tcpST) {
        String [] parts = null ;
        parts = in.split(token);
        //final String time = parts[7];
        for(int i = 0; i < parts.length; i++)
            System.out.println(parts[i]);
        Item item = new Item(parts[4],parts[5],Double.parseDouble(parts[2]));
        int id = 1;
        if(auctionList.size() > 0){
            id = ((Auction)auctionList.get(auctionList.size() - 1)).getAuctionID()  + 1;}

        Auction auction = new Auction(id,Integer.parseInt(parts[3]),Byte.parseByte(parts[1]),Double.parseDouble(parts[2]),parts[6],item,Integer.parseInt(parts[7]));
        Client client = new Client(parts[6]);
        (auction.getClients()).add(client);
        auctionList.add((Auction)auction);
        
        if(parts[1].equals("1"))
        {
            startClosing(auction,tcpST);
        }
//        String str = "9" + token + auction.getAuctionID();
//        tableAction(str);
        String out = "";
        return out;
    }

    public String listAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        String out = "1" + token;
        for(int i = 0;i < auctionList.size(); i++)
        {
            if(((Auction)auctionList.get(i)).isIsActive())
                out += ((Auction)(auctionList.get(i))).getAuctionID() + token + ((Auction)(auctionList.get(i))).getItem().getName() + token +((Auction)(auctionList.get(i))).getItem().getDescription() + token+ ((Auction)(auctionList.get(i))).getItem().getStartingPrize() + token + ((Auction)(auctionList.get(i))).getHighestBid() +token+((Auction)(auctionList.get(i))).getSellerIP() + token;
        }
        if(out.equals("1" + token))
            out = "There are no Active auctions";
        return out;
    }

    public String registerAction(String in) {
        
        String [] parts = null ;
        parts = in.split(token);

        String out= "";
        Auction currentAuction = getAuction(parts[1]);
        if(currentAuction.isIsActive()){
        Client client= new Client(parts[2]);
    
        if(isRegistered(currentAuction,parts[2]) == false)
        {
            (currentAuction.getClients()).add(client);
             out = "You have been registerd to auction " + parts[1];
        }
        else
            out = "You have already been registered to this auction";
        }
        else
            out = "This auction is not active any more!";
        return out;
    }

    public String bidAction(String in,List<TCPServerThread> tcpST) {
        String [] parts = null ;
        parts = in.split(token);       

        Auction currentAuction = getAuction(parts[1]);
        String out = "";

         if(currentAuction.isIsActive()){
        if(currentAuction.getHighestBid() < Double.parseDouble(parts[2]) && isRegistered(currentAuction,parts[3])){
        currentAuction.setHighestBid(Double.parseDouble(parts[2]));
        Date d = new Date();
            Vector biddingPair = new Vector();
            biddingPair.add(parts[2]);
            biddingPair.add("-");
            biddingPair.add(parts[3]);
            biddingPair.add("-");
            biddingPair.add(d);
            currentAuction.getBiddingHistory().add(biddingPair);
            String msgToParticipants = "Auction ID: " + currentAuction.getAuctionID()+token+ "Item Name: "+ currentAuction.getItem().getName()+token+"New Bid: "+parts[2]+token+"Bidder IP: "+parts[3];
            System.out.println(msgToParticipants);
            notifyParticipants(msgToParticipants,currentAuction,tcpST,parts[3]);
        }
        else if(currentAuction.getHighestBid() >= Integer.parseInt(parts[2]))
        {
            out = "This is not the highest bid";
        }

        else if(isRegistered(currentAuction,parts[3]) == false)
        {
            out = "You have not been registered to this auction";
        }
        }
        else
            out = "This auction is not active any more!";
                   
        return out;
    }

    public String highestAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        Auction currentAuction = getAuction(parts[1]);
        String out = "";
         //if(currentAuction.isIsActive()){
        if (currentAuction.getBiddingHistory().size() >0)
            out += "The highest bid is: " +  ((Vector)currentAuction.getBiddingHistory().get(currentAuction.getBiddingHistory().size() - 1)).get(0) +" and was placed at: " +  ((Vector)currentAuction.getBiddingHistory().get(currentAuction.getBiddingHistory().size() -1)).get(4);
        else
            out += "Still no bid has been placed for this auction";
//        }
//        else
//            out = "This auction is not active any more!";
        return out;
    }

    public String historyAction(String in) {
        String [] parts = null ;
        parts = in.split(token);  
        Auction currentAuction = getAuction(parts[1]);

        String out = "5" + token;
         //if(currentAuction.isIsActive()){
        if(currentAuction.getBiddingHistory().size() > 0)
        {
        out+= "Auction ID: " + currentAuction.getAuctionID() + token +",  Item name: " +currentAuction.getItem().getName() + token +",  Item Description: " +currentAuction.getItem().getDescription() +token +",  Seller IP: " +currentAuction.getSellerIP() +token +",  Starting Price: "+currentAuction.getItem().getStartingPrize() + token;
        for(int i = 0; i < currentAuction.getBiddingHistory().size(); i++)
        {
            //for(int j = 0; j < ((Vector)currentAuction.getBiddingHistory().get(i)).size() - 2;j++ )
                out += "Bid : " + ((Vector)currentAuction.getBiddingHistory().get(i)).get(0) +token+ " Bidder : " + ((Vector)currentAuction.getBiddingHistory().get(i)).get(2) + token;
        }
        }
        else
            out = "No bids have been placed yet!";
//        }
//        else
//            out = "This auction is not active any more!";
        return out;
    }

    public String withdrawAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        Auction currentAuction = getAuction(parts[1]);
        String out = "";

         if(currentAuction.isIsActive()){
        if(isRegistered(currentAuction,parts[2])){
            if(!currentAuction.getSellerIP().equals(parts[2]))
            {
                if(isHighestBidder(currentAuction.getBiddingHistory(),parts[2]))
                    out = "Sorry you cannot withdraw from this auction. You are the highest bidder!";
                else
                {
                    for(int i = 0; i < currentAuction.getClients().size(); i++)
                    {
                        if(((Client)(currentAuction.getClients().get(i))).getIp().equals(parts[2]))
                        {
                            currentAuction.getClients().remove(i);
                            break;
                        }
                    }
                out = "withdrawn from auction " + parts[1];
                }
            }
            else
            {
                out = "You cannot withdraw from this auction.You are the creator!";
            }
        }
        else
            out = "You are not registered in this auction in order to withdraw!";

        }
        else
            out = "This auction is not active any more!";
        return out;
    }

    public String participantsAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        Auction currentAuction = getAuction(parts[1]);

        String out = "7" + token + currentAuction.getAuctionID() + token;
         if(currentAuction.isIsActive()){
        for(int i = 0; i< (currentAuction.getClients()).size();i++)
        {
            out += ((Client)(currentAuction.getClients()).get(i)).getIp() + token;
        }
        }
        else
            out = "This auction is not active any more!";
        return out;
    }

    public String messageAction(String in, List<TCPServerThread> tcpST) {
        String [] parts = null ;
        parts = in.split(token);

        String out = "";
        for(int i = 0 ; i < tcpST.size(); i++)
        {
            System.out.println((((TCPServerThread)tcpST.get(i)).getSocket().getInetAddress().toString()).replace("/","") + " blaaaaa" + parts[1]);
            if((((TCPServerThread)tcpST.get(i)).getSocket().getInetAddress().toString()).replace("/","").equals(parts[1]))
            {
                out = "8"+token+parts[2]+token+parts[3];
              ((TCPServerThread)tcpST.get(i)).getOut().println(out);
            }
        }
        if(out.equals(""))
            return "The IP address you have entered is incorrect";

        return "";
    }

    public String tableAction(String in) {
        String [] parts = null ;
        parts = in.split(token);
        String out = "9";
        for(int i = 0;i < auctionList.size(); i++)
        {
            out += token + ((Auction)(auctionList.get(i))).getAuctionID() + token + ((Auction)(auctionList.get(i))).getItem().getName() + token + ((Auction)(auctionList.get(i))).getItem().getStartingPrize() + token + ((Auction)(auctionList.get(i))).getSellerIP() + token + isRegistered(parts[1],((Auction)auctionList.get(i)).getClients());
        }
        return out;
    }

    public Auction getAuction(String s)
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

    public boolean isRegistered(String string, List clients) {
        for(int i = 0; i < clients.size(); i++)
        {
            if(((Client)clients.get(i)).getIp().equals(string))
                return true;
        }
        return false;
    }


    private void notifyParticipants(String msgToParticipants,Auction auct,List<TCPServerThread> tcpST,String senderIP) {
        for(int i = 0; i < auct.getClients().size(); i++)
        {
            for(int j = 0 ; j < tcpST.size(); j++)
            {
                if((((TCPServerThread)tcpST.get(j)).getSocket().getInetAddress().toString()).replace("/","").equals(((Client)auct.getClients().get(i)).getIp()))
                {
                    if (!(((TCPServerThread)tcpST.get(j)).getSocket().getInetAddress().toString()).replace("/","").equals(senderIP))
                        ((TCPServerThread)tcpST.get(j)).getOut().println("11"+token+msgToParticipants);
                }
            }
        }
    }

    private boolean isRegistered(Auction auct,String ip) {

        for(int i = 0; i < (auct.getClients()).size(); i++)
        {
            if(((Client)auct.getClients().get(i)).getIp().equals(ip))
                return true;
        }
        return false;
    }

    private void startClosing(final Auction auct, List<TCPServerThread> tcpST) {
            TCPServerThread tcp = null;
            for(int i = 0 ; i < tcpST.size(); i++)
        {
            //System.out.println((((TCPServerThread)tcpST.get(i)).getSocket().getInetAddress().toString()).replace("/","") + " blaaaaa" + parts[1]);
            if((((TCPServerThread)tcpST.get(i)).getSocket().getInetAddress().toString()).replace("/","").equals(auct.getSellerIP()))
            {
              tcp = (TCPServerThread)tcpST.get(i);
            }
        }
        TimerThread timeThread= new TimerThread(auct.getTimer(),tcp.getOut(),auct,tcpST,token);
            timeThread.start();

    }

    public boolean isHighestBidder(List biddingHistory,String ip)
    {
        if(biddingHistory.size() > 0 && ((Vector)(biddingHistory).get(biddingHistory.size() - 1)).get(2).equals(ip))
            return true;
        else
            return false;
    }

    public String disconnect(String in) {
        String [] parts = null ;
        parts = in.split(token);

        for(int i = 0; i < auctionList.size(); i++)
        {
            if(((Auction)auctionList.get(i)).isIsActive())
            {
            if(((Auction)auctionList.get(i)).getSellerIP().equals(parts[1]))
               return "13" + token + "Sorry, you cannot disconnect since you are the creator of an auction";
            
            else if(isHighestBidder(((Auction)auctionList.get(i)).getBiddingHistory(),parts[1]))
                return "13" + token + "Sorry, you cannot disconnect since you are the highest bidder";
            }
        }
        return "13" + token + "You have disconnected from the auction system!Farewell!";
    }

    void changeHighestBidder(String ip) {
        if(auctionList.size()>0){
            
                for(int i = 0; i < auctionList.size(); i++)
                {
                    if(isHighestBidder(((Auction)auctionList.get(i)).getBiddingHistory(),ip))
                    {
                    if(((Auction)auctionList.get(i)).getBiddingHistory().size() > 0&& ((Vector)((Auction)auctionList.get(i)).getBiddingHistory().get(((Auction)auctionList.get(i)).getBiddingHistory().size()-1)).get(3).equals(ip))
                    {
                        ((Auction)auctionList.get(i)).setHighestBid(Double.parseDouble((String)(((Vector)((Auction)auctionList.get(i)).getBiddingHistory().get(((Auction)auctionList.get(i)).getBiddingHistory().size()-1)).get(3))));
                        ((Auction)auctionList.get(i)).getBiddingHistory().remove((Vector)((Auction)auctionList.get(i)).getBiddingHistory().get(((Auction)auctionList.get(i)).getBiddingHistory().size()-1));
                    }
//                    for(int j = 0; j<((Auction)auctionList.get(i)).getBiddingHistory().size();j++)
//                    {
//
//                    }
                }
            }
//        for(int i = 0; i < ((Auction)auctionList.get(i)).getBiddingHistory().size(); i++)
//        {
//            for(int j = 0; j < ((Vector)((Auction)auctionList.get(j)).getBiddingHistory().get(i)).size(); j++)
//            {
//                System.out.println((((Vector)((Auction)auctionList.get(j)).getBiddingHistory().get(i))).get(j));
//            }
//        }
        }
    }


}
