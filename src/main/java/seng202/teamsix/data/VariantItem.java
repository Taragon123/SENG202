package seng202.teamsix.data;
import java.util.ArrayList;

/**
 * Name: VariantItem.java
 * Date: August - September, 2019
 *
 * Description:
 *
 * Author:
 */

public class VariantItem {
    private ArrayList<Item_Ref> variantList = new ArrayList<Item_Ref>();
    private boolean priceFixed;

    /**
     * Gets the variant list.
     * @return ArrayList containing variants
     */
    public ArrayList<Item_Ref> getVariants() {
        return variantList;
    }

    /**
     * Adds item to variant list.
     * @param itemToAdd to be added to the list.
     */
    public void addToMenu(Item_Ref itemToAdd) {
        variantList.add(itemToAdd);
    }

    /**
     * Removes item from menu list.
     * @param itemToRemove to be removed from the list.
     */
    public void removeFromMenu(Item_Ref itemToRemove) {
        variantList.remove(itemToRemove);
    }
}
