package seng202.teamsix.managers;

/**
 * Name: OrderManager.java
 * Date: September, 2019
 * Author(s): Hamesh Ravji, George Stephenson
 *
 * This class
 */

import seng202.teamsix.data.*;

import java.util.ArrayList;

public class OrderManager {

    private Order cart;

    /**
     * Adds the item to the cart given a reference to an Item item_ref, number of items qty, and a reference to an ItemTag default tag.
     * @param item_ref A reference to the item that we wish to add to the list.
     * @param qty The quantity corresponding to the number of Items.
     * @param default_tag A reference to the Item_Tag that we want to add to the list of dependencies if it isn't arleady there.
     */
    public void addToCart(Item_Ref item_ref, int qty, ItemTag_Ref default_tag) {
        cart.getOrderTree().addToOrder(item_ref, qty, default_tag);
    }

    /**
     * Removes the item from the cart given a reference to an Item item_ref, and number of items qty.
     * @param item_ref A reference to the Item that we wish to remove from the list.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void removeFromCart(Item_Ref item_ref, int qty) {
        cart.getOrderTree().removeFromOrder(item_ref, qty);
    }

    /**
     * A little unsure on what this does, will check with group.
     * @param itemtag_ref
     */
    public void trySetOrderForTag(ItemTag_Ref itemtag_ref) {
        // To be implemented.
    }

    /**
     * Returns the order.
     * @return The order.
     */
    public Order getCart() {
        return cart;
    }

    /**
     * Setter for the cart.
     * @param cart
     */
    public void setCart(Order cart) {
        this.cart = cart;
    }

    /**
     * This will be used to reset the cart when the user wishes to clear it, or when a new order is placed.
     */
    public void resetCart() {
        this.cart = new Order();
    }

    /**
     * Returns a Currency object corresponding to the cash required to pay for the order.
     */
    public Currency getCashRequired() {
        ArrayList<OrderItem> orderItems = cart.getOrderTree().getDependants();
        double cashCountTemp = 0.0;
        Currency cashRequired = new Currency();
        for (OrderItem orderItem: orderItems) {
            Item item = (Item)orderItem.getItem();
            cashCountTemp += item.getMarkupPrice().getTotalCash() * orderItem.getQuantity();
        }
        cashRequired.setTotalCash(cashCountTemp);
        return cashRequired;
    }

    /**
     * Returns a Boolean corresponding to whether payment for the order was received.
     */
    public boolean requestPayment(boolean payment_received) {
        return payment_received;
    }

    /**
     * Cancelling the order.
     */
    public void cancelOrder() {
        resetCart();
    }

    /**
     * Finalises the order by saving it so it can be viewed in future if needed, sends order to chefs, prints receipt.
     */
    public void finaliseOrder() {
        // Save the order with StorageAccess/

        // Send order to kitchen via order ticket which is to be printed.

        // Print customers receipt.
    }
}
