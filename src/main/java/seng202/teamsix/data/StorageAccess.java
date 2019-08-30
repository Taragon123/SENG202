/**
 * Name: StorageAccess.java
 * Authors: Connor Macdonald
 * Date: 26/08/2019
 */

package seng202.teamsix.data;


/**
 * Storage Access is the template of data storage classes as well as a singleton with a reference to current storage access used.
 * An example usage of the class can be seen below:
 * Item_Ref item_reference;
 * Item item = StorageAccess.instance().getItem(item_reference);
 */
public abstract class StorageAccess {
    private static StorageAccess internal;

    /**
     * Singleton access method
     * @return singleton instance
     */
    public static StorageAccess instance() {
        return internal;
    }

    // Get Methods
    public abstract Item getItem(Item_Ref ref);
    public abstract ItemTag getItemTag(ItemTag_Ref ref);
    public abstract Menu getMenu(Menu_Ref ref);
    public abstract Order getOrder(Order_Ref ref);
    public abstract StockInstance getStockInstance(StockInstance_Ref ref);

    // Update/Set Methods
    public abstract void updateItem(Item item);
    public abstract void updateItemTag(ItemTag tag);
    public abstract void updateMenu(Menu menu);
    public abstract void updateOrder(Order order);
    public abstract void updateStockInstance(StockInstance stock);

    // Storage methods
    public abstract void loadData();
    public abstract void saveData();
}
