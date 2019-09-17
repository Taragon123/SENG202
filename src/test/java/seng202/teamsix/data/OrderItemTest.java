package seng202.teamsix.data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderItemTest {

    @Test
    public void setItemTest() {
        OrderItem order = new OrderItem();
        Item_Ref random_item = new Item_Ref();
        order.setItem(random_item);
        assertTrue(random_item.equals(order.getItem()));
    }

    @Test
    public void addDependantTest() {
        OrderItem order = new OrderItem();
        OrderItem test = new OrderItem();
        assertEquals(0, order.getDependants().size());
        order.addDependant(test);
        assertEquals(1, order.getDependants().size());
    }

    @Test
    public void setQuantityTest() {
        OrderItem order = new OrderItem();

        order.setQuantity(50);
        assertEquals(50, order.getQuantity());

        order.setQuantity(0);
        assertEquals(0, order.getQuantity());

        order.setQuantity(-100);
        assertEquals(0, order.getQuantity());
    }

    /**
     * This tests the addToOrder method. This method is necessary in order to make use of the hierarchical structure where
     * the top of the order is an OrderItem object without an associated item. This OrderItem contains all the OrderItems
     * part of the order in the list of dependencies. This test adds items to the list of dependencies, both items already
     * part of the order and not already in the order.
     */
    @Test
    public void addToOrderTest() {
        Item item1 = initialiseItem1();
        Item item2 = initialiseItem2();
        OrderItem bag = new OrderItem();
        ItemTag_Ref itemTagRefToAdd1 = new ItemTag("Gluten-Free", true);
        ItemTag_Ref itemTagRefToAdd2 = new ItemTag("Dairy-Free", false);
        bag.addToOrder(item1, 5, itemTagRefToAdd1);
        bag.addToOrder(item1, 3, itemTagRefToAdd1);
        bag.addToOrder(item2, 2, itemTagRefToAdd2);
        assertEquals(8, bag.getDependants().get(0).getQuantity());
        assertEquals(2, bag.getDependants().get(1).getQuantity());
    }

    /**
     * Tests the removeFromOrder method. This method is necessary as it is used in the OrderManager to remove items from
     * the cart.
     */
    @Test
    public void removeFromOrderTest() {
        Item item1 = initialiseItem1();
        Item item2 = initialiseItem2();
        OrderItem bag = new OrderItem();
        ItemTag_Ref itemTagRefToAdd1 = new ItemTag("Gluten-Free", true);
        bag.addToOrder(item1, 5, itemTagRefToAdd1);
        assertTrue(bag.removeFromOrder(item1, 4));
        assertEquals(1, bag.getDependants().get(0).getQuantity());
        assertFalse(bag.removeFromOrder(item2, 4));
    }

    /**
     * This returns an Item object that we can use for testing.
     * @return Item object used for testing.
     */
    public Item initialiseItem1() {
        ArrayList<ItemTag_Ref> tagList = new ArrayList<ItemTag_Ref>();
        ItemTag_Ref itemTagRefToAdd1 = new ItemTag("Gluten-Free", true);
        ItemTag_Ref itemTagRefToAdd2 = new ItemTag("Dairy-Free", false);
        tagList.add(itemTagRefToAdd1);
        tagList.add(itemTagRefToAdd2);
        Recipe recipe = new Recipe("Cut Potatoes, cover in batter, deep-try for 5 minutes.");
        Currency base_price = new Currency();
        base_price.setTotalCash(7.50);
        Currency markup_price = new Currency();
        markup_price.setTotalCash(10.00);
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", base_price, markup_price, recipe, tagList, UnitType.G);
        return item;
    }

    /**
     * This returns an Item object that we can use for testing.
     */
    public Item initialiseItem2() {
        CompositeItem item = new CompositeItem();
        ArrayList<ItemTag_Ref> tags = new ArrayList<ItemTag_Ref>();
        ArrayList<Item> subItems = new ArrayList<Item>();
        subItems.add(new Item("Coke", "Cold one", new Currency(), new Currency(), tags, UnitType.G));
        item.setComponents(subItems);
        return item;
    }
}