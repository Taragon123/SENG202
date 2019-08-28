package seng202.teamsix.logical_services;

import seng202.teamsix.data.Item;

import java.util.ArrayList;

public class OrderBuilder {
    private ArrayList<Item> cart = new ArrayList<Item>();
    private ArrayList<String> itemNames = new ArrayList<String>();

    /**
     * A getter for the ArrayList containing the names of the corresponding items in the cart.
     * @return ArrayList of strings corresponding to the names of the items in the cart.
     */
    public ArrayList<String> getItemNames() {
        return itemNames;
    }

}
