package seng202.teamsix.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import seng202.teamsix.data.StockInstance;
import seng202.teamsix.managers.StockManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class StockScreenController implements Initializable {
    private int[] stocks = {1, 3, 4};


    @FXML
    private GridPane itemPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public GridPane createItemDisplay(int i) {
        GridPane grid = new GridPane();
        Label name = new Label("Hello there");
        Button editBtn = new Button(String.valueOf(stocks[i]));
        grid.add(editBtn, 0, 0);
        grid.add(name, 1, 0);
        return grid;
    }

    public void populateList() {
        itemPane.getChildren().clear();
        for (int i = 0; i < stocks.length; i++) {
            GridPane grid = createItemDisplay(i);
            itemPane.add(grid, 0, i);
        }
    }

    public void refreshScrollPane() {}

    public void addStock() {}

    public void updateStock() {}

    public void searchItems() {
        populateList();
//        GridPane data = new ItemTableData().getGrid();
//        itemPane.add(data, 0, 0);
    }
}
