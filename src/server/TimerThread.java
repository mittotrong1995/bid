/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import adt.Auction;
import adt.Client;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanja
 */
public class TimerThread extends Thread{
    private int time;
    private String out;
    private PrintWriter output;
    private Auction auct;
    private List<TCPServerThread> tcpST;
    private String token;


    public TimerThread(int time,PrintWriter output,Auction auct,List<TCPServerThread> tcpST,String token)
    {
        this.time= time;
        this.out = "blaaaa";
        this.output = output;
        this.auct = auct;
        this.tcpST = tcpST;
        this.token = token;
    }

    public void activate(int time)
    {
        this.time = time;
        out = "";
        //this.run();
    }

    public void run()
    {
         try {
                        TimerThread.sleep(time * 1000);
                        if(auct.getBiddingHistory().size() > 0)
                            out = "Auction Id: " + auct.getAuctionID() + ", Item name: " + auct.getItem().getName() + ", Highest bid: " + auct.getHighestBid() + ", Buyer: " + ((Vector)auct.getBiddingHistory().get(auct.getBiddingHistory().size()-1)).get(2);
                        else
                            out = "Auction Id: " + auct.getAuctionID() + ", Item name: " + auct.getItem().getName() + ", Highest bid: " + auct.getHighestBid() + ", Buyer: " + auct.getSellerIP();
                        auct.setIsActive(false);
                       notifyParticipants(out,auct,tcpST,auct.getSellerIP());
                        output.println(out);
                         //this.wait();
                        //this.stop();

                    } catch (InterruptedException ex) {
                       // throw new RuntimeException("Interrupted");
                        Logger.getLogger(AuctionProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

}
