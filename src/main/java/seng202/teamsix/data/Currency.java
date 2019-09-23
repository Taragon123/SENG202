/**
 * Name: UUID_Entity.java
 * Authors: Anzac Morel
 * Date: 19/08/2019
 */

package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Currency implements Comparable<Currency> {
    @XmlAttribute(name="cents")
    private int cents = 0;

    public Currency() { }

    public Currency(double value) {
        setTotalCash(value);
    }

    public Currency(int dollars, int cents) {
        addCash(dollars, cents);
    }

    /**
     * Sets the value of dollars.
     * @param newDollarValue containing the amount of dollars.
     */
    public void setDollars(int newDollarValue){
        cents = newDollarValue*100 + getCents();
    }

    /**
     * Gets the value of dollars.
     * @return int amount of dollars
     */
    public int getDollars() {
        return cents / 100;
    }

    /**
     * Sets the value of cents.
     * @param newCentValue containing the amount of cents.
     */
    public void setCents(int newCentValue){
        cents = getDollars() * 100 + newCentValue;
    }

    /**
     * Gets the value of cents.
     * @return int amount of cents
     */
    public int getCents() {
        return cents % 100;
    }

    /**
     * Gets the value of the total cash.
     * @return int amount of total cash
     */
    public double getTotalCash() {
        return cents / 100.0;
    }

    /**
     * Sets the value of total cash.
     * @param newTotal containing the new cash total.
     */
    public void setTotalCash(double newTotal) {
        cents = (int) Math.round(newTotal*100.0);
    }

    /**
     * Calculates the new value of total cash and calls set total cash.
     * @param numDollars containing the amount of dollars.
     * @param numDollars containing the amount of cents.
     */
    public void addCash(int numDollars, int numCents) {
        cents += numCents;
        cents += numDollars * 100;
    }
    /**
     * Calculates the new value of total cash and calls set total cash.
     * @param numDollars containing the amount of dollars.
     * @param numDollars containing the amount of cents.
     */
    public void subCash(int numDollars, int numCents) {
        cents -= numCents;
        cents -= numDollars * 100;
    }

    @Override
    public int compareTo(Currency currency) {
        int total_cents_A = currency.cents;
        int total_cents_B = this.cents;

        return total_cents_A - total_cents_B;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Currency) {
            return ((Currency)obj).cents == this.cents;
        }
        return false;
    }

    @Override
    //public String toString() {
    //    return String.format("$%d.%d", getDollars(), getCents());
    //}
    public String toString() {
        return String.format("$%.2f", getTotalCash());
    }
}
