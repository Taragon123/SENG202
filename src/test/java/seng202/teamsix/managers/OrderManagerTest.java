package seng202.teamsix.managers;

import org.junit.jupiter.api.Test;
import seng202.teamsix.data.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    @Test
    void addToCartTest() {
        OrderManager orderManager = new OrderManager();
        orderManager.resetCart();
        Item_Ref item_refToAdd = initialiseItem1();
        ItemTag tag = new ItemTag("Gluten free!", false);
        ItemTag_Ref tag_ref = (ItemTag_Ref)tag;
        orderManager.addToCart(item_refToAdd, 5);
        OrderItem orderItemRetrievedFromOrder = orderManager.getCart().getOrderTree().getDependants().get(0);

        assertEquals(((Item)item_refToAdd).getName(), ((Item)orderItemRetrievedFromOrder.getItem()).getName());
        assertEquals(5, orderItemRetrievedFromOrder.getQuantity());

        orderManager.addToCart(item_refToAdd, 10);
        orderItemRetrievedFromOrder = orderManager.getCart().getOrderTree().getDependants().get(0);

        assertEquals(15, orderItemRetrievedFromOrder.getQuantity());
    }

    @Test
    void resetCartTest() {
        OrderManager orderManager = new OrderManager();
        orderManager.resetCart();

        assertEquals(0, orderManager.getCart().getOrderTree().getDependants().size());
    }

    @Test
    void testGetCashRequiredTest() {
        OrderManager orderManager = new OrderManager();
        orderManager.resetCart();
        Item_Ref item_ref1 = initialiseItem1();
        orderManager.addToCart(item_ref1, 25);

        assertEquals(250.0, orderManager.getCashRequired().getTotalCash());
    }

    Item_Ref initialiseItem1() {
        ArrayList<ItemTag_Ref> tagList = new ArrayList<ItemTag_Ref>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in batter, deep-try for 5 minutes.");
        Currency base_price = new Currency();
        base_price.setTotalCash(7.50);
        Currency markup_price = new Currency();
        markup_price.setTotalCash(10.00);
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", base_price, markup_price, recipe, tagList, UnitType.G);
        return item;
    }

}