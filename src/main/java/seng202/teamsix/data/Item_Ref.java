package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item_ref")
public class Item_Ref extends UUID_Entity {
    private int quantity = 1;

    public Item_Ref() {}
    public Item_Ref(UUID_Entity entity) {
        uuid = entity.uuid;
    }

    public Item_Ref copyRef() {
        Item_Ref ref = new Item_Ref();
        ref.uuid = uuid;
        return ref;
    }

    public void setQuanity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
