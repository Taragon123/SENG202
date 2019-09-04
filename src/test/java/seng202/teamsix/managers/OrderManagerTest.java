package seng202.teamsix.managers;

import org.junit.jupiter.api.Test;
import seng202.teamsix.data.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    @Test
    void addToCart() {
        Order cart = new Order();
        OrderManager orderManager = new OrderManager();
        orderManager.setCart(cart);
        ArrayList<ItemTag_Ref> tagList = new ArrayList<ItemTag_Ref>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in batter, deep-try for 5 minutes.");
        Currency base_price = new Currency();
        base_price.setTotalCash(7.50);
        Currency markup_price = new Currency();
        markup_price.setTotalCash(10.00);
        Item itemToAdd = new Item("Large Fries", "Deep-fried pieces of potato. ", base_price, markup_price, recipe, tagList, UnitType.G);
        Item_Ref item_refToAdd = (Item_Ref)itemToAdd;
        ItemTag tag = new ItemTag("Gluten free!", false);
        ItemTag_Ref tag_ref = (ItemTag_Ref)tag;
        orderManager.addToCart(item_refToAdd, 5, tag_ref);
        for (OrderItem item: orderManager.getCart().getOrderTree().getDependants()) {
            Item item_ref_temp = (Item)item.getItem();
            assertEquals(itemToAdd.getName(), item_ref_temp.getName());
        }
    }

    @Test
    void removeFromCart() {
    }

    @Test
    void trySetOrderForTag() {
    }

    @Test
    void getCart() {
    }

    @Test
    void setCart() {
    }

    @Test
    void getCashRequired() {
    }

    @Test
    void requestPayment() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void finaliseOrder() {
    }
}