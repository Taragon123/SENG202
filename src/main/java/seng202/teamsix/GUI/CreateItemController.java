package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seng202.teamsix.data.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateItemController implements Initializable {
    private Item_Ref modifying_item = null;

    @FXML
    private Label label_ingredients;

    @FXML
    private TableView<?> table_selected_items;

    @FXML
    private TextField textfield_search_items;

    @FXML
    private TableView<Item> table_searchable_items;

    @FXML
    private TableColumn<Item, String> tablecol_itemname_search;

    @FXML
    private TextField textfield_name;

    @FXML
    private ComboBox<String> combobox_unit;

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

    void createNewWindow() {
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
        Stage stage = new Stage();
        stage.setTitle("Modify Item");
        stage.setScene(root);
        stage.getIcons().add(new Image("file:assets/icons/icon.png"));
        stage.show();
    }

    CreateItemController(Item_Ref item_ref) {
        modifying_item = item_ref;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialise unit types
        combobox_unit.getItems().clear();
        for(UnitType unit : UnitType.values()) {
            combobox_unit.getItems().add(unit.name());
        }
        combobox_unit.setValue(UnitType.NUM.name());

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

        // Setup table cell factories
        tablecol_itemname_search.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Setup search listener
        textfield_search_items.textProperty().addListener((observable, oldValue, newValue) -> {
            itemSearchTextChanged();
        });
        itemSearchTextChanged();

        if (modifying_item != null) {
        }
    }

    @FXML
    private void itemSearchTextChanged() {


        String searchText = textfield_search_items.getText();
        DataQuery<Item> query = new DataQuery<>(Item.class);
        query.sort_by("name", true);

        if (searchText.length() != 0) {
            String regex = String.format("(?i)[A-Z]*(%s)[A-Z]*", searchText);
            query.addConstraintRegex("name", regex);
        }

        List<UUID_Entity> itemref_list = query.runQuery();
        table_searchable_items.getItems().clear();
        for (UUID_Entity item_ref : itemref_list) {
            Item item = StorageAccess.instance().getItem(new Item_Ref(item_ref));
            table_searchable_items.getItems().add(item);
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
}
