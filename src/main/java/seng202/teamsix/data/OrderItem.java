/**
 * Name: OrderItem.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 * Name: OrderItem.java
 * Date: August - September, 2019
 *
 * Defines an ordered item for an order, an order item can contain many dependent order items that are required for the item
 *
 * Authors: Connor Macdonald, Hamesh Ravji
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

    public void setDependants(ArrayList<OrderItem> newDependants) {
        this.dependants = newDependants;
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

    /**
     * Adds an Item to the Order, given an Item_Ref item_ref, int qty, and ItemTag_Ref default tag.
     * @param item_ref Refers to the Item of which we want to add to the order.
     * @param qty The number of items we want to add too the order.
     * @param default_tag The tag corresponding to the Item, indicates if the item is gluten-free or dairy-free etc.
     */
    public void addToOrder(Item_Ref item_ref, int qty, ItemTag_Ref default_tag) {
        boolean is_added = false;
        for (OrderItem orderItem: dependants) {
            if (orderItem.getItem() == item_ref) {
                orderItem.quantity += qty;
                is_added = true;
            }
        }
        if (! is_added) {
            OrderItem orderItemToAdd = new OrderItem();
            orderItemToAdd.setItem(item_ref);
            orderItemToAdd.setQuantity(qty);
            this.dependants.add(orderItemToAdd);
        }
    }

    /**
     * Removes an Item from the Order, given an Item_Ref item_ref, and an int qty.
     * @param item_ref Refers to the Item which we want to remove from the Order.
     * @param qty The number of items we want to remove from the order.
     * @return True if items are removed (even if qty > already in cart), false if they didn't exist in the first place.
     */
    public boolean removeFromOrder(Item_Ref item_ref, int qty) {
        boolean is_removed = false;
        for (OrderItem orderItem: dependants) {
            if (orderItem.getItem() == item_ref) {
                if (orderItem.quantity > qty) {
                    orderItem.quantity -= qty;
                    is_removed = true;
                } else {
                    dependants.remove(orderItem);
                    is_removed = true;
                }
            }
        }
        return is_removed;
    }
}
