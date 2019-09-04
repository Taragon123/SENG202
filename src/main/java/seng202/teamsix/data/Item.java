package seng202.teamsix.data;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;

/** Name: Item.java
 *
 * Class Item, essentially the item that is going to be listed in the menu. Can consist of multiple Compost or variant items.
 * Consists of a name, description, price that the business purchased the stock at, selling price (markup_price), optional
 * recipe, an Arraylist consisting of tags to indicate whether the Item is gluten-free etc, and a unit type.
 *
 * Date: August - September, 2019
 * Author: Hamesh Ravji
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Item extends Item_Ref {
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private Currency base_price;
    @XmlElement
    private Currency markup_price;
    @XmlElement
    private Recipe recipe;
    @XmlElement
    private ArrayList<ItemTag_Ref> tags;
    @XmlElement
    private UnitType qty_unit;


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public Currency getBasePrice() {
        return base_price;
    }

    public Currency getMarkupPrice() {
        return markup_price;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public ArrayList<ItemTag_Ref> getTags() {
        return tags;
    }

    public UnitType getQtyUnit() {
        return qty_unit;
    }

    Item() {}

    /**
     * Constructor class which takes all parameters including recipe.
     * @param name The name we wish to set the item to.
     * @param description A small description we wish to set for the item.
     * @param base_price The cost price of the Item.
     * @param markup_price The selling price of the item to be listed on the menu.
     * @param recipe The recipe to assist the chefs with making the item.
     * @param tags A list of tags to indicate whether the item is gluten-free etc.
     * @param qty_unit The unit in relation to the quantity of the Item, such as sauce requires units L or ML.
     */
    public Item(String name, String description, Currency base_price, Currency markup_price, Recipe recipe,
                ArrayList<ItemTag_Ref> tags, UnitType qty_unit) {
        this.name = name;
        this.description = description;
        this.base_price = base_price;
        this.markup_price = markup_price;
        this.recipe = recipe;
        this.tags = tags;
        this.qty_unit = qty_unit;
    }

    /**
     * Constructor class for those items which do not have a recipe.
     * @param name The name we wish to set the item to.
     * @param description A small description we wish to set for the item.
     * @param base_price The cost price of the Item.
     * @param markup_price The selling price of the item to be listed on the menu.
     * @param tags A list of tags to indicate whether the item is gluten-free etc.
     * @param qty_unit The unit in relation to the quantity of the Item, such as sauce requires L or ML.
     */
    public Item(String name, String description, Currency base_price, Currency markup_price,
                ArrayList<ItemTag_Ref> tags, UnitType qty_unit) {
        this.name = name;
        this.description = description;
        this.base_price = base_price;
        this.markup_price = markup_price;
        this.tags = tags;
        this.qty_unit = qty_unit;
    }

    /**
     * Calculate the markup percentage on the item, which is essentially the percentage of the cost price added to
     * get the selling price.
     * @return The markup percentage on the Item.
     */
    public double getMarkupPercentage() {
        return (((markup_price.getTotalCash() - base_price.getTotalCash()) / base_price.getTotalCash())*100);
    }

    /**
     * Calculates the profit that can be made from selling the item at selling price.
     * @return The profit that can be made by selling the item.
     */
    public double getProfit() {
        return markup_price.getTotalCash() - base_price.getTotalCash();
    }
}
