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

}
