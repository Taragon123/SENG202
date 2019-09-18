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

    /**
     * This function returns the first variant containing the given tag.
     * @param ref
     * @return
     */
    public Item_Ref getVariantWithTag(ItemTag_Ref ref) {
        for(Item_Ref variant_ref : getVariants()) {
            Item variant = StorageAccess.instance().getItem(variant_ref);
            if(variant.hasTag(ref)) {
                return variant_ref;
            }
        }
        return null;
    }

    /**
     * This helper function is used to swap out an item corresponding to a VariantItem such that it has the given tag.
     * @param ref The tag which we want the given Item object to comply with.
     * @return A boolean indicating if the VariantItem now complies with the tag.
     */
    /*public boolean setForTag(ItemTag_Ref ref) {
        boolean parent_has_tag = false;
        Item_Ref newVariantItem = StorageAccess.instance().getItem(this);
        for (Item_Ref variant_ref: ((VariantItem)newVariantItem).getVariants()) {
            Item variant = StorageAccess.instance().getItem(variant_ref);
            if (variant.hasTag(ref)) {
                this.removeVariant(this.getVariants().get(0));
                this.addVariant(variant);
                parent_has_tag = true;
            }
        }
        return parent_has_tag;
    }*/
}
