package seng202.teamsix.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.teamsix.data.Item_Ref;
import seng202.teamsix.data.StockInstance;
import seng202.teamsix.data.StorageAccess;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class StockInstanceDialog implements Initializable, CustomDialogInterface {
    private Stage stage;
    private Item_Ref item_ref;

    @FXML
    private TextField quantityInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private CheckBox notHasExpiryInput;
    @FXML
    private Label titleLbl;

    /**
     * Constructor that sets the item_ref for stock instance
     * @param item_ref stock instance will be created with this Item_Ref
     */
    public StockInstanceDialog(Item_Ref item_ref) {
        this.item_ref = item_ref;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = StorageAccess.instance().getItem(item_ref).getName();
        titleLbl.setText("Add Stock of " + name);
    }

    /**
     * Closes window
     */
    public void cancel() {
        System.out.println("hit");
        stage.close();
    }

    /**
     * Checks inputs adds StockInstance and closes window
     */
    public void confirm() {
        Date expiryDate = null;
        if (checkInputs()) {
            if (!notHasExpiryInput.selectedProperty().get()) {
                LocalDate localDate = dateInput.getValue();
                expiryDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
            float quantity = Float.parseFloat(quantityInput.getText());
            Date currentDate = new Date();
            StorageAccess.instance().updateStockInstance(new StockInstance(currentDate, !notHasExpiryInput.selectedProperty().get(), expiryDate, quantity, item_ref));
            stage.close();
        }
    }

    /**
     * Checks inputs
     * @return true if valid
     */
    private boolean checkInputs() {
        try {
            Double.parseDouble(quantityInput.getText());
            return true;
        } catch (NumberFormatException e) {
            //TODO add error to GUI
            return false;
        }
    }

    /**
     * Sets parent stage
     * @param stage parent stage
     */
    public void preSet(Stage stage) {
        this.stage = stage;
    }

    /**
     * Disables DatePicker if checkbox selected
     */
    public void updateCheckbox() {
        if (notHasExpiryInput.selectedProperty().get()) {
            dateInput.setDisable(true);
            dateInput.getEditor().clear();
        } else {
            dateInput.setDisable(false);
        }
    }
}
