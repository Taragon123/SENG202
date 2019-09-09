package seng202.teamsix.managers;

import seng202.teamsix.data.*;

import java.util.ArrayList;

public class FinancialManager {
    Item_Ref createItem(Item item) {
        StorageAccess.instance().updateItem(item);
        return item;
    }

    /*ArrayList<Item_Ref> getItemByTag(ItemTag_Ref tag) {
        ArrayList<Item_Ref> items = StorageAccess.instance().getAllItems();
        ArrayList<Item_Ref> output = new ArrayList<Item_Ref>();
        for(int i = 0; i < items.size(); i++) {
            Item item = StorageAccess.instance().getItem(items[i]);
            if(item.getTags().contains(tag)) {
                output.add(items[i]);
            }
        }
        return output;
    }*/
}
