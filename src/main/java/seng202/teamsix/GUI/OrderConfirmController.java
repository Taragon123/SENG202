package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seng202.teamsix.data.Currency;
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

    public void confirm_order() {
        System.out.println(totalChange.compareTo(orderCost));
        if (totalChange.compareTo(orderCost) <= 0) {
            System.out.println("confirm");
        } else {
            System.out.println("not enough change");
        }
    }

    public void setOrderManager(OrderManager order_manager) {
        orderManager = order_manager;
        orderCost = orderManager.getCashRequired();
        cost_field.setText("Cost: " + orderCost);
    }
}
