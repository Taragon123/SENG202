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
     * Constructor class sets the registerAmount to the given int initialAmount value.
     */
    public CashRegister(int initialAmount) {
        registerAmount = new Currency();
        registerAmount.setCents(initialAmount);
    }

    /**
     * Returns the amount of cash currently in the cash register.
     * @return Amount of cash remaining in the register.
     */
    public double getRegisterAmount() {
        return registerAmount.getTotalCash();
    }

    /**
     * This method sets the amount in the cash register given the total amount as an integer cents. The Currency class
     * wraps the cents around to dollars while the number of cents is greater than 100.
     * @param cents The amount we wish to set the cash register to, in cents.
     */
    public void setRegisterAmount(int cents) {
        registerAmount.setCents(cents);
    }

    /**
     * This method adds an integer containing the number of cents to add. As the Currency class already has cents wrap
     * around for dollars, we only need to add the cents.
     * @param centsToAdd The number of cents we want to add to the cash register.
     */
    public void addRegisterAmount(int centsToAdd) {
        registerAmount.setCents(registerAmount.getCents() + centsToAdd);
    }
}
