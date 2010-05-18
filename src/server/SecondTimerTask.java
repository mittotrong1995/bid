/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import adt.Auction;
import adt.Client;
import java.io.PrintWriter;
import java.util.List;
import java.util.TimerTask;
import java.util.Vector;

/**
 *
 * @author Serban
 */

public class SecondTimerTask extends TimerTask{
    private int time;
    private String out;
    private PrintWriter output;
    private Auction auction;
    private List<TCPServerThread> tcpST;
    private String token;
    private int id;
    private int step;

    public SecondTimerTask(int id, int time,PrintWriter output,Auction auction,List<TCPServerThread> tcpST,String token)
    {
        this.id = id;
        this.time = time;
        this.out = "";
        this.output = output;
        this.auction = auction;
        this.tcpST = tcpST;
        this.token = token;
        this.step = 1;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run()
    {
        String message = "";
        String item = auction.getItem().getName();
        String price = Double.toString(auction.getHighestBid());
        switch (step){
            case 1: message = "Last bid for item " + item + " was price " + price + " - going once";
                    notifyParticipants(message, auction, tcpST, "");
                    break;
            case 2: message = "Last bid for item " + item + " was price " + price + " - going twice";
                    notifyParticipants(message, auction, tcpST, "");
                    break;
            case 3: message = "Last bid for item " + item + " was price " + price + " - going 3 times";
                    notifyParticipants(message, auction, tcpST, "");
                    break;
            case 4: message = "Last bid for item " + item + " was price " + price + " - going 4 times";
                    notifyParticipants(message, auction, tcpST, "");
                    break;
            case 5: message = "Last bid for item " + item + " was price " + price + " - going last time";
                    notifyParticipants(message, auction, tcpST, "");
                    break;
            case 6: String buyer = "";
                    if(auction.getBiddingHistory().size() > 0)
                         buyer = "" + ((Vector)auction.getBiddingHistory().get(auction.getBiddingHistory().size()-1)).get(2);
                    else
                         buyer = auction.getSellerIP();
                    out = "Auction Id: " + auction.getAuctionID() + token +"Item name: " + auction.getItem().getName() + token + "Highest bid: " + auction.getHighestBid() + token +"Buyer: " + buyer;
                    auction.setIsActive(false);
                    notifyParticipants(out,auction,tcpST,"");
                    this.cancel();
                    break;
            default: message = "Second type of closing";
                    break;
        }
        step++;
    }
    private void notifyParticipants(String msgToParticipants,Auction auct,List<TCPServerThread> tcpST,String senderIP) {
        for(int i = 0; i < auct.getClients().size(); i++)
            for(int j = 0 ; j < tcpST.size(); j++)
                if((((TCPServerThread)tcpST.get(j)).getSocket().getInetAddress().toString()).replace("/","").equals(((Client)auct.getClients().get(i)).getIp()))
                    if (!(((TCPServerThread)tcpST.get(j)).getSocket().getInetAddress().toString()).replace("/","").equals(senderIP))
                        ((TCPServerThread)tcpST.get(j)).getOut().println("12"+token+msgToParticipants);
    }
    
}