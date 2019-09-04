/**
 * Name: OrderItem.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 * Defines an ordered item for an order, an order item can contain many dependent order items that are required for the item
 */


@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class OrderItem {
    @XmlElement
    private Item_Ref item = null;
    @XmlElement
    private ArrayList<OrderItem> dependants = new ArrayList<OrderItem>();
    @XmlElement
    private int quantity = 0;

    public Item_Ref getItem() {
        return this.item;
    }

    public void setItem(Item_Ref item) {
        // TODO(Connor): Add check that item exists
        this.item = item;
    }

    public ArrayList<OrderItem> getDependants() {
        return this.dependants;
    }

    public void addDependant(OrderItem order_item) {
        this.dependants.add(order_item);
    }

    public void removeDependant(OrderItem order_item) {
        this.dependants.remove(order_item);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity,0);
    }

    public void addToOrder(Item_Ref item_ref) {

    }
}
