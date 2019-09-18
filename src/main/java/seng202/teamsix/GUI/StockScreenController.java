package seng202.teamsix.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import seng202.teamsix.data.StockInstance;
import seng202.teamsix.managers.StockManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class StockScreenController implements Initializable {
    private ArrayList<StockInstance> stocks = new ArrayList<StockInstance>();

    @FXML
    private GridPane itemPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }



    public void searchItems() {
        GridPane data = new ItemTableData().getGrid();
        itemPane.add(data, 0, 0);
    }
}
