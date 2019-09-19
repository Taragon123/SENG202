package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seng202.teamsix.data.DataQuery;
import seng202.teamsix.data.StockInstance;
import seng202.teamsix.data.UUID_Entity;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class StockScreenController implements Initializable {
    private List<UUID_Entity> stockitems;


    @FXML
    private GridPane itemPane;

    @FXML
    private Button addBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        GridPane oi = new GridPane();
//        oi.getColumnConstraints().clear();
////        oi.getColumnConstraints().addAll(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
//        oi.setGridLinesVisible(true);
//        itemPane.add(oi, 0, 0);
//        oi.add(new Button(), 0, 0);
//        oi.add(new Button(), 1, 0);
//        oi.add(new Button(), 1, 1);

    }

    public GridPane createItemDisplay(int i) {

        Label name = new Label("Name");
        Label description = new Label("Description");
        Label price = new Label("Price");

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBtn.setText(String.format("this is edit btn num %d", i));
            }
        });

        Button adjustStockBtn = new Button("Adjust Stock");
        adjustStockBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBtn.setText(String.format("this is adjust btn num %d", i));
            }
        });

        final GridPane grid = new GridPane();
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        grid.setGridLinesVisible(true);
        grid.add(name, 0, 0);
        grid.add(description, 1, 0);
        grid.add(price, 0, 1);
        grid.add(editBtn, 2, 0);
        grid.add(adjustStockBtn, 2, 1);

        return grid;
    }

    public void populateList() {
        itemPane.getChildren().clear();
        for (int i = 0; i < 5; i++) {
            GridPane grid = createItemDisplay(i);
            itemPane.add(grid, 0, i);
        }
    }

    public void refreshScrollPane() {}

    public void addStock() {}

    public void updateStock() {}

    public void searchItems() {
        DataQuery<StockInstance> query = new DataQuery<>(StockInstance.class);
        stockitems = query.runQuery();
        System.out.println(stockitems.size());
        populateList();
//        GridPane data = new ItemTableData().getGrid();
//        itemPane.add(data, 0, 0);
    }
}
