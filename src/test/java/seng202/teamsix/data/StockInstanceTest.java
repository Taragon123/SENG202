package seng202.teamsix.data;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class StockInstanceTest {

    @Test
    public void testHasExpired() {
        Date date_added = new Date();
        Date date_expired = new Date(0);
        StockInstance stock_item = new StockInstance(date_added, true, date_expired, 100);
        assertEquals(true, stock_item.hasExpired());
    }


    @Test
    public void testTimeRemaining() {
        Date date_added = new Date();
        Date date_expired = new Date();
        StockInstance stock_item = new StockInstance(date_added, true, date_expired, 100);
        assertEquals(0, stock_item.timeRemaining());
    }

    @Test
    public void testSetQuantityRemaining() {
        Date date_added = new Date();
        Date date_expired = new Date();
        StockInstance stock_item = new StockInstance(date_added, true, date_expired, 100);

        assertEquals(100, stock_item.getQuantityRemaining(), 0.0001);

        //Test adding quantity
        stock_item.setQuantityRemaining(10);
        assertEquals(110, stock_item.getQuantityRemaining(), 0.0001);

        //Test subtracting quantity
        stock_item.setQuantityRemaining(-20);
        assertEquals(90, stock_item.getQuantityRemaining(), 0.0001);

        //Test subtracting more than what is remaining
        stock_item.setQuantityRemaining(-100);
        assertEquals(0, stock_item.getQuantityRemaining(), 0.0001);
    }
}