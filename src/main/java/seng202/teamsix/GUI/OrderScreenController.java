package seng202.teamsix.GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import seng202.teamsix.data.*;
import seng202.teamsix.data.MenuItem;
import seng202.teamsix.managers.OrderManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Name: OrderScreenController.java
 * Authors: Taran Jennison, Andy Clifford
 * Date: 07/09/2019
 * Last Updated: 19/09/2019, Andy
 */


public class OrderScreenController implements Initializable {

    /**
     * The OrderManager will mainly need to be used in the OrderScreenController.
     */
    private OrderManager orderManager;

    public Popup optionPopup = new Popup();
    private Stage window;
    private Scene managmentScene;
    private boolean isPopupInit = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String cost = String.format("Cost: $%.2f", 0.0);
        cost_field.setText(cost);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
            date_time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        TableColumn itemCol = new TableColumn<MenuItem, String>("Item");
        TableColumn priceCol = new TableColumn<MenuItem, String>("Price");
        itemCol.setMinWidth(265);
        priceCol.setMinWidth(92);

        itemCol.setCellValueFactory(new PropertyValueFactory("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        order_list_display.getColumns().clear();
        order_list_display.getColumns().addAll(itemCol, priceCol);

        Set<Menu_Ref> menu_refSet = StorageAccess.instance().getAllMenus(); //retrieve uuid of all menus
        for (Menu_Ref menu_ref: menu_refSet) {

            //Create Tab pane and add it to the list of Tab's (menu_tabs)
            Tab tab = createTab(menu_ref, "-fx-background-color: #576574; -fx-pref-width: 175; -fx-pref-height: 50; -fx-font-size: 20;");
            menu_tabs.getTabs().add(tab);

            //Create GridPane and set it as the content of the Tab
            GridPane grid = createGridPane();
            tab.setContent(grid);

            //Set the row and column constraints (this call enables the grid to have 5 columns and 5 rows)
            int colMax = 5; // the max col number
            int rowMax = 5; // the max row number
            setColumnConstraints(grid, colMax);
            setRowConstraints(grid, rowMax);

            //Now populate buttons with
            int currIndex = 0;
            ArrayList<MenuItem> menu_items = StorageAccess.instance().getMenu(menu_ref).getMenuItems();
            for (MenuItem menu_item : menu_items) {
                Button button = createButton(menu_item);
                grid.add(button, currIndex % colMax, currIndex / rowMax);
                currIndex++;
            }
        }
    }

    /**
     * Creates a Button object with text associated with MenuItem reference
     * @param menu_item a reference to a MenuItem (uuid)
     * @return a Button object with text set to the name of the MenuItem and userData set to the reference to that item (uuid)
     */
    private Button createButton(MenuItem menu_item) {
        Button button = new Button();
        String buttonText = menu_item.getName();
        button.setText(buttonText);
        button.setTextFill(Paint.valueOf("#FFFFFF"));
        button.setUserData(menu_item); //sets the user data of the button to the item reference (uuid)

        //Setup onAction event fort the button i.e. add_to_order
        EventHandler<ActionEvent> actionEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Button button = (Button)e.getSource(); //gets the button that was clicked
                MenuItem menu_item = (MenuItem)button.getUserData(); //cast the userData of the button to a menuItem
                add_to_order(menu_item);
            }
        };
        button.setOnAction(actionEvent);

