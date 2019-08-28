package seng202.teamsix.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
class MenuItemTest {

    @Test
    void testSetAndGetPrice() {
        MenuItem menuItem = new MenuItem();
        menuItem.setPrice(123.456);
        assertTrue("100% passed", menuItem.getPrice() == 123.456);
        System.out.println("100% passed");
    }

    @Test
    void testSetAndGetName() {
        MenuItem menuItem = new MenuItem();
        menuItem.setName("Cheeseburger");
        assertTrue("100% passed", menuItem.getName() == "Cheeseburger");
        System.out.println("100% passed");
    }

    @Test
    void testSetAndGetDescription() {
        MenuItem menuItem = new MenuItem();
        menuItem.setDescription("Yummy");
        assertTrue("100% passed", menuItem.getDescription() == "Yummy");
        System.out.println("100% passed");
    }

    @Test
    public void testSetAndGetItem() {
        UUID_Entity itemReference = new UUID_Entity();
        MenuItem menuItem = new MenuItem();
        menuItem.setItem(itemReference);
        assertTrue("Passed", menuItem.getItem() == itemReference);
        System.out.println("100% passed");
    }
}