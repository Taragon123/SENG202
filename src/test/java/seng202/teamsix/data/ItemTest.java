package seng202.teamsix.data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void testGetMarkupPercentage() {
        ArrayList<UUID_Entity> tagList = new ArrayList<UUID_Entity>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in batter, deep-try for 5 minutes.");
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", 7.50, 10.00, recipe, tagList, UnitType.G);
        assertEquals((1.0/3.0)*100, item.getMarkupPercentage(), 0.0);
    }

    @Test
    /**
     * GIVEN Large fries with a base price of $7.50 and selling price of $10.00.
     * WHEN getProfit() called on Item large fries.
     * THEN the profit amount is computed, which is just $10.00 - $7.50 = $2.50.
     */
    public void testGetProfit() {
        ArrayList<UUID_Entity> tagList = new ArrayList<UUID_Entity>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in batter, deep-try for 5 minutes.");
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", 7.50, 10.00, recipe, tagList, UnitType.G);
        assertEquals(2.5, item.getProfit(), 0.0);
    }

    @Test
    public void testGetAllTags() {
        ArrayList<UUID_Entity> tagList = new ArrayList<UUID_Entity>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in batter, deep-try for 5 minutes.");
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", 7.50, 10.00, recipe, tagList, UnitType.G);
        assertEquals(tagList, item.getAllTags());
    }
}
