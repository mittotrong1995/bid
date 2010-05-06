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
    private double startingPrize;

    public Item() {
        this.name = null;
        this.description = null;
        this.startingPrize = 0;
    }
    public Item(String name, String description, double startingPrize) {
        this.name = name;
        this.description = description;
        this.startingPrize = startingPrize;
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

    public double getStartingPrize() {
        return startingPrize;
    }

    public void setStartingPrize(double startingPrize) {
        this.startingPrize = startingPrize;
    }




}
