/**
 * Name: Order.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import java.time.LocalDateTime;

/**
 * Orders are the finalised version of an order. An order contains an order tree of OrderItems that express items ordered.
 */
public class Order extends UUID_Entity{
    private OrderItem order_tree = new OrderItem();
    private LocalDateTime timestamp;

    public OrderItem getOrderTree() {
        return order_tree;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
