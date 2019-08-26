package seng202.teamsix.data;
import java.util.ArrayList;

public class Menu extends MenuItem {
    private ArrayList<MenuItem> menuItems = new ArratList<MenuItem>();
    private String name;
    private String description;

    /**
     * Sets the value of name.
     * @param String containing the value of name.
     */
    void setName(String newName) {
        name = newName;
    }

    /**
     * Gets the value of name.
     * @return String name of menu
     */
     String getName() {
        return name;
    }

    /**
     * Sets the value of description.
     * @param String containing the value of the description.
     */
     void setDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * Gets the value of description.
     * @return String description of menu
     */
     String getDescription() {
        return description;
    }

    /**
     * Gets the menu items in the menu.
     * @return ArrayList containing menu items
     */
    ArrayList<MenuItem> getMenuItems() {
         return menuItems;
    }

    /**
     * Adds item to menu list.
     * @param MenuItem to be added to the list.
     */
    void addToMenu(MenuItem itemToAdd) {
        menuItems.add(itemToAdd);
    }

    /**
     * Removes item from menu list.
     * @param MenuItem to be removed from the list.
     */
    void removeFromMenu(MenuItem itemToRemove) {
        menuItems.remove(itemToRemove);
    }
}
