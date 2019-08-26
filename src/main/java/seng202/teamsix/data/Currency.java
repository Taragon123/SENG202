package seng202.teamsix.data;

public class Currency {
    public double dollars;
    public double cents;
    public double totalCash;

    /**
     * Sets the value of dollars.
     * @param newDollarValue containing the amount of dollars.
     */
    void setDollars(double newDollarValue){
        dollars = newDollarValue;
    }

    /**
     * Gets the value of dollars.
     * @return double amount of dollars
     */
    double getDollars() {
        return dollars;
    }

    /**
     * Sets the value of cents.
     * @param newCentValue containing the amount of cents.
     */
    void setCents(double newCentValue){
        cents = newCentValue;
    }

    /**
     * Gets the value of cents.
     * @return double amount of cents
     */
    double getCents() {
        return cents;
    }

    /**
     * Gets the value of the total cash.
     * @return double amount of total cash
     */
    public double getTotalCash() {
        return totalCash;
    }

    /**
     * Sets the value of total cash.
     * @param newTotal containing the new cash total.
     */
    public void setTotalCash(double newTotal) {
        totalCash = newTotal;
    }

    /**
     * Calculates the new value of total cash and calls set total cash.
     * @param numDollars containing the amount of dollars.
     * @param numDollars containing the amount of cents.
     */
    public void addCash(double numDollars, double numCents) {
        double totalToAdd = numDollars + (numCents / 100);
        double newTotal = totalCash + totalToAdd;
        setTotalCash(newTotal);
    }

    /**
     * Calculates the new value of total cash and calls set total cash.
     * @param numDollars containing the amount of dollars.
     * @param numDollars containing the amount of cents.
     */
    public void subCash(double numDollars, double numCents) {
        double totalToSub = numDollars + (numCents / 100);
        double newTotal = totalCash - totalToSub;
        setTotalCash(newTotal);
    }
}
