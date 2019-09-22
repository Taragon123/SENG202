/**
 * Name: Order.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
    @XmlElement @QueryField
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
}
