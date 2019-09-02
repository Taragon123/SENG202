package seng202.teamsix.managers;

/**
 * Name: OrderManager.java
 * Date: 31/08/2019
 * Author: Hamesh Ravji
 *
 * This class manages all things to do with an Order, such as building it, as well as updating relative data afterwards.
 */

import seng202.teamsix.data.Order;

public class OrderManager {
    private Order order;

    public OrderManager() {
        order = new Order();
    }

    public void buildOrder() {
        OrderBuilder orderBuilder = new OrderBuilder();
        // Methods which should be available from this class are:
        // this.order = orderBuilder.getOrder();
        // orderBuilder.addItem();
        // orderBuilder.removeItem();
    }

    public void finaliseBuilder() {
        FinaliseBuilder finaliseBuilder = new FinaliseBuilder(order);
        // Methods which should be available from this class are:
        // finaliseBuilder.printReceipt();
        // finaliseBuilder.parseXML();

    }

}
