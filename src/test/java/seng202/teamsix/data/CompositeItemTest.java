/**
 * Name: CompositeItemTest.java
 * Authors: George Stephenson
 * Date: 22/08/2019
 */

package seng202.teamsix.data;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class CompositeItemTest {
    @Test
    public void testSetGetItems() {
        CompositeItem item = new CompositeItem();
        ArrayList<ItemTag_Ref> tags = new ArrayList<ItemTag_Ref>();
        ArrayList<Item> subItems = new ArrayList<Item>();
        subItems.add(new Item("Coke", "Cold one", new Currency(), new Currency(), tags, UnitType.KG));
        item.setComponents(subItems);
        assertEquals(item.getItems().size(), 1);
    }

    @Test
    public void testSetGetRecipe() {
        CompositeItem item = new CompositeItem();
        item.setCompositeRecipe(new Recipe("Do nothing, there's no components!"));
        assertEquals(item.getCompositeRecipe().getMethod(), "Do nothing, there's no components!");
    }

    @Test
    public void test() {

    }
}