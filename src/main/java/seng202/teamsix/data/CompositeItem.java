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
     * @return String explaining how to combine components
     */
    public String getCompositeRecipe() {
        return recipe.getMethod();
    }
}
