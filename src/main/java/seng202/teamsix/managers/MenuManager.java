package seng202.teamsix.managers;

import seng202.teamsix.data.Menu;
import seng202.teamsix.data.MenuItem;
import seng202.teamsix.logical_services.MenuBuilder;
import seng202.teamsix.logical_services.MenuLoaderSaver;
import seng202.teamsix.logical_services.MenuModifier;

/**
 * Class MenuManager, the manager class for the Menu, contains all the functionalities of the menu
 * in the system. What the user can do with the menu.
 *
 * Author: Rchi Lugtu
 * Date Created: 31/08/19
 * Last Modified: ~
 */
public class MenuManager {
    private Menu menu;

    public void buildMenu(String menuName, String menuDescription) {
        MenuBuilder menuBuilder = new MenuBuilder(menuName, menuDescription);
        menu = menuBuilder.returnMenu();
    }

    public void addToMenu(MenuItem menuItem) {
        MenuModifier menuModifier = new MenuModifier(this.menu);
        menuModifier.addMenuItem(menuItem);
    }

    public void removeFromMenu(String toBeRemoved) {
        MenuModifier menuModifier = new MenuModifier(this.menu);
        menuModifier.removeMenuItem();
    }

    public void editMenu() {
        MenuModifier menuModifier = new MenuModifier(this.menu);
        menuModifier.modifyMenu();
    }

    public void saveMenu() {
        MenuLoaderSaver menuLoaderSaver = new MenuLoaderSaver(this.menu);
    }

    public void loadMenu() {
        MenuLoaderSaver menuLoaderSaver = new MenuLoaderSaver(this.menu);
    }
}
