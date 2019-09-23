package seng202.teamsix.GUI;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import seng202.teamsix.data.*;
import seng202.teamsix.data.MenuItem;
import seng202.teamsix.managers.OrderManager;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private ArrayList<OrderTableEntry> orderList;

    public OrderConfirmController(ArrayList<OrderScreenController.OrderTableEntry> orderList) {

        this.orderList = new ArrayList<>();
        for (OrderScreenController.OrderTableEntry entry: orderList) {
            MenuItem menu_item = entry.getMenuItem();
            OrderTableEntry e = new OrderTableEntry(menu_item, this);
            this.orderList.add(e);
        }
    }

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

        TableColumn itemCol = new TableColumn<MenuItem, String>("Item");
        TableColumn priceCol = new TableColumn<MenuItem, String>("Price");
        itemCol.setMinWidth(270);
        priceCol.setMinWidth(90);
        itemCol.setCellValueFactory(new PropertyValueFactory("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        order_list_display.getColumns().clear();
        order_list_display.setSelectionModel(null);
        order_list_display.getColumns().addAll(itemCol, priceCol);

        for (OrderTableEntry entry: orderList) {
            order_list_display.getItems().add(entry);
        }

    }
    @FXML
    private TableView<OrderTableEntry> order_list_display;

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
        if (input.equals("Clear")) {
            totalChange.setTotalCash(0);
            change_field.setText("Change: " + totalChange);
        } else {
            totalChange.addCash(0, currencyConvert.get(input));
            change_field.setText("Change: " + totalChange);
        }
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

    }

    public static class OrderTableEntry {
        private final MenuItem menu_item;
        private final SimpleStringProperty name;
        private final SimpleStringProperty price;

        private OrderTableEntry(MenuItem menu_item, OrderConfirmController parent) {
            this.menu_item = menu_item;
            this.name = new SimpleStringProperty(menu_item.getName());
            this.price = new SimpleStringProperty(menu_item.getPrice().toString());
        }

        public String getName() { return name.get(); }
        public String getPrice() { return price.get(); }
    }

}
