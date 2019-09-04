package seng202.teamsix.managers;

import seng202.teamsix.data.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * ItemManager manages item definitions and tags.
 */
public class ItemManager
{
    /*
    TODO UNFINISHED, SOME METHODS INCOMPLETE
     */

    /**
     * Constructor
     */
    public ItemManager() {}

    /**
     * Gets item with given ID
     * @param item_id ID query
     * @return Item
     */
    public Item getItem(Item_Ref item_id) {
        return StorageAccess.instance().getItem(item_id);
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
    public void addUpdateItem(Item new_item) {
        StorageAccess.instance().updateItem(new_item);
    }

    /**
     * Adds tag to stored tags.
     * @param new_tag tag to add
     */
    public void addTag(ItemTag new_tag) {
        StorageAccess.instance().updateItemTag(new_tag);
    }

    //Removing from added objects

    /**
     * Removes item with given UUID
     * @param item_id removes item with this ID
     */
    public void removeItem(UUID item_id) {
        // TODO needs methods implementing in StorageAccess
    }

    /**
     * Removes item tag with given UUID
     * @param tag_id removes tag with this ID
     */
    public void removeTag(UUID tag_id) {
        // TODO needs methods implementing in StorageAccess
    }

}
