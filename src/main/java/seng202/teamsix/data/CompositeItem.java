package seng202.teamsix.data;

import java.util.List;

public class CompositeItem {
    private List<UUID_Entity> item_list;
    private Recipe recipe;

    public CompositeItem(List<UUID_Entity> item_list) {
        this.item_list = item_list;
    }

    public List<UUID_Entity> getItems() {
        return item_list;
    }

    public  String getRecipe() {
        return recipe.toString();
    }
}
