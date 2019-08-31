package seng202.teamsix.logical_services;

import seng202.teamsix.data.Menu;

/**
 * Class MenuBuilder, this build a new menu for the system. Includes name & description of the menu
 * to be built.
 *
 * Author: Rchi Lugtu
 * Date Created: 31/08/19
 * Last Modified: ~
 */
public class MenuBuilder {
    Menu menu = new Menu();

    public MenuBuilder(String menuName, String menuDescription) {
        this.menu.setName(menuName);
        this.menu.setDescription(menuDescription);
    }

    public Menu returnMenu() {
        return menu;
    }



}
