/**
 * Name: UUID_Entity.java
 * Authors: Anzac Morel
 * Date: 19/08/2019
 */

package seng202.teamsix.data;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Currency {
    @XmlAttribute(name="dollars")
    private int dollars = 0;
    @XmlAttribute(name="cents")
    private int cents = 0;


    public Currency() { }

    public Currency(int dollars, int cents) {
        addCash(dollars, cents);
    }

    /**
     * Sets the value of dollars.
     * @param newDollarValue containing the amount of dollars.
     */
    public void setDollars(int newDollarValue){
        dollars = newDollarValue;
    }

    /**
     * Gets the value of dollars.
     * @return int amount of dollars
     */
    public int getDollars() {
        return dollars;
    }

    /**
     * Sets the value of cents.
     * @param newCentValue containing the amount of cents.
     */
    public void setCents(int newCentValue){
        cents = 0;
        if (newCentValue > 0) {
            addCash(0, newCentValue);
        }
        else {
            subCash(0, -newCentValue);
        }
    }

    /**
     * Gets the value of cents.
     * @return int amount of cents
     */
    public int getCents() {
        return cents;
    }

    /**
     * Gets the value of the total cash.
     * @return int amount of total cash
     */
    public double getTotalCash() {
        return dollars + (cents / 100.0);
    }

    /**
     * Sets the value of total cash.
     * @param newTotal containing the new cash total.
     */
    public void setTotalCash(double newTotal) {
        dollars = (int)newTotal;
        cents = (int)Math.round(BigDecimal.valueOf(newTotal).divideAndRemainder(BigDecimal.ONE)[1].doubleValue() * 100.0);
    }

    /**
     * Calculates the new value of total cash and calls set total cash.
     * @param numDollars containing the amount of dollars.
     * @param numDollars containing the amount of cents.
     */
    public void addCash(int numDollars, int numCents) {
        cents += numCents;
        dollars += numDollars + cents / 100;
        cents = Math.floorMod(cents, 100);
    }
    /**
     * Calculates the new value of total cash and calls set total cash.
     * @param numDollars containing the amount of dollars.
     * @param numDollars containing the amount of cents.
     */
    public void subCash(int numDollars, int numCents) {
        dollars -= numDollars + Math.ceil(numCents / 100.0);
        cents = Math.floorMod(cents - numCents, 100);
    }
}
