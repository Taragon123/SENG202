package seng202.teamsix.data;
import java.util.ArrayList;

public class Menu extends MenuItem {
    private ArrayList<MenuItem> menuItems = new ArratList<MenuItem>();
    private String name;
    private String description;

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

    ArrayList<MenuItem> getMenuItems() {
         return menuItems;
    }

    void addToMenu(MenuItem itemToAdd) {
        menuItems.add(itemToAdd);
    }

    void removeFromMenu(MenuItem itemToRemove) {
        menuItems.remove(itemToRemove);
    }
}
