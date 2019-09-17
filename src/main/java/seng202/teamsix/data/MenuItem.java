package seng202.teamsix.data;

import javax.xml.bind.annotation.*;

/**
 * Class MenuItem, items to be added to the menu. Includes name, description, and price for each menu item crated
 * that is to be displayed in the menu.
 * Author: Rchi Lugtu
 * Date Created: 27/08/19
 * Last Updated: 31/08/19
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class MenuItem extends Menu_Ref{
    @XmlElement
    private Item_Ref item;
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private double price;
    @XmlElement
    private int colour;

    /**
     * Sets the price of item
     * @param price the price of the item to be displaued to the menu
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * Gets the price of the item in the menu
     * @return the price of the item in the menu
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the colour associated with buttons of this menu
     * @param colour the colour that is to be associated with buttons of this menu
     */
    public void setColour(int colour) {
        this.colour = colour;
    }

    /**
     * Gets the colour associated with buttons of this menu
     * @return the colour that is associated with buttons of this menu
     */
    public int getColour() {
        return colour;
    }

    /**
     * Gets the name of the menu item.
     * @return String name of the item to be added to the menu.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the menu item.
     * @param name String name of the item to be added to the menu.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the menu item.
     * @return String description of the item to be added to the menu.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     * @param description String description of the item to be added to the menu.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the item reference
     * @param item item reference to be added to the menu
     */
    public void setItem(Item_Ref item) {
        this.item = item;
    }

    /**
     * Gets the item reference
     * @return Item_Ref item, which is the reference of the item
     */
    public Item_Ref getItem() {
        return item;
    }

}

