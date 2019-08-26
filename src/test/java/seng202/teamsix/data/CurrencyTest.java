package seng202.teamsix.data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {
    @Test
    public void testSetAndGetDollars() {
        Currency cash = new Currency();
        cash.setDollars(5);
        assertEquals(5, cash.getDollars());
    }
    @Test
    public void testSetAndGetCents() {
        Currency cash = new Currency();
        cash.setCents(50);
        assertEquals(50, cash.getCents());
    }
    @Test
    public void testSetAndGetTotalCash() {
        Currency cash = new Currency();
        cash.setTotalCash(100);
        assertEquals(100, cash.getTotalCash());
    }
    @Test
    public void testAddCash() {
        Currency cash = new Currency();
        cash.setTotalCash(100);
        cash.addCash(5, 0);
        assertEquals(105, cash.getTotalCash());
        cash.setTotalCash(100);
        cash.addCash(5, 10);
        assertEquals(105.1, cash.getTotalCash());
    }
    @Test
    public void testSubCash() {
        Currency cash = new Currency();
        cash.setTotalCash(100);
        cash.subCash(5, 0);
        assertEquals(95, cash.getTotalCash());
        cash.setTotalCash(100);
        cash.subCash(5, 10);
        assertEquals(94.9, cash.getTotalCash());
    }
}
