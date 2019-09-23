package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import seng202.teamsix.data.Currency;
import seng202.teamsix.data.Item;
import seng202.teamsix.data.OrderItem;
import seng202.teamsix.data.StorageAccess;
import seng202.teamsix.managers.OrderManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class OrderConfirmController implements Initializable {

    private HashMap<String, Integer> currencyConvert = new HashMap<String, Integer>();
    private Currency totalChange = new Currency();
    private Currency orderCost = new Currency(); //get order cost
    private boolean isEftpos = false;
    private OrderManager orderManager;
    private OrderScreenController orderScreenController;
    private Popup changePopup = new Popup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        change_field.setText("Change: " + totalChange);
        cost_field.setText("Cost: ");
        currencyConvert.put("10c", 10);
        currencyConvert.put("20c", 20);
        currencyConvert.put("50c", 50);
        currencyConvert.put("$1", 100);
        currencyConvert.put("$2", 200);
        currencyConvert.put("$5", 500);
        currencyConvert.put("$10", 1000);
        currencyConvert.put("$20", 2000);
        currencyConvert.put("$50", 5000);
        currencyConvert.put("$100", 10000);
    }
    @FXML
    private TableView order_list_display;

    @FXML
    private TableColumn<OrderItem, String> itemCol;

    @FXML
    private TableColumn<Currency, String> priceCol;

    @FXML
    private Label cost_field;

    @FXML
    private Label change_field;

    @FXML
    private Button confirmButton;

    public void add_change(ActionEvent event) {
        String input = ((Button) event.getSource()).getText();
        totalChange.addCash(0, currencyConvert.get(input));
        change_field.setText("Change: " + totalChange);
        if (totalChange.compareTo(orderCost) <= 0 || isEftpos) {
            confirmButton.setDisable(false);
        } else {
            confirmButton.setDisable(true);
        }
    }

    public void eftpos_toggle() {
        isEftpos = !isEftpos;
        System.out.println(String.format("Eftpos: %b", isEftpos));
        if (totalChange.compareTo(orderCost) <= 0 || isEftpos) {
            confirmButton.setDisable(false);
        } else {
            confirmButton.setDisable(true);
        }
    }

    public void cancel_confirm(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    public void confirm_order(ActionEvent event) throws IOException {
//        System.out.println(totalChange.compareTo(orderCost));
//        System.out.println("confirm");
//        System.out.println(orderManager.getCartOrderItems());
        orderManager.finaliseOrder();
        orderScreenController.clear_order();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        if (!isEftpos) {
            displayChange(window);
        }
    }

    @FXML
    private Label changeDueField;

    private void displayChange(Stage window) throws IOException {
        Window mainScreen = window.getOwner();
        FXMLLoader loaderOptions = new FXMLLoader(getClass().getResource("change_due.fxml"));
        loaderOptions.setController(this);
        Parent pop = loaderOptions.load();
        changePopup.getContent().add(pop);
        changePopup.setAutoHide(true);
        changeDueField.setText(String.format("$%.2f",(float) orderCost.compareTo(totalChange)/100));
        Double centreHeight = mainScreen.getHeight()/2 - 250;
        Double centreWidth = mainScreen.getWidth()/2 - 270;
        changePopup.show(mainScreen, mainScreen.getX()+centreWidth, mainScreen.getY()+centreHeight);
    }

    public void close_change() {
        changePopup.hide();
    }

    public void setOrderManager(OrderManager order_manager, OrderScreenController orderController) {
        orderManager = order_manager;
        orderScreenController = orderController;
        orderCost = orderManager.getCart().getTotalCost();
        cost_field.setText("Cost: " + orderCost);

        /* init table view
        for (OrderItem order_item : orderManager.getCartOrderItems()) {
            Item item = StorageAccess.instance().getItem(order_item.getItem());
            String name = item.getName();
            //Currency cost = order_item.getCost();
            order_list_display.getItems().add(name);
        }*/
    }

}
