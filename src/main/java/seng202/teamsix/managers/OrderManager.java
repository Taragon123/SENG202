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
        new_root.addToOrder(item_ref, qty, menu_item.getPrice(), 0);
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
     * Finalises the order by saving it so it can be viewed in future if needed, sends order to chefs, prints receipt.
     */
    public void finaliseOrder() {
        // Save the order with StorageAccess/
        cart.setTimestamp(new Date());
        StorageAccess.instance().updateOrder(cart);

        // Send order to kitchen via order ticket which is to be printed. Also prints a new line.
        System.out.println(cart.getChefOrder()+"\n");

        //Update the cash register
        cashRegister.addRegisterAmount(cart.getTotalCost());

        // Print customers receipt.
        System.out.println(cart.getReceipt());
        localTicketCount += 1;
        resetCart();
        cart.localTicketNumber = localTicketCount;
    }

    /*public Currency getCashRequired() {
        return cart.getCashRequired();
    }*/
}
