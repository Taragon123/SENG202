package seng202.teamsix;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testFoo() {
        App app = new App();
        assertTrue(app.foo());
    }

    public void testFooTaran() {
        assertTrue(1+2 == 3);
    }

    public void testQuickMath() {
        System.out.println("goes through this test case");
        assertTrue(2+2 == 4);
    }

    public void testAndy() {
        assertEquals(1 + 2, 3);
    }
}
