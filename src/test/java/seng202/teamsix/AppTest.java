package seng202.teamsix;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
        assertTrue( true );
    }

    @Test
    public void testFoo() {
        App app = new App();
        assertTrue(app.foo());
    }

    @Test
    public void testFooTaran() {
        assertTrue(1+2 == 3);
    }

    @Test
    public void testQuickMath() {
        System.out.println("goes through this test case");
        assertTrue(2+2 == 4);
    }

    @Test
    public void testFooAnzac() {
        System.out.println("Testing");
        assertTrue(13 * 2 == 26);
    }

    @Test
    public void testAndy() {
        assertEquals(1 + 2, 3);
    }

    @Test
    public void testRchi() {
        System.out.println("BLAHHH");
        assertEquals(2 + 2, 4);
    }

    @Test
    public void testForGeorge() {
        System.out.println("Testing for george");
        assertEquals(4+3, 7);
    }
}
/*
Comment
 */