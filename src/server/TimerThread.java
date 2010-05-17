/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import adt.Auction;
import java.io.PrintWriter;
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


    public TimerThread(int time,PrintWriter output,Auction auct)
    {
        this.time= time;
        this.out = "blaaaa";
        this.output = output;
        this.auct = auct;
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
                        output.println(out);
                         //this.wait();
                        //this.stop();

                    } catch (InterruptedException ex) {
                       // throw new RuntimeException("Interrupted");
                        Logger.getLogger(AuctionProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

}
