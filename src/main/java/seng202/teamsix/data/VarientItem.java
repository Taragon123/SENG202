package seng202.teamsix.data;
import java.util.ArrayList;

public class VarientItem {
    public ArrayList<UUID_Entity> variantList = new ArrayList<UUID_Entity>();
    public boolean priceFixed;

    /**
     * Gets the variant list.
     * @return ArrayList containing variants
     */
    public ArrayList<UUID_Entity> getVariants() {
        return variantList;
    }

    /**
     * Adds item to variant list.
     * @param itemToAdd to be added to the list.
     */
    public void addToMenu(UUID_Entity itemToAdd) {
        variantList.add(itemToAdd);
    }

    /**
     * Removes item from menu list.
     * @param itemToRemove to be removed from the list.
     */
    public void removeFromMenu(UUID_Entity itemToRemove) {
        variantList.remove(itemToRemove);
    }
}
