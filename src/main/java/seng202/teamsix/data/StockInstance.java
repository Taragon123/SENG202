package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Class StockInstance, allows us to check if stock is expired and keep track of remaining quantities
 * Author: Andrew Clifford
 * Date Created: 27/08/19
 * Last Updated: 29/08/19
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class StockInstance extends StockInstance_Ref {

    @XmlElement @QueryField
    private Item_Ref stock_item;
    @XmlElement @QueryField
    private Date date_added;
    @XmlElement @QueryField
    private Date date_expires;
    @XmlElement @QueryField
    private boolean does_expire;
    @XmlElement @QueryField
    private float quantity_remaining;

    /**
     * Empty constructor
     */
    public StockInstance() {}

    /**
     * Constructor for a StockInstance. If an item does not expire then it must be constructed with null for
     * the expiry date.
     * @param date_added the date that the StockInstance was added
     * @param does_expire boolean does the StockInstance expire
     * @param date_expires the date that the StockInstance expires
     */
    public StockInstance(Date date_added, boolean does_expire, Date date_expires, float quantity_remaining, Item_Ref item_ref) {
        super(); //generates uuid for the StockInstance
        this.stock_item = item_ref;
        this.date_added = date_added;
        this.does_expire = does_expire;
        this.date_expires = date_expires; //Instantiated with null if the item does not expire
        this.quantity_remaining = quantity_remaining;
    }

    /**
     * Compares the expiry date with the current date to check if stock has expired or not
     * @return true if the StockInstance expires AND current_date is past the expiry_date,
     * false otherwise
     */
    public boolean hasExpired() {
        if (does_expire) {
            return timeRemaining() <= 0; //checks if the date_now past the expiry_date
        } else {
            return false;
        }
    }

    /**
     * Calculates the difference in days between the date_expires and the current date
     * @return the difference in days as an integer
     */
    public int timeRemaining() {
        Date date_now = new Date();
        int diff_in_days = (int)( (date_expires.getTime() - date_now.getTime()) / (1000 * 60 * 60 * 24) );
        return diff_in_days;
    }

    public Date getDateAdded() {return date_added;}

    public Date getDateExpired() {return date_expires;}

    public float getQuantityRemaining() {return quantity_remaining;}

    public boolean getDoesExpire() {return does_expire;}

    public Item_Ref getStockItem() {return stock_item;}

    /**
     * Adds quantity to the quantity remaining i.e. to reduce the quantity remaining, pass in a negative
     * float as te parameter
     * @param quantity the quantity of the StockInstance to be added to the quantity remaining
     */
    public void setQuantityRemaining(float quantity) {
        quantity_remaining += quantity;
        if (quantity_remaining < 0) {
            quantity_remaining = 0;
        }
    }
}
