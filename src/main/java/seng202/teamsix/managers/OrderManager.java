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

    private Order cart = new Order();

    /**
     * Adds the item to the cart given a reference to a MenuItem menu_item, number of items qty.
     * @param menu_item A reference to the menu item that we wish to add to the cart.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void addToCart(MenuItem menu_item, int qty) {
        Item_Ref item_ref = menu_item.getItem();
        cart.getOrderTree().addToOrder(item_ref, qty);
    }

    /**
     * Removes the item from the cart given a reference to an Item item_ref, and number of items qty.
     * @param menu_item A reference to the MenuItem that we wish to remove from the list.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void removeFromCart(MenuItem menu_item, int qty) {
        Item_Ref item_ref = menu_item.getItem();
        cart.getOrderTree().removeFromOrder(item_ref, qty);
    }

    /**
     * returns a boolean indicating whether the OrderItem object has the associated itemtag_ref.
     * @param orderComponent The OrderItem we want to check has the tag.
     * @param itemtag_ref The ItemTag_Ref object we want to see is included in the list of tags associated with the OrderItem.
     * @return
     */
    public boolean hasTagHelper(OrderItem orderComponent, ItemTag_Ref itemtag_ref) {

        boolean hasTag = false;
        Item_Ref component = orderComponent.getItem();
        if (component instanceof CompositeItem) {
            hasTag = ((CompositeItem)component).hasTag(itemtag_ref);
        } else if (component instanceof VariantItem) {
            Item itemComponent = StorageAccess.instance().getItem(component);
            hasTag = ((VariantItem)component).hasTag(itemtag_ref);
        } else if (component instanceof Item) {
            Item itemComponent = StorageAccess.instance().getItem(component);
            hasTag = itemComponent.hasTag(itemtag_ref);
        }
        return hasTag;
    }

    /**
     * A little unsure on what this does, will check with group. Should return boolean, if it the order can't be set for
     * tag, return false.
     * @param itemtag_ref
     */
    public boolean setOrderForTag(ItemTag_Ref itemtag_ref) {
        return false;
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
