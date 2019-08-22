package seng202.teamsix.data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void testGetMarkupPercentage() {
        ArrayList<UUID_Entity> tagList = new ArrayList<UUID_Entity>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in potato, deep-try for 5 minutes.");
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", 7.50, 10.00, recipe, tagList, "G");
        assertEquals((1.0/3.0)*100, item.getMarkupPercentage(), 0.0);
    }

    @Test
    public void testGetProfit() {
        ArrayList<UUID_Entity> tagList = new ArrayList<UUID_Entity>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in potato, deep-try for 5 minutes.");
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", 7.50, 10.00, recipe, tagList, "G");
        assertEquals(2.5, item.getProfit(), 0.0);
    }

    @Test
    public void testGetAllTags() {
        ArrayList<UUID_Entity> tagList = new ArrayList<UUID_Entity>();
        Recipe recipe = new Recipe("Cut Potatoes, cover in potato, deep-try for 5 minutes.");
        Item item = new Item("Large Fries", "Deep-fried pieces of potato. ", 7.50, 10.00, recipe, tagList, "G");
        assertEquals(tagList, item.getAllTags());
    }
}
