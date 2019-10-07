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
        registerAmount.setTotalCash(initialAmount);
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
     * This method adds a Currency object, containing the number of cents to add, to the current registerAmount Currency
     * @param amount The Currency to be added containing the amount of cents that will be added
     */
    public void addRegisterAmount(Currency amount) {
        registerAmount.addCash(amount);
    }

    /**
     * This method adds a Currency object, containing the number of cents to subtract, to the current registerAmount Currency
     * @param amount The Currency to be subtracted containing the amount of cents that will be subtracted
     */
    public void subRegisterAmount(Currency amount) {
        registerAmount.subCash(amount);
    }
}
