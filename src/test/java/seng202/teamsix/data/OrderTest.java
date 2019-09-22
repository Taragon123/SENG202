package seng202.teamsix.data;

import org.junit.Test;
import seng202.teamsix.managers.OrderManager;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testGetCashRequiredTest() {
        Order order = new Order();
        Item_Ref burger_ref = initialiseItem1(); // returns a reference to a burger item already one of the xml files.
        OrderItem root = order.getOrderTree();
        root.addToOrder(burger_ref, 25, null); // Each burger has a selling price of $19.99. By multiplying this by 25 we get $499.75.
        assertEquals(499.75, order.getCashRequired().getTotalCash(), 0.0);
    }

    @Test
    public void testPrintChefsOrder() {
        StorageAccess.initTestMode("ItemTest");

        Item_Ref combo_ref = new Item_Ref();
        combo_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        CompositeItem combo_item = (CompositeItem) StorageAccess.instance().getItem(combo_ref);
        MenuItem menu_combo = new MenuItem();
        menu_combo.setItem(combo_ref);
        OrderManager orderManager = new OrderManager();
        orderManager.addToCart(menu_combo, 4);
        System.out.println(orderManager.getCart().getChefOrder());

    }

    /**
     * This helper method imports the xml items and retrieves a reference to a burger item already created in the xml.
     * @return reference to a burger.
     */
    Item_Ref initialiseItem1() {
        StorageAccess.initTestMode("ItemTest");
        Item_Ref combo_ref = new Item_Ref();
        combo_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        return combo_ref;
    }
}