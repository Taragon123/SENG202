/**
 * Name: Order.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Orders are the finalised version of an order. An order contains an order tree of OrderItems that express items ordered.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Order extends Order_Ref{
    @XmlElement
    private OrderItem order_tree = new OrderItem();
    @XmlElement
    private Date timestamp;

    public OrderItem getOrderTree() {
        return order_tree;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int localTicketNumber;

    /**
     * Returns a Currency object corresponding to the cash required to pay for the order.
     */
    public Currency getCashRequired() {
        ArrayList<OrderItem> orderItems = getOrderTree().getDependants();
        double cashCountTemp = 0.0;
        Currency cashRequired = new Currency();
        for (OrderItem orderItem: orderItems) {
            Item item = StorageAccess.instance().getItem(orderItem.getItem());
            cashCountTemp += item.getMarkupPrice().getTotalCash() * orderItem.getQuantity();
        }
        cashRequired.setTotalCash(cashCountTemp);
        return cashRequired;
    }

    /**
     * This method prints the order, just the item names and quantity. Currently prints to the command line.
     */
    public String getChefOrder() {
        // need to find the depth of the order so that we can use the method getOrderTreeRepr() of the class OrderItem.
        String returnString = "";
        getOrderTree().getOrderTreeRepr(0);
        return returnString;
    }

    /**
     * This method prints the Order in a receipt format, currently to the command line.
     */
    public void printReceipt() {
        // similar to the printChefOrder, more details.

    }
}
