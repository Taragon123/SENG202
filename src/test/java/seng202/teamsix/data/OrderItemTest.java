package seng202.teamsix.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderItemTest {

    @Test
    public void setItemTest() {
        OrderItem order = new OrderItem();
        UUID_Entity random_item = new UUID_Entity();
        order.setItem(random_item);
        assertTrue(random_item.equals(order.getItem()));
    }

    @Test
    public void addDependantTest() {
        OrderItem order = new OrderItem();
        OrderItem test = new OrderItem();
        assertEquals(0, order.getDependants().size());
        order.addDependant(test);
        assertEquals(1, order.getDependants().size());
    }

    @Test
    public void setQuantityTest() {
        OrderItem order = new OrderItem();

        order.setQuantity(50);
        assertEquals(50, order.getQuantity());

        order.setQuantity(0);
        assertEquals(0, order.getQuantity());

        order.setQuantity(-100);
        assertEquals(0, order.getQuantity());
    }
}