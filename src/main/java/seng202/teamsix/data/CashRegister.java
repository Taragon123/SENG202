package seng202.teamsix.data;

/**
 * Name: CashRegister.java
 * Class is used to maintain the amount of cash we currently have in the cash register.
 *
 * Date: September, 2019
 * Author: Hamesh Ravji
 */

/**
 * This enum is used to keep track of the currency types, the coins and notes along with their associated values.
 */
enum CurrencyType {
    HUNDRED_D(100), FIFTY_D(50), TWENTY_D(20), TEN_D(10), FIVE_D(5), TWO_D(2),
    ONE_D(1), FIFTY_C(0.50), TWENTY_C(0.20), TEN_C(0.10);

    public double value;

    CurrencyType(double value) {
        this.value = value;
    }

}

class Till {
    private int num100Notes = 0;
    private int num50Notes = 0;
    private int num20Notes = 0;
    private int num10Notes = 0;
    private int num5Notes = 0;
    private int num10cCoins = 0;
    private int num20cCoins = 0;
    private int num50cCoins = 0;
    private int num1dCoins = 0;
    private int num2dCoins = 0;

    public int getNumNotesOrCoins(CurrencyType currencyType) {
        switch(currencyType) {
            case HUNDRED_D:
                return num100Notes;
            case FIFTY_D:
                return num50Notes;
            case TWENTY_D:
                return num20Notes;
            case TEN_D:
                return num10Notes;
            case TWO_D:
                return num2dCoins;
            case FIVE_D:
                return num5Notes;
            case ONE_D:
                return num1dCoins;
            case FIFTY_C:
                return num50cCoins;
            case TWENTY_C:
                return num20cCoins;
            case TEN_C:
                return num10cCoins;
        }
        return 0;
    }

    public void setNumNotesOrCoins(CurrencyType currencyType, int n) {
        switch(currencyType) {
            case HUNDRED_D:
                num100Notes += n;
                break;
            case FIFTY_D:
                num50Notes += n;
                break;
            case TWENTY_D:
                num20Notes += n;
                break;
            case TEN_D:
                num10Notes += n;
                break;
            case FIVE_D:
                num5Notes += n;
                break;
            case TWO_D:
                num2dCoins += n;
                break;
            case ONE_D:
                num1dCoins += n;
                break;
            case FIFTY_C:
                num50cCoins += n;
                break;
            case TWENTY_C:
                num20cCoins += n;
                break;
            case TEN_C:
                num10cCoins += n;
                break;
        }

    }

    public Till(int num100s, int num50s, int num20s, int num10s, int num5s, int num2s, int num1s, int num50cs,
                int num20cs, int num10cs) {
        this.num100Notes = num100s;
        this.num50Notes = num50s;
        this.num20Notes = num20s;
        this.num10Notes = num10s;
        this.num5Notes = num5s;
        this.num2dCoins = num2s;
        this.num1dCoins = num1s;
        this.num50cCoins = num50cs;
        this.num20cCoins = num20cs;
        this.num10cCoins = num10cs;
    }

    public boolean equals(Till other) {
        return (this.num100Notes == other.num100Notes &&
                this.num50Notes == other.num50Notes &&
                this.num20Notes == other.num20Notes &&
                this.num10Notes == other.num10Notes &&
                this.num5Notes == other.num5Notes &&
                this.num2dCoins == other.num2dCoins &&
                this.num1dCoins == other.num1dCoins &&
                this.num50cCoins == other.num50cCoins &&
                this.num20cCoins == other.num20cCoins &&
                this.num10cCoins == other.num10cCoins);
    }

    public Till() {}

}

public class CashRegister {

    private Currency registerAmount;
    private Till till;

    /**
     * Constructor class creates a new Currency object and sets the initial total cash value to $0.
     */
    public CashRegister() {
        registerAmount = new Currency();
        registerAmount.setTotalCash(0);
        till = new Till();
    }

    /**
     * Constructor class sets the registerAmount to the given Currency initialAmount value.
     * @param tillToAssign The Till object we wish to set the current till to.
     */
    public CashRegister(Till tillToAssign) {
        till = tillToAssign;
        registerAmount = new Currency();
        updateRegisterAmount(till);
    }

    /**
     * Updates the registerAmount so that it is concurrent with the amount of notes and coins in the till.
     * @param till A Till object which contains the amount of notes and coins currently in the till.
     */
    private void updateRegisterAmount(Till till) {
        double amountInTill = 0.0;
        amountInTill += 100 * till.getNumNotesOrCoins(CurrencyType.HUNDRED_D);
        amountInTill += 50 * till.getNumNotesOrCoins(CurrencyType.FIFTY_D);
        amountInTill += 20 * till.getNumNotesOrCoins(CurrencyType.TWENTY_D);
        amountInTill += 10 * till.getNumNotesOrCoins(CurrencyType.TEN_D);
        amountInTill += 5 * till.getNumNotesOrCoins(CurrencyType.FIVE_D);
        amountInTill += 2 * till.getNumNotesOrCoins(CurrencyType.TWO_D);
        amountInTill += 1 * till.getNumNotesOrCoins(CurrencyType.ONE_D);
        amountInTill += 0.5 * till.getNumNotesOrCoins(CurrencyType.FIFTY_C);
        amountInTill += 0.2 * till.getNumNotesOrCoins(CurrencyType.TWENTY_C);
        amountInTill += 0.1 * till.getNumNotesOrCoins(CurrencyType.TEN_C);
        registerAmount.setTotalCash(amountInTill);
    }


    /**
     * Returns the amount of cash currently in the cash register.
     * @return Amount of cash remaining in the register.
     */
    public Currency getRegisterAmount() {
        return registerAmount;
    }

    /**
     * A getter for the till.
     * @return A Till object which contains the number of each note/coin in the register.
     */
    public Till getTill() {
        return till;
    }

    /**
     * A setter for the till.
     * @param till The Till object we wish to assign to the till object.
     */
    public void setTill(Till till) {
        this.till = till;
    }

    /**
     * This is currently very inefficient in terms of reusing code, will be refactored at a later date.
     * This function returns a denomination of the given amount based on the notes and coins in the cash register.
     * @param amount The amount for which we want to return the denomination.
     * @return A Till object where the number of each type of note/coin is stored in the object.
     */
    public Till getDenomination(double amount) {

        Till denomTill = new Till();
        Till tempTill = this.till;
        if (amount == 0 || amount < 0) {
            return denomTill;
        } else {
            for(CurrencyType type : CurrencyType.values())
            {
                while(amount >= type.value && tempTill.getNumNotesOrCoins(type) > 0) {
                    tempTill.setNumNotesOrCoins(type, tempTill.getNumNotesOrCoins(type) - 1);
                    denomTill.setNumNotesOrCoins(type,denomTill.getNumNotesOrCoins(type) + 1);
                    amount -= type.value;
                }
            }
        }
        return denomTill;
    }

    /**
     * Adds the number of each coin and note listed in the tillToAdd object to the cash register till object.
     * @param tillToAdd Contains the number of notes and coins that we want to add to the cash register till.
     */
    public void addToTill(Till tillToAdd) {
        for (CurrencyType type: CurrencyType.values()) {
            this.till.setNumNotesOrCoins(type, (this.till.getNumNotesOrCoins(type) + tillToAdd.getNumNotesOrCoins(type)));
        }
    }

    /**
     * This function removes all the notes from the tillToRemove from the cash register till. If the tillToRemove has
     * more of a note/coin than the till currently has, return false.
     * @param tillToRemove Contains the number of each coin and note that we want to be removed from the till.
     * @return A boolean indicating whether there is enough of each of the coins and notes listed in the tillToRemove
     * within the cash register till.
     */
    public boolean removeFromTill(Till tillToRemove) {
        Till tempTill = this.till;
        for (CurrencyType type: CurrencyType.values()) {
            if (tempTill.getNumNotesOrCoins(type) >= tillToRemove.getNumNotesOrCoins(type)) {
                tempTill.setNumNotesOrCoins(type, (tempTill.getNumNotesOrCoins(type) - tillToRemove.getNumNotesOrCoins(type)));
            } else {
                return false;
            }
        }
        this.till = tempTill;
        return true;
    }
}
