/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adt;

/**
 *
 * @author sanja
 */
public class Item {
    private String name;
    private String description;
    private double startingPrice;

    public Item() {
        this.name = null;
        this.description = null;
        this.startingPrice = 0;
    }
    public Item(String name, String description, double startingPrize) {
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

}
