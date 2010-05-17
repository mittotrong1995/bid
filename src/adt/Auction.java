/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adt;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author sanja
 */
public class Auction {
    private int auctionID;
    private int quantity;
    private byte closingType;
    private double highestBid;
    private String sellerIP;
    private Item item;
    private List clients;
    private List biddingHistory;
    private int timer;

    public Auction(int auctionID, int quantity, byte closingType, double highestBid, String sellerIP,Item item,int timer) {
        this.auctionID = auctionID;
        this.quantity = quantity;
        this.closingType = closingType;
        this.highestBid = highestBid;
        this.sellerIP = sellerIP;
        this.item = item;
        this.timer = timer;
        clients = new LinkedList();
        biddingHistory = new LinkedList();
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

     public Auction() {
        this.auctionID = 0;
        this.quantity = 0;
        this.closingType = 0;
        this.highestBid = 0;
        this.sellerIP = null;
        this.item = null;
        clients = new LinkedList();
        biddingHistory = new LinkedList();
    }

    public List getBiddingHistory() {
        return biddingHistory;
    }

    public void setBiddingHistory(List biddingHistory) {
        this.biddingHistory = biddingHistory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public byte getClosingType() {
        return closingType;
    }

    public void setClosingType(byte closingType) {
        this.closingType = closingType;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellerIP() {
        return sellerIP;
    }

    public void setSellerIP(String sellerIP) {
        this.sellerIP = sellerIP;
    }

    public List getClients() {
        return clients;
    }

    public void setClients(List clients) {
        this.clients = clients;
    }

}
