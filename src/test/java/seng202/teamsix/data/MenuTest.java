package seng202.teamsix.data;

import org.junit.Test;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class MenuTest {
    @test
    public void testSetAndGetName(){
        Menu menus = new Menu();
        menus.setName("New Menu");
        assertEquals("New Menu", menus.getName());
    }

    @test
    public void testSetAndGetName(){
        Menu menus = new Menu();
        menus.setDescription("This Menu Is New");
        assertEquals("This Menu Is New", menus.getDescription());
    }

}

