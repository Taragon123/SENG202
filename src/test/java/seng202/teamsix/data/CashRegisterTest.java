package seng202.teamsix.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    @Test
    void getRegisterAmountTest() {
        Till till = new Till(1, 2, 1, 3, 1, 5, 6, 4, 5, 10);
        CashRegister cashRegister = new CashRegister(till);
        assertEquals(275.0, cashRegister.getRegisterAmount().getTotalCash());
    }

    @Test
    void getDenominationTest() {
        Till till = new Till(5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
        CashRegister cashRegister = new CashRegister(till);
        Till actualDenomination = cashRegister.getDenomination(125);

        Till expectedDenomination = new Till(1, 0, 1, 0, 1, 0, 0, 0, 0, 0);

        assertTrue(actualDenomination.equals(expectedDenomination));
    }
}