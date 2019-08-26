package seng202.teamsix.data;

public class MenuItem {
    private UUID_Entity item;
    private String name;
    private String description;
    private double price;

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

//    public UUID_Entity getItem() {
//        return item;
//    }

}

