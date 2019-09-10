/**
 * Name: UUID_Entity.java
 * Authors: Anzac Morel
 * Date: 19/08/2019
 */

package seng202.teamsix.data;
import java.util.ArrayList;

public class VariantItem extends Item {
    public ArrayList<Item_Ref> variantList = new ArrayList<>();

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
    public void addVariant(Item_Ref itemToAdd) {
        variantList.add(itemToAdd);
    }

    /**
     * Removes item from menu list.
     * @param itemToRemove to be removed from the list.
     */
    public void removeVariant(Item_Ref itemToRemove) {
        variantList.remove(itemToRemove);
    }

    /**
     * Check if VariantItem has a variant with tag
     * @param ref item tag to check for
     * @return true if contains a variant with tag
     */
    @Override
    public boolean hasTag(ItemTag_Ref ref) {
        boolean parent_has_tag = false;
        for(Item_Ref variant_ref : getVariants()) {
            Item variant = StorageAccess.instance().getItem(variant_ref);
            parent_has_tag |= variant.hasTag(ref);
        }

        return parent_has_tag;
    }
}
