package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import seng202.teamsix.data.Currency;
import seng202.teamsix.data.Item;
import seng202.teamsix.data.OrderItem;
import seng202.teamsix.data.StorageAccess;
import seng202.teamsix.managers.OrderManager;

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

    public void add_change(ActionEvent event) {
        String input = ((Button) event.getSource()).getText();
        totalChange.addCash(0, currencyConvert.get(input));
        change_field.setText("Change: " + totalChange);
    }

    public void eftpos_toggle() {
        isEftpos = !isEftpos;
        System.out.println(String.format("Eftpos: %b", isEftpos));
    }

    public void cancel_confirm(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    public void confirm_order(ActionEvent event) {
        System.out.println(totalChange.compareTo(orderCost));
        if (totalChange.compareTo(orderCost) > 0 && !isEftpos) {
            System.out.println("not enough change");
        } else {
            System.out.println("confirm");
            System.out.println(String.format("change due: $%d", orderCost.compareTo(totalChange)/100));
            System.out.println(orderManager.getCartOrderItems());
            orderManager.finaliseOrder();
            orderScreenController.clear_order();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    public void setOrderManager(OrderManager order_manager, OrderScreenController orderController) {
        orderManager = order_manager;
        orderScreenController = orderController;
        orderCost = orderManager.getCashRequired();
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