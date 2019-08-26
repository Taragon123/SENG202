package seng202.teamsix.data;

public class Menu extends MenuItem {
    private ArrayList<MenuItem> menuItems = new ArratList<MenuItem>();
    private String name;
    private String description;

    private void setName(String newName) {
        name = newName;
    }

    private String getName() {
        return name;
    }

    private void setDescription(String newDescription) {
        description = newDescription;
    }

    private String getDescription() {
        return description;
    }

    void addToMenu(MenuItem itemToAdd) {
        menuItems.add(itemToAdd);
    }

    void removeFromMenu(MenuItem itemToRemove) {
        menuItems.remove(itemToRemove);
    }
}
