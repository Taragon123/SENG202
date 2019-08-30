/**
 * Name: OrderItem.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import java.util.ArrayList;

/**
 * Defines an ordered item for an order, an order item can contain many dependent order items that are required for the item
 */
public class OrderItem {
    private UUID_Entity item = null;
    private ArrayList<OrderItem> dependants = new ArrayList<OrderItem>();
    private int quantity = 0;

    public UUID_Entity getItem() {
        return this.item;
    }

    public void setItem(UUID_Entity item) {
        // TODO(Connor): Add check that item exists
        this.item = item;
    }

    public ArrayList<OrderItem> getDependants() {
        return this.dependants;
    }

    public void addDependant(OrderItem order_item) {
        this.dependants.add(order_item);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity,0);
    }
}
