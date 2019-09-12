/**
 * Name: UUID_Entity.java
 * Authors: Anzac Morel
 * Date: 19/08/2019
 */

package seng202.teamsix.managers;

import seng202.teamsix.data.Order;

import java.util.ArrayList;

public class HistoricalManager {
    private ArrayList<Order> order_list;

    /**
     * Constructor for the HistoricalManager class, used if there is no current list of orders
     */
    public HistoricalManager() {
        order_list = new ArrayList<>();
    }

    /**
     * Adds the given Order to the ArrayList of orders
     * @param order Order to be added to the list of orders
     */
    public void addOrder(Order order) {
        if (!order_list.contains(order)) {
            order_list.add(order);
        } else {
            System.err.println("Can't add same order to the list of orders!");
        }
    }

    /**
     * Prints the timestamp of each order in the order list
     */
    public void printOrderDates() {
        for (int index = 0; index < order_list.size(); index++) {
            System.out.println(order_list.get(index).getTimestamp());
        }
    }

    /**
     * Prints the items that were contained in each order in the order list
     */
    public void printOrderContents() {
        for (int index = 0; index < order_list.size(); index++) {
            System.out.println(order_list.get(index).getOrderTree());
        }
    }
}
