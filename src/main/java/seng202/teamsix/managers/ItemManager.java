package seng202.teamsix.managers;

import seng202.teamsix.data.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * ItemManager manages item definitions and tags.
 */
public class ItemManager
{
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<ItemTag> tags = new ArrayList<ItemTag>();

    /**
     * Constructor
     */
    public ItemManager() {}

    /**
     * Gets a list of all stored tags.
     * @return list of tags
     */
    public ArrayList<ItemTag> getAllTags() {
        return tags;
    }

    /**
     * Gets a list of all stored items.
     * @return list of items
     */
    public ArrayList<Item> getAllItems() {
        return items;
    }

    /**
     * Gets item with given ID
     * @param item_id ID query
     * @return Item
     */
    public Item getItem(UUID item_id) {
        ArrayList<ItemTag_Ref> tags = new ArrayList<ItemTag_Ref>();
        return new Item("Coke", "Cold one", new Currency(), new Currency(), tags, UnitType.KG);
    }

    /**
     * Gets all items with specified tags
     * @param tags picks only items with these tags
     * @return list of items
     */
    public ArrayList<Item> getItemsWithTags(ArrayList<ItemTag> tags) {
        return new ArrayList<Item>();
    }

    //Adding to stored objects
    /**
     * Adds item to stored items.
     * @param new_item item to add
     */
    public void addItem(Item new_item) {
        items.add(new_item);
    }

    /**
     * Adds tag to stored tags.
     * @param new_tag tag to add
     */
    public void addTag(ItemTag new_tag) {
        tags.add(new_tag);
    }

    //Removing from added objects

    /**
     * Removes item with given UUID
     * @param item_id removes item with this ID
     */
    public void removeItem(UUID item_id) {

    }

    /**
     * Removes item tag with given UUID
     * @param tag_id removes tag with this ID
     */
    public void removeTag(UUID tag_id) {

    }
}
