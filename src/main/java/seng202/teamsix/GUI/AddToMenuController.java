package seng202.teamsix.GUI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import seng202.teamsix.data.*;
import seng202.teamsix.data.Menu;
import seng202.teamsix.data.MenuItem;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddToMenuController implements Initializable {
    private Stage stage;
    private Item_Ref item_ref;
    private final List<UUID_Entity> menus;

    @FXML
    private TextField priceInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private ComboBox<String> menuDropdown;
    @FXML
    private Label titleLbl;

    public AddToMenuController(Item_Ref item_ref, List<UUID_Entity> menu) {
        this.item_ref = item_ref;
        this.menus = menu;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = StorageAccess.instance().getItem(item_ref).getName();
        titleLbl.setText("Add Stock of " + name);
        for (UUID_Entity entity: menus) {
            menuDropdown.getItems().add(StorageAccess.instance().getMenu(new Menu_Ref(entity)).getName());
        }

    }

    public void cancel() {
        System.out.println("hit");
        stage.close();
    }

    public void confirm() {
        Date expiryDate = null;
        if (checkInputs()) {
            float rawPrice = Float.parseFloat(priceInput.getText());
            Currency price = new Currency(rawPrice);
            Menu menu = StorageAccess.instance().getMenu(new Menu_Ref(menus.get(menuDropdown.getSelectionModel().getSelectedIndex())));
            MenuItem newMenuItem = new MenuItem();
            newMenuItem.setItem(item_ref);
            newMenuItem.setDescription(descriptionInput.getText());
            newMenuItem.setName(StorageAccess.instance().getItem(item_ref).getName());
            newMenuItem.setPrice(price);
            menu.addToMenu(newMenuItem);

            StorageAccess.instance().updateMenu(menu);
            stage.close();
        }
    }

    private boolean checkInputs() {
        try {
            double quantity = Double.parseDouble(priceInput.getText());
            return true;
        } catch (NumberFormatException e) {
            //TODO add error to GUI
            return false;
        }
    }


    public void preSet(Stage stage) {
        this.stage = stage;
    }

}
