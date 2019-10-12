package seng202.teamsix.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seng202.teamsix.data.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class SetCashFloat implements Initializable, CustomDialogInterface {
    private Stage stage;
    private CashRegister register;
    private Stage controller_window;


    @FXML
    private TextField dollarsInput;
    @FXML
    private Label errorBox;
    @FXML
    private TextField centsInput;

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
            float dollars = Float.parseFloat(dollarsInput.getText());
            float cents = Float.parseFloat(centsInput.getText());
            register.setRegisterAmount((int) ((dollars*100) + cents));
            controller_window.close();
        }
    }

    private boolean checkInputs() {
        errorBox.setText("");
        if (centsInput.getText().equals("")) {
            centsInput.setText("0");
        }
        if (dollarsInput.getText().equals("")) {
            dollarsInput.setText("0");
        }
        try {
            double dollarValue = Double.parseDouble(dollarsInput.getText());
            double centValue = Double.parseDouble(centsInput.getText());
            if (dollarValue < 0 || centValue < 0) {
                errorBox.setText("Price must be\ngreater or equal to $0");
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
