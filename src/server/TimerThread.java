/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanja
 */
public class TimerThread extends Thread{
    private int time;
    private String out;


    public TimerThread(int time)
    {
        this.time= time;
        this.out = "blaaaa";
    }

    public void activate(int time)
    {
        this.time = time;
        out = "blaaaa";
        //this.run();
    }

    public void run()
    {
         try {
                        TimerThread.sleep(time * 1000);
                        this.interrupt();
                        System.out.println(TimerThread.interrupted() + "dze");
                        out = " cekaaaaaaaav";
                         //this.wait();
                        //this.stop();

                    } catch (InterruptedException ex) {
                       // throw new RuntimeException("Interrupted");
                        Logger.getLogger(AuctionProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

}
