/**
 * Name: CompositeItem.java
 * Authors: George Stephenson
 * Date: 22/08/2019
 */
package seng202.teamsix.data;

import java.util.ArrayList;
import java.util.List;

public class CompositeItem extends Item{
    private List<Item> item_list;
    private Recipe recipe;

    /**
     * Constructor for blank composite item.
     */
    public CompositeItem() {
        super("NA", "NA", new Currency(), new Currency(), new Recipe("NA"), new ArrayList<ItemTag_Ref>(), UnitType.KG);
    }

    /**
     * Constructor that takes a list of items as parameter.
     * @param item_list List of UUID_Entity components
     */
    public CompositeItem(String name, String description, Currency base_price, Currency markup_price, Recipe recipe,
                         ArrayList<ItemTag_Ref> tags, UnitType qty_unit, List<Item> item_list) {
        super(name, description, base_price, markup_price, recipe, tags, qty_unit);
        this.item_list = item_list;
    }

    /**
     * Sets recipe for composite item.
     * @param recipe Recipe object
     */
    public void setCompositeRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Sets list of components.
     * @param item_list List of UUID_Entity components
     */
    public void setComponents(List<Item> item_list) {
        this.item_list = item_list;
    }

    /**
     * Getter for component items.
     * @return List of UUID_Entity components
     */
    public List<Item> getItems() {
        return item_list;
    }

    /**
     * Gets recipe for combining components.
     * @return recipe for composite item
     */
    public Recipe getCompositeRecipe() {
        return recipe;
    }

    /**
     *
     * @param ref tag to check
     * @return true if item contains tag
     */
    @Override
    public boolean hasTag(ItemTag_Ref ref) {
        // Check first if naturally contains tag
        for(ItemTag_Ref tag : getTags()) {
            if(tag.equals(ref)) {
                return true;
            }
        }

        // Check if item inherits tag from children
        if(getItems().size() > 0) {
            ItemTag tag = StorageAccess.instance().getItemTag(ref);
            Boolean parent_has_tag = tag.getIsDominant();

            for (Item_Ref child_ref : getItems()) {
                Item item = StorageAccess.instance().getItem(child_ref);
                Boolean child_has_tag = item.hasTag(ref);
                if (tag.getIsDominant()) {
                    parent_has_tag &= child_has_tag;
                } else {
                    parent_has_tag |= child_has_tag;
                }
            }

            return parent_has_tag;
        }
        
        return false;
    }
}
