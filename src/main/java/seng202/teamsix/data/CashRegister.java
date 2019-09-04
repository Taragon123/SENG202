package seng202.teamsix.data;

/**
 * Name: CashRegister.java
 * Class is used to maintain the amount of cash we currently have in the cash register.
 *
 * Date: September, 2019
 * Author: Hamesh Ravji
 */

public class CashRegister {

    private Currency registerAmount;

    /**
     * Constructor class creates a new Currency object and sets the initial total cash value to $0.
     */
    public CashRegister() {
        registerAmount = new Currency();
        registerAmount.setTotalCash(0);
    }

    /**
     * Constructor class sets the registerAmount to the given Currency initialAmount value.
     * @param initialAmount The Currency object we wish to set the current registerAmount to.
     */
    public CashRegister(Currency initialAmount) {
        registerAmount = initialAmount;
    }

    /**
     * Returns the amount of cash currently in the cash register.
     * @return Amount of cash remaining in the register.
     */
    public Currency getRegisterAmount() {
        return registerAmount;
    }
}