        //layout options etc.
        button.setMnemonicParsing(false);
        button.setPrefHeight(150.0);
        button.setPrefWidth(200.0);
        button.setLayoutX(198.0);
        button.setLayoutY(20.0);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
        button.setStyle("-fx-background-color: #66390b; -fx-font-size: 20; -fx-background-radius: 10;");
        return button;
    }

    /**
     * Creates a Tab pane with text set to the name associated with the Menu Reference
     * @param menu_ref a reference to a Menu
     * @param style the colour and background options etc. from scenebuilder
     * @return a Tab pane with text set to the name associated with the Menu Reference
     */
    private Tab createTab(Menu_Ref menu_ref, String style) {
        Tab tab = new Tab();
        tab.setStyle(style);
        String tabText = StorageAccess.instance().getMenu(menu_ref).getName();
        tab.setText(tabText);
        tab.setClosable(false);
        return tab;
    }

    /**
     * Creates a GridPane and sets options equal to those in SceneBuilder
     * @return GridPane object
     */
    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        grid.setAlignment(Pos.CENTER);
        grid.setLayoutX(-1.0);
        grid.setLayoutY(1.0);
        grid.setPrefHeight(611.0);
        grid.setPrefWidth(910.0);
        grid.setStyle("-fx-background-color: #c8d6e5;");
        grid.setMinWidth(10.0);
        grid.setPadding(new Insets(10, 10, 10, 10));
        return grid;
    }

    /**
     * Sets the row constraints of a GridPane object so that it has rowMax rows.
     * @param grid a GridPane object
     * @param rowMax integer number of rows that the GridPane should have
     */
    private void setRowConstraints(GridPane grid, int rowMax) {
        for (int i = 0; i < rowMax; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(10.0);
            row.setPrefHeight(30.0);
            row.setValignment(VPos.CENTER);
            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }
    }

    /**
     * Sets the column constraints of a GridPane object so that it has colMax columns.
     * @param grid a GridPane object
     * @param colMax integer number of columns that the GridPane should have
     */
    private void setColumnConstraints(GridPane grid, int colMax) {
        for (int i = 0; i < colMax; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(10.0);
            column.setPrefWidth(100.0);
            column.setHalignment(HPos.CENTER);
            column.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(column);
        }
    }

    @FXML
    private Label date_time;

    @FXML
    private TabPane menu_tabs;

    @FXML
    private Label cost_field;

    @FXML
    private TableView order_list_display;


    public void add_to_order(MenuItem menu_item) {
        //OrderManager will add the specified item to cart #backend
        orderManager.addToCart(menu_item, 1);
        order_list_display.getItems().add(menu_item); //add the menu_item to the tableview
        double cost = menu_item.getPrice().getTotalCash();
        updateCostField(cost);

    }

    public void updateCostField(double cost) {
        String costText = cost_field.getText();
        int start = costText.indexOf("$") + 1;
        float currentCost = Float.parseFloat(costText.substring(start, costText.length()));
        currentCost += cost;
        cost_field.setText("Cost: $" + currentCost);
    }

    public void remove_from_order(MenuItem menu_item) {
        orderManager.removeFromCart(menu_item, 1);
        order_list_display.getItems().remove(menu_item);
    }

    public void confirm_order() throws IOException {
        System.out.println("Confirming");
        FXMLLoader loadConfirmOrder = new FXMLLoader(getClass().getResource("confirm_order.fxml"));
        OrderConfirmController orderConfirmController = new OrderConfirmController();

        loadConfirmOrder.setController(orderConfirmController);
        Parent confirmOrder = loadConfirmOrder.load();
        orderConfirmController.setOrderManager(orderManager);
        Scene confirmOrderScene = new Scene(confirmOrder, 750, 610);
        Stage confirmWindow = new Stage();
        confirmWindow.initModality(Modality.WINDOW_MODAL);
        confirmWindow.initOwner(window);
        confirmWindow.setScene(confirmOrderScene);
        confirmWindow.show();
    }

    public void cancel_order() {
        order_list_display.getItems().clear();
        cost_field.setText("Cost: $0.0");
        System.out.println("Canceled"); }

    public void open_management() {
        System.out.println("Management");
        optionPopup.hide();
        window.setScene(managmentScene);
    }

    public void toggle_options() {
        System.out.println("options");
        if (optionPopup.isShowing()) {
            optionPopup.hide();
        } else {
            Double centreHeight = window.getHeight()/2 - 250;
            Double centreWidth = window.getWidth()/2 - 270;
            optionPopup.show(window, window.getX()+centreWidth, window.getY()+centreHeight);
        }
    }

    public void open_filters() { System.out.println("filter"); }

    public void preSet(Stage primaryStage, Scene management) {
        window = primaryStage;
        managmentScene = management;
    }

    public void setOrderManager(OrderManager orderManager1) {
        orderManager = orderManager1;
    }
}