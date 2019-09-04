package seng202.teamsix.managers;

import seng202.teamsix.data.ItemTag_Ref;
import seng202.teamsix.data.Item_Ref;
import seng202.teamsix.data.Order;
import seng202.teamsix.data.OrderItem;

public class OrderManager {

    private Order cart;

    /**
     * Adds the item to the cart given a reference to an Item item_ref, number of items qty, and a reference to an ItemTag default tag.
     * @param item_ref A reference to the item that we wish to add to the list.
     * @param qty The quantity corresponding to the number of Items.
     * @param default_tag A reference to the Item_Tag that we want to add to the list of dependencies if it isn't arleady there.
     */
    public void addToCart(Item_Ref item_ref, int qty, ItemTag_Ref default_tag) {
        // Waiting for the addToOrder method to be completed in the OrderItem class.
        OrderItem orderItemToAdd = new OrderItem();
        orderItemToAdd.setItem(item_ref);
        orderItemToAdd.setQuantity(qty);
        cart.getOrderTree().addDependant(orderItemToAdd);
    }

    /**
     * Removes the item from the cart given a reference to an Item item_ref, and number of items qty.
     * @param item_ref A reference to the Item that we wish to remove from the list.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void removeFromCart(Item_Ref item_ref, int qty) {
        // Waiting for implementation in the OrderItem class, or some more information on how to traverse through the
        // OrderItem structure.
    }

    /**
     * A little unsure on what this does, will check with group.
     * @param itemtag_ref
     */
    public void trySetOrderForTag(ItemTag_Ref itemtag_ref) {
        // To be implemented.
    }

    /**a
     * Returns the order.
     * @return The order.
     */
    public Order getCart() {
        return cart;
    }

    /**
     *
     * @param cart
     */
    public void setCart(Order cart) {
        this.cart = cart;
    }

    /**
     * Returns a Currency object corresponding to the cash required to pay for the order.
     */
    public void getCashRequired() {
        // To be implemented.
    }

    /**
     * Returns a Boolean corresponding to whether payment for the order was received.
     */
    public void requestPayment() {
        // To be implemented.
    }

    /**
     * Cancelling the order.
     */
    public void cancelOrder() {
        // Yet to be implemented.
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
