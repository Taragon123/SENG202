package seng202.teamsix.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class MenuItemTest {

    @Test
    public void testSetAndGetPrice() {
        MenuItem menuItem = new MenuItem();
        menuItem.setPrice(123.456);
        assertTrue("100% passed", menuItem.getPrice() == 123.456);
        System.out.println("100% passed");
    }

    @Test
    public void testSetAndGetName() {
        MenuItem menuItem = new MenuItem();
        menuItem.setName("Cheeseburger");
        assertTrue("100% passed", menuItem.getName() == "Cheeseburger");
        System.out.println("100% passed");
    }

    @Test
    public void testSetAndGetDescription() {
        MenuItem menuItem = new MenuItem();
        menuItem.setDescription("Yummy");
        assertTrue("100% passed", menuItem.getDescription() == "Yummy");
        System.out.println("100% passed");
    }

    @Test
    public void testSetAndGetItem() {
        Item_Ref itemReference = new Item_Ref();
        MenuItem menuItem = new MenuItem();
        menuItem.setItem(itemReference);
        assertTrue("Passed", menuItem.getItem() == itemReference);
        System.out.println("100% passed");
    }
}