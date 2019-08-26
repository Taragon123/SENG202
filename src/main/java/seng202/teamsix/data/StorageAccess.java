package seng202.teamsix.data;


/**
 * Storage Access is the template of data storage classes as well as a singleton with a reference to current storage access used.
 * An example usage of the class can be seen below:
 * UUID_Entity item_reference;
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
    public abstract Item getItem(UUID_Entity ref);
    public abstract ItemTag getItemTag(UUID_Entity ref);
    public abstract Menu getMenu(UUID_Entity ref);
    //public abstract Order getOrder(UUID_Entity ref);
    //public abstract StockInstance getStockInstance(UUID_Entity ref);

    // Update/Set Methods
    public abstract void updateItem(Item item);
    public abstract void updateItemTag(ItemTag tag);
    public abstract void updateMenu(Menu menu);
    //public abstract void updateOrder(Order order);
    //public abstract void updateStockInstance(Item item);
}
