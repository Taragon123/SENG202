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
import java.util.ResourceBundle;import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.teamsix.data.*;
import seng202.teamsix.data.Menu;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;


public class SetCashFloat implements Initializable, CustomDialogInterface {
    private Stage stage;
    private CashRegister register;
    private Stage controller_window;

    @FXML
    private TextField quantityInput;
    @FXML
    private Label errorBox;

    void createNewWindow() {
        FXMLLoader loaderCreateItem = new FXMLLoader(getClass().getResource("cash_float_screen.fxml"));
        loaderCreateItem.setController(this);

        // If it cannot load fxml the function exits without creating window
        Parent parentCreateItem = null;
        try {
            parentCreateItem = loaderCreateItem.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene root = new Scene(parentCreateItem, 250, 225);
        controller_window = new Stage();
        controller_window.setTitle("Set cash float");
        controller_window.setScene(root);
        controller_window.getIcons().add(new Image("file:assets/icons/icon.png"));
        controller_window.show();
    }

    public SetCashFloat(CashRegister register) {
        this.register = register;
    }

    public void cancel() {
        controller_window.close();
    }

    public void confirm() {
        if (checkInputs()) {
            float rawPrice = Float.parseFloat(quantityInput.getText());
            register.setRegisterAmount((int) rawPrice);
            controller_window.close();
        }
    }

    private boolean checkInputs() {
        errorBox.setText("");
        try {
            double price = Double.parseDouble(quantityInput.getText());
            if (price <= 0) {
                errorBox.setText("Price must be\ngreater than $0");
                return false;
            }
        } catch(NumberFormatException e){
                errorBox.setText("Price must be a number");
                return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void preSet(Stage stage) {
        this.stage = stage;
    }
}
