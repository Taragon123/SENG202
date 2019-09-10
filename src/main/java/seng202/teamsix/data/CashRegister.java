package seng202.teamsix.data;

/**
 * Class is used to maintain the amount of cash we currently have in the cash register.
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
    public double getRegisterAmount() {
        return registerAmount.getTotalCash();
    }

    public void setRegisterAmount(int cents) {
        registerAmount.setCents(cents);
    }
}
