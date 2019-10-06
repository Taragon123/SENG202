/**
 * Name: OrderManager.java
 * Date: September, 2019
 * Author(s): Hamesh Ravji, George Stephenson, Taran Jennison
 *
 * This class is used to manage Orders and is instantiated once each time the app is launched. The class keeps track of
 * the current order, a local ticket number which is initially set to 1 and increments as orders are finalised, and a
 * CashRegister object to keep track of how much is currently in the register.
 */

package seng202.teamsix.managers;

import seng202.teamsix.data.*;

import java.util.Date;

public class OrderManager {

    private Order cart;
    private int localTicketCount = 1;

    /**
     * Initialise the Cash Register with $200 by default. This can be changed.
     */
    private CashRegister cashRegister = new CashRegister(200);

    /**
     * This method sets the cashRegister attribute to a the given newCashRegister attribute.
     * @param newCashRegister The CashRegister object we wish to set to the attribute cashRegister.
     * @return true if the given newCashRegister amount is greater than or equal to 0, else false.
     */
    public boolean setCashRegister(CashRegister newCashRegister) {
        if (newCashRegister.getRegisterAmount() < 0) {
            return false;
        }
        cashRegister = newCashRegister;
        return true;
    }

    /**
     * Constructor for OrderManager, whenever a OrderManager is constructed, the local order number must be set to 1.
     * Initially the OrderManager will need to start with an empty cart as well.
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
    public OrderItem addToCart(MenuItem menu_item, int qty) {
        Item_Ref item_ref = menu_item.getItem();
        return cart.getOrderTree().addToOrder(item_ref, qty, menu_item.getPrice(), 0);
    }

    /**
     * Removes the item from the cart given a reference to an Item item_ref, and number of items qty.
     * @param menu_item A reference to the MenuItem that we wish to remove from the list.
     * @param qty The quantity corresponding to the number of Items.
     */
    public void removeFromCart(MenuItem menu_item, int qty) {
        Item_Ref item_ref = StorageAccess.instance().getItem(menu_item.getItem());
        cart.getOrderTree().removeFromOrder(item_ref, qty, menu_item.getPrice());
    }

    /**
     * Returns the Order object contained in the cart attribute.
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
     * Also resets the cart and increments the localTicketCount by one and setting the new cart's localTicketNumber to 1.
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
}
