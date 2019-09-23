package seng202.teamsix.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import seng202.teamsix.data.Menu;
import seng202.teamsix.data.MenuItem;
import seng202.teamsix.data.Menu_Ref;
import seng202.teamsix.data.StorageAccess;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMenu implements Initializable, CustomDialogInterface {
    private Menu menu;
    private Stage window;
    private boolean isNew;
    private ObservableList<MenuItemTableEntry> menuItemEntries = FXCollections.observableArrayList();
    @FXML
    private TextField nameInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private Label titleLbl;
    @FXML
    private TableView<MenuItemTableEntry> menuItemTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isNew) {
            titleLbl.setText("Add Menu");
        } else {
            titleLbl.setText("Edit Menu");
            nameInput.setText(menu.getName());
            descriptionInput.setText(menu.getDescription());
        }
        refresh();
        createMenuItemTable();
    }

    private void refresh() {
        getObservableMenuItemList(menuItemEntries);
    }

    public EditMenu(Menu_Ref menu_ref) {
        if (menu_ref == null) {
            menu = new Menu();
            isNew = true;
        } else {
            menu = StorageAccess.instance().getMenu(menu_ref);
            isNew = false;
        }
    }

    public void cancel() {
        window.close();
    }

    public void confirm() {
        if (nameInput.getText().length() > 0) {
            menu.setName(nameInput.getText());
            menu.setDescription(descriptionInput.getText());
            StorageAccess.instance().updateMenu(menu);
            window.close();
        }
    }

    public void preSet(Stage window) {
        this.window = window;
    }

    private void createMenuItemTable() {
//        menuItemTable = new TableView<>();
        menuItemTable.setItems(menuItemEntries);

        TableColumn<MenuItemTableEntry, String> nameColumn = new TableColumn<>("Item Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        menuItemTable.getColumns().add(nameColumn);

        TableColumn<MenuItemTableEntry, String> descColumn = new TableColumn<>("Description");
        descColumn.setMinWidth(100);
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        menuItemTable.getColumns().add(descColumn);

        TableColumn<MenuItemTableEntry, String> priceColumn = new TableColumn<>("Menu Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuPrice"));
        menuItemTable.getColumns().add(priceColumn);

        TableColumn<MenuItemTableEntry, Button> btnColumn = new TableColumn<>();
        btnColumn.setMinWidth(70);
        btnColumn.setCellValueFactory(new PropertyValueFactory<>("delBtn"));
        menuItemTable.getColumns().add(btnColumn);
    }

    private void getObservableMenuItemList(ObservableList<MenuItemTableEntry> observableListlist) {
        observableListlist.clear();
        for (MenuItem menuitem: menu.getMenuItems()) {
            observableListlist.add(new MenuItemTableEntry(menuitem, menu, this));
        }
    }

    public static class MenuItemTableEntry {
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;
        private final SimpleStringProperty menuPrice;
        private final Button delBtn;

        private MenuItemTableEntry(MenuItem menuItem, Menu menu, EditMenu parent) {
            this.name = new SimpleStringProperty(menuItem.getName());
            this.description = new SimpleStringProperty(menuItem.getDescription());
            this.menuPrice = new SimpleStringProperty(menuItem.getPrice().toString());
            this.delBtn = new Button("Remove");
            delBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    menu.removeFromMenu(menuItem);
                    parent.refresh();
                }
            });
        }

        public String getName() {
            return name.get();
        }
        public String getDescription() {
            return description.get();
        }
        public String getMenuPrice() {
            return menuPrice.get();
        }
        public Button getDelBtn() {
            return delBtn;
        }
    }
}
