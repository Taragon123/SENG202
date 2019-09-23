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

    public Currency(int intiDollars, int intiCents) { setValue(intiDollars, intiCents); }

    /**
     * Sets the value of the Currency object to the given dollar and cent value
     * @param newDollars int amount of dollars
     * @param newCents int amount of cents
     */
    public void setValue(int newDollars, int newCents) { cents = newDollars*100 + newCents; }

    /**
     * Returns the current value of cents as an int
     * @return total amount of cents in Currency (
     */
    public int getValue() {
        return cents;
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
     * Gets the remainder cents after dollars.
     * @return int amount of cents after dollars
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
     * Sets the value of total cash using double as input.
     * @param newTotal Double containing the new cash total.
     */
    public void setTotalCash(double newTotal) {
        cents = (int) Math.round(newTotal*100.0);
    }

    /**
     * Calculates the new value of total cash by adding the cents of the additional to the current cents value
     * @param additional The Currency object that is to be added to the current Currency object
     */
    public void addCash(Currency additional) {
        cents += additional.getValue();
    }
    /**
     * Calculates the new value of total cash by subtracting the cents of the subtrahend to the current cents value
     * @param subtrahend Currency containing the amount of cents to be deducted
     */
    public void subCash(Currency subtrahend) {
        cents -= subtrahend.getValue();
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
