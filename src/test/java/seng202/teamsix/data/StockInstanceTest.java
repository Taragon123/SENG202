package seng202.teamsix.data;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StockInstanceTest {

    @Test
    void testHasExpired() {
        Date date_added = new Date();
        Date date_expired = new Date();
        StockInstance stock_item = new StockInstance(date_added, true, date_expired, 100);
        assertEquals(stock_item.hasExpired(), true);
    }

    @Test
    void testTimeRemaining() {
        Date date_added = new Date();
        Date date_expired = new Date();
        StockInstance stock_item = new StockInstance(date_added, true, date_expired, 100);
        assertEquals(0, stock_item.timeRemaining());
    }

    @Test
    void testSetQuantityRemaining() {
        Date date_added = new Date();
        Date date_expired = new Date();
        StockInstance stock_item = new StockInstance(date_added, true, date_expired, 100);

        assertEquals(100, stock_item.getQuantityRemaining());

        //Test adding quantity
        stock_item.setQuantityRemaining(10);
        assertEquals(110, stock_item.getQuantityRemaining());

        //Test subtracting quantity
        stock_item.setQuantityRemaining(-20);
        assertEquals(90, stock_item.getQuantityRemaining());

        //Test subtracting more than what is remaining
        stock_item.setQuantityRemaining(-100);
        assertEquals(0, stock_item.getQuantityRemaining());
    }
}