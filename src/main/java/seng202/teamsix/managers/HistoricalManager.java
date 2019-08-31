package seng202.teamsix.managers;

import seng202.teamsix.data.Order;

import java.util.ArrayList;

public class HistoricalManager {
    private ArrayList<Order> order_list;

    public void addOrder(Order order) {
        if (!order_list.contains(order)) {
            order_list.add(order);
        } else {
            System.err.println("Can't add same order to the list of orders!");
        }
    }

    public void removeOrder(Order order) {
        if (order_list.contains(order)) {
            order_list.remove(order);
        } else {
            System.err.println("The specified order is not in the list of orders!");
        }
    }
}
