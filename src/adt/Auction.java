/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adt;

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

    public Auction(int auctionID, int quantity, byte closingType, double highestBid, String sellerIP,Item item) {
        this.auctionID = auctionID;
        this.quantity = quantity;
        this.closingType = closingType;
        this.highestBid = highestBid;
        this.sellerIP = sellerIP;
        this.item = item;
    }

     public Auction() {
        this.auctionID = 0;
        this.quantity = 0;
        this.closingType = 0;
        this.highestBid = 0;
        this.sellerIP = null;
        this.item = null;
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
}
