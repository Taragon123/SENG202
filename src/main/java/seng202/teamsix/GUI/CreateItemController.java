package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.teamsix.data.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateItemController implements Initializable {
    private Item_Ref modifying_item = null;
    private Stage controller_window;
    private EventHandler<ActionEvent> callback = null;

    @FXML
    private Label label_ingredients;

    @FXML
    private Text label_title;

    @FXML
    private TextField textfield_search_items;

    @FXML
    private ListView<Item> listview_item_search;

    @FXML
    private ListView<Item> listview_selected_items;

    @FXML
    private TextField textfield_name;

    @FXML
    private ComboBox<UnitType> combobox_unit;

    @FXML
    private CheckBox checkbox_is_variant;

    @FXML
    private TextArea textfield_description;

    @FXML
    private TextArea textfield_recipe;

    @FXML
    private TextField textfield_wholesale;

    @FXML
    private TextField textfield_retail;

    @FXML
    private Button button_cancel;

    @FXML
    private Button button_confirm;

    @FXML
    private ListView<ItemTag> listview_tags;

    @FXML
    private Label label_errormessage;

    void createNewWindow(EventHandler<ActionEvent> callback_handler) {
        FXMLLoader loaderCreateItem = new FXMLLoader(getClass().getResource("modify_item_screen.fxml"));
        loaderCreateItem.setController(this);

        // If it cannot load fxml the function exits without creating window
        Parent parentCreateItem = null;
        try {
            parentCreateItem = loaderCreateItem.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene root = new Scene(parentCreateItem, 1024, 720);
        controller_window = new Stage();

        // Set Title
        if(modifying_item == null) {
            controller_window.setTitle("Create Item");
        } else {
            controller_window.setTitle("Modify Item");
        }

        callback = callback_handler;

        controller_window.setScene(root);
        controller_window.getIcons().add(new Image("file:assets/icons/icon.png"));
        controller_window.show();
    }

    CreateItemController(Item_Ref item_ref) {
        modifying_item = item_ref;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialise unit types
        combobox_unit.getItems().clear();
        for(UnitType unit : UnitType.values()) {
            combobox_unit.getItems().add(unit);
        }
        combobox_unit.setValue(UnitType.NUM);

        // Initialise tags
        DataQuery<ItemTag> query = new DataQuery<>(ItemTag.class);
        query.sort_by("name", true);
        List<UUID_Entity> tagref_list = query.runQuery();

        listview_tags.getItems().clear();
        for(UUID_Entity tag_ref : tagref_list) {
            ItemTag tag = StorageAccess.instance().getItemTag(new ItemTag_Ref(tag_ref));
            listview_tags.getItems().add(tag);
        }
        listview_tags.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Setup list view of list view
        listview_item_search.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Setup search listener
        textfield_search_items.textProperty().addListener((observable, oldValue, newValue) -> {
            updateItemSearchList();
        });
        updateItemSearchList();

        // Set title
        if(modifying_item == null) {
            label_title.setText("Create Item");
        } else {
            label_title.setText("Modify Item");
        }

        // Load modifying item data
        if (modifying_item != null) {
            Item item = StorageAccess.instance().getItem(modifying_item);
            if(item == null) {
                label_errormessage.setText("Failed to load item");
                return;
            }

            // Set Name
            textfield_name.setText(item.getName());

            // Set Unittype
            combobox_unit.setValue(item.getQtyUnit());

            // Set Checkbox
            checkbox_is_variant.setSelected(item instanceof VariantItem);

            // Set Description
            textfield_description.setText(item.getDescription());

            // Set Recipe
            textfield_recipe.setText(item.getRecipe().getMethod());

            // Set Base Price
            textfield_wholesale.setText(Double.toString(item.getBasePrice().getTotalCash()));

            // Set Retail Price
            textfield_retail.setText(Double.toString(item.getMarkupPrice().getTotalCash()));

            // Set Tags
            if(item.getTags() != null) {
                for(ItemTag_Ref ref : item.getTags()) {
                    ItemTag tag = StorageAccess.instance().getItemTag(ref);
                    if(tag != null) {
                        listview_tags.getSelectionModel().select(tag);
                    }
                }
            }

            // Set Item references
            List<Item_Ref> item_list = new ArrayList<>();
            if (item instanceof CompositeItem) {
                item_list = ((CompositeItem)item).getItems();
            } else if (item instanceof VariantItem) {
                item_list = ((VariantItem)item).getVariants();
            }

            if(item_list != null) {
                for(Item_Ref ref : item_list) {
                    Item selected_item = StorageAccess.instance().getItem(ref);
                    if (selected_item != null) {
                        listview_selected_items.getItems().add(selected_item);
                    }
                }
            }
            updateItemSearchList();
        }
    }

    @FXML
    private void updateItemSearchList() {
        String searchText = textfield_search_items.getText();
        DataQuery<Item> query = new DataQuery<>(Item.class);
        query.sort_by("name", true);

        if (searchText.length() != 0) {
            String regex = String.format("(?i).*(%s).*", searchText);
            query.addConstraintRegex("name", regex);
        }

        List<UUID_Entity> itemref_list = query.runQuery();
        listview_item_search.getItems().clear();
        for (UUID_Entity item_ref : itemref_list) {
            Item item = StorageAccess.instance().getItem(new Item_Ref(item_ref));
            if(!listview_selected_items.getItems().contains(item)) {
                listview_item_search.getItems().add(item);
            }
        }
    }

    @FXML
    void itemTypeChanged(ActionEvent event) {
        if (checkbox_is_variant.isSelected()) {
            label_ingredients.setText("Variant Items");
            textfield_search_items.setPromptText("Search Items");
        } else {
            label_ingredients.setText("Ingredients");
            textfield_search_items.setPromptText("Search Ingredients");
        }
    }

    @FXML
    void addItemClicked(ActionEvent event) {
        if (listview_item_search.getSelectionModel().getSelectedItem() != null) {
            listview_selected_items.getItems().add(listview_item_search.getSelectionModel().getSelectedItem());
            listview_item_search.getItems().remove(listview_item_search.getSelectionModel().getSelectedItem());
        }
    }


    @FXML
    void removeItemClicked(ActionEvent event) {
        if (listview_selected_items.getSelectionModel().getSelectedItem() != null) {
            listview_selected_items.getItems().remove(listview_selected_items.getSelectionModel().getSelectedItem());
            updateItemSearchList();
        }
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        controller_window.close();
    }

    ArrayList<Item_Ref> getSelectedItems() {
        // Down casting to just be item_references
        ArrayList<Item_Ref> selected = new ArrayList<>();
        for(Item item : listview_selected_items.getItems()) {
            selected.add(new Item_Ref(item));
        }

        return selected;
    }

    ArrayList<ItemTag_Ref> getSelectedTags() {
        ArrayList<ItemTag_Ref> selected = new ArrayList<>();
        for(ItemTag tag : listview_tags.getSelectionModel().getSelectedItems()) {
            selected.add(new ItemTag_Ref(tag));
        }

        return selected;
    }

    @FXML
    void confirmClicked(ActionEvent event) {
        Item item = null;

        if(modifying_item != null) {
            item = StorageAccess.instance().getItem(modifying_item);
        }

        // Initialise item
        ArrayList<Item_Ref> selected_items = getSelectedItems();
        if(item == null) {
            if(selected_items.size() > 0) {
                if(checkbox_is_variant.isSelected()) {
                    item = new VariantItem();
                } else {
                    item = new CompositeItem();
                }
            }else {
                item = new Item();
            }
        }

        // Set name
        item.setName(textfield_name.getText());

        // Set unit type
        item.setQtyUnit(combobox_unit.getValue());

        // Set description
        item.setDescription(textfield_description.getText());

        // Set Recipe
        item.setRecipe(new Recipe(textfield_recipe.getText()));

        // Set tags
        item.setTags(getSelectedTags());

        // Set Base Price
        try {
            Currency baseprice = new Currency(Double.parseDouble(textfield_wholesale.getText()));
            item.setBasePrice(baseprice);
        } catch(NumberFormatException e) {
            label_errormessage.setText("Wholesale price must be a number");
            return;
        }

        // Set Retail Price
        try {
            Currency retailPrice = new Currency(Double.parseDouble(textfield_retail.getText()));
            item.setMarkupPrice(retailPrice);
        } catch(NumberFormatException e) {
            label_errormessage.setText("Retail price must be a number");
            return;
        }

        // Set selected items
        if(item instanceof CompositeItem) {
            ((CompositeItem) item).setComponents(selected_items);
        }else if(item instanceof VariantItem) {
            ((VariantItem)item).setVariants(selected_items);
        }

        // Update item
        StorageAccess.instance().updateItem(item);

        // Quit window
        controller_window.close();
        callback.handle(new ActionEvent());
    }
}
