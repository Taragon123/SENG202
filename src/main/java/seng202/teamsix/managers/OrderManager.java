package seng202.teamsix.managers;

/**
 * Name: OrderManager.java
 * Date: September, 2019
 * Author(s): Hamesh Ravji, George Stephenson
 *
 * This class
 */

import seng202.teamsix.data.*;

import java.util.Date;
import java.util.List;

public class OrderManager {

    private Order cart;

    private int localTicketCount = 1;
    // initialize chas register with $200
    private CashRegister cashRegister = new CashRegister(200);

    /**
     * Whenever a OrderManager is constructed, the local order number must be set to 1.
     */
    public OrderManager() {
        cart = new Order();
        cart.localTicketNumber = localTicketCount;
    }

    /**
     * Adds the item to the cart given a reference to a MenuItem menu_item, number of items qty.
     * @param menu_item A reference to the menu item that we wish to add to the cart.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void addToCart(MenuItem menu_item, int qty) {
        Item_Ref item_ref = menu_item.getItem();
        OrderItem new_root = cart.getOrderTree();
        new_root.addToOrder(item_ref, qty, menu_item.getPrice());
        cart.setOrderTree(new_root);


    }

    /**
     * Removes the item from the cart given a reference to an Item item_ref, and number of items qty.
     * @param menu_item A reference to the MenuItem that we wish to remove from the list.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void removeFromCart(MenuItem menu_item, int qty) {
        Item_Ref item_ref = StorageAccess.instance().getItem(menu_item.getItem());
        OrderItem new_root = cart.getOrderTree();
        new_root.removeFromOrder(item_ref, qty, menu_item.getPrice());
        cart.setOrderTree(new_root);
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
     * This will be used to reset the cart when the user wishes to clear it, or when a new order is placed.
     */
    public void resetCart() {
        this.cart = new Order();
    }


    /**
     * Returns a Boolean corresponding to whether payment for the order was received.
     */
    public boolean checkPaymentReceived(boolean payment_received) {
        return payment_received;
    }

    /**
     * Finalises the order by saving it so it can be viewed in future if needed, sends order to chefs, prints receipt.
     */
    public void finaliseOrder() {
        // Save the order with StorageAccess/
        cart.setTimestamp(new Date());
        System.out.println(cart.getTimestamp());
        StorageAccess.instance().updateOrder(cart);

        // Send order to kitchen via order ticket which is to be printed.
        printChefOrder();

        //Update the cash register
        cashRegister.addRegisterAmount(getCart().getTotalCost());
        System.out.println(String.format("Cash register: %.2f", cashRegister.getRegisterAmount()));

        // Print customers receipt.
        printReceipt();
        localTicketCount += 1;
        resetCart();
        cart.localTicketNumber = localTicketCount;
    }

    public Currency getCashRequired() {
        return cart.getCashRequired();
    }

    public List<OrderItem> getCartOrderItems() {
        return cart.getOrderTree().getDependants();
    }


    /**
     * This method prints the order, just the item names and quantity. Currently prints to the command line.
     */
    private void printChefOrder() {
        // need to find the depth of the order so that we can use the method getOrderTreeRepr() of the class OrderItem.

    }

    /**
     * This method prints the Order in a receipt format, currently to the command line.
     */
    private void printReceipt() {
        // similar to the printChefOrder, more details.

    }
}
