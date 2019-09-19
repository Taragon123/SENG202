package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seng202.teamsix.data.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class StockScreenController implements Initializable {
    private List<UUID_Entity> stockList;
    private List<UUID_Entity> itemList;

    @FXML
    private GridPane stockPane;
    @FXML
    private Button addBtn;
    @FXML
    private GridPane itemPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private GridPane createStockDisplay(UUID_Entity stockInstanceUUID) {
        StockInstance stockInstance = StorageAccess.instance().getStockInstance(new StockInstance_Ref(stockInstanceUUID));
        Item item = StorageAccess.instance().getItem(stockInstance.getStockItem());

        //Creating labels
        System.out.println(item.getName());
        Label name_lbl = new Label("Item name: " + item.getName());
        Label date_added_lbl = new Label("Date added: " + stockInstance.getDateAdded().toString());
        Label quantity_remaining_lbl = new Label("Quantity remaining: " + (int)stockInstance.getQuantityRemaining());
        Label date_expires_lbl = new Label("Expires: ");
        if (stockInstance.getDoesExpire()) {
            date_expires_lbl.setText(date_expires_lbl.getText() + stockInstance.getDateExpired().toString());
        } else {
            date_expires_lbl.setText(date_expires_lbl.getText() + "N/A");
        }

        //Creating buttons
        Button adjustStockBtn = new Button("Adjust");

        //Adding event handlers for buttons
        adjustStockBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBtn.setText("frvrfr");
            }
        });

        //Creating grid and adding components to grid
        final GridPane grid = new GridPane();

        grid.setGridLinesVisible(true);
        grid.add(name_lbl, 0, 0);
        grid.add(quantity_remaining_lbl, 0, 1);
        grid.add(date_added_lbl, 1, 0);
        grid.add(date_expires_lbl, 1, 1);
        grid.add(adjustStockBtn, 2, 0);
        adjustStockBtn.setMaxHeight(Double.MAX_VALUE);
        return grid;
    }

    private GridPane createItemDisplay(UUID_Entity item) {
        Label name = new Label("Name");
        Label description = new Label("Description");
        Label price = new Label("Price");

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBtn.setText(String.format("this is edit btn num %d"));
            }
        });

        Button adjustStockBtn = new Button("Adjust Stock");
        adjustStockBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBtn.setText(String.format("this is adjust btn num %d"));
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

    public void populateLists() {
        stockPane.getChildren().clear();
        for (int i = 0; i < stockList.size(); i++) {
            GridPane grid = createStockDisplay(stockList.get(i));
            stockPane.add(grid, 0, i);
        }
        for (int i = 0; i < itemList.size(); i++) {
            GridPane grid = createItemDisplay(itemList.get(i));
            itemPane.add(grid, 0, i);
        }
    }



    public void refreshPanes() {
        populateLists();
    }

    public void addStock() {}

    public void updateStock() {}

    public void searchItems() {
        DataQuery<StockInstance> stockQuery = new DataQuery<>(StockInstance.class);
        DataQuery<Item> itemQuery = new DataQuery<>(Item.class);
        stockList = stockQuery.runQuery();
        itemList = itemQuery.runQuery();
        refreshPanes();
    }
}
