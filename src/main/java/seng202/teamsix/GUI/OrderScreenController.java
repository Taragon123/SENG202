package seng202.teamsix.GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import seng202.teamsix.data.*;
import seng202.teamsix.data.MenuItem;

import java.awt.*;
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
 * Last Updated: 17/09/2019
 */


public class OrderScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String cost = String.format("Cost: $%.2f", 10.20);
        cost_field.setText(cost);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
            date_time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        Set<Menu_Ref> menu_refSet = StorageAccess.instance().getAllMenus(); // retrieve uuid of all menus
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
        button.setUserData(menu_item.getItem()); //sets the user data of the button to the item reference (uuid)

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
    private GridPane burger_grid;

    @FXML
    private Label cost_field;

    public void add_to_order(ActionEvent actionEvent) {

        Button node = (Button)actionEvent.getSource();

        System.out.println("Added"); }

    public void confirm_order() { System.out.println("Confirmed"); }

    public void cancel_order() { System.out.println("Canceled"); }

    public void open_options() { System.out.println("options"); }

    public void open_filters() { System.out.println("filter"); }

}

/*        int i = 0;
        for (Node node: burger_grid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setText("Hello");
                node.setUserData(i);
                i++;
            }
        }*/