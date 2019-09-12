package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="order_ref")
public class Order_Ref extends UUID_Entity {
    public Order_Ref copyRef() {
        Order_Ref ref = new Order_Ref();
        ref.uuid = uuid;
        return ref;
    }
}
