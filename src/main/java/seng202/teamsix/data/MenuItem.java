package seng202.teamsix.data;

public class MenuItem {
    private UUID_Entity item;
    private String name;
    private String description;

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

