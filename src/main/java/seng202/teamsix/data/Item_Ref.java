package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item_ref")
public class Item_Ref extends UUID_Entity {
    public Item_Ref copyRef() {
        Item_Ref ref = new Item_Ref();
        ref.uuid = uuid;
        return ref;
    }
}
