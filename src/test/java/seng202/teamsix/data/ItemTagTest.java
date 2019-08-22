package seng202.teamsix.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTagTest {

    @Test
    void getIsDominant() {
        ItemTag tag = new ItemTag("Gluten free!", false);
        assertEquals(false, tag.getIsDominant());
        tag = new ItemTag("this tag is very cool my dude", true);
        assertEquals(true, tag.getIsDominant());
    }

    @Test
    void getName() {
        ItemTag tag = new ItemTag("Gluten free!", false);
        assertEquals("Gluten free!", tag.getName());
        tag = new ItemTag("this tag is very cool my dude", false);
        assertEquals("this tag is very cool my dude", tag.getName());
    }
}