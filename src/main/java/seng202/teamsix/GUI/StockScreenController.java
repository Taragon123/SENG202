package seng202.teamsix.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seng202.teamsix.data.*;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class StockScreenController implements Initializable {
    private List<UUID_Entity> stockList;
    private List<UUID_Entity> itemList;
    private Stage window;
    private Scene orderScene;

    @FXML
    private GridPane root;
    @FXML
    private StackPane stockTabPane;
    @FXML
    private StackPane itemTabPane;
    @FXML
    private Button addBtn;
    @FXML
    private Button switchToOrderViewBtn;

    //public Popup dialogPopup = new Popup();

    private FXMLLoader loader;

    @FXML
    public void addItemAction(ActionEvent event ) {
        try {
            loader = new FXMLLoader(getClass().getResource("dialogBox.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Item to be Added");
            stage.setScene(new Scene(root1));
            //dialogPopup.show();
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }

//    public void closePopup(ActionEvent event) throws IOException {
//        dialogPopup.hide();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshData();
        refreshPanes();
    }

    public void openOrderView() {
        System.out.println("Ordering");
        window.setScene(orderScene);
    }

    public void preSet(Stage primaryStage, Scene orderScene) {
        this.window = primaryStage;
        this.orderScene = orderScene;
    }

    public void refreshData() {
        DataQuery<StockInstance> stockQuery = new DataQuery<>(StockInstance.class);
        DataQuery<Item> itemQuery = new DataQuery<>(Item.class);
        stockList = stockQuery.runQuery();
        itemList = itemQuery.runQuery();
    }

    public void refreshPanes() {
        createStockTable();
        createItemTable();
    }

    public void createStockTable() {
        TableView<StockTableEntry> table = new TableView<>();
        table.setItems(getObservableStockTableEntryList());

        //Add name column
        TableColumn<StockTableEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        //Add date added column
        TableColumn<StockTableEntry, String> dateAddedColumn = new TableColumn<>("Date Added");
        dateAddedColumn.setMinWidth(100);
        dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("date_added"));
        table.getColumns().add(dateAddedColumn);

        //Add expiry date column
        TableColumn<StockTableEntry, String> dateExpiredColumn = new TableColumn<>("Expiry Date");
        dateExpiredColumn.setMinWidth(100);
        dateExpiredColumn.setCellValueFactory(new PropertyValueFactory<>("date_expires"));
        table.getColumns().add(dateExpiredColumn);

        //Add quantity column
        TableColumn<StockTableEntry, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity_remaining"));
        table.getColumns().add(quantityColumn);

        stockTabPane.getChildren().addAll(table);
    }

    public void createItemTable() {
        TableView<ItemTableEntry> table = new TableView<>();
        table.setItems(getObservableItemTableEntryList());

        //Add name column
        TableColumn<ItemTableEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        //Add description column
        TableColumn<ItemTableEntry, String> descColumn = new TableColumn<>("Description");
        descColumn.setMinWidth(100);
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.getColumns().add(descColumn);

        //Add base price column
        TableColumn<ItemTableEntry, String> basePriceColumn = new TableColumn<>("Cost Price");
        basePriceColumn.setMinWidth(100);
        basePriceColumn.setCellValueFactory(new PropertyValueFactory<>("base_price"));
        table.getColumns().add(basePriceColumn);

        //Add markup price column
        TableColumn<ItemTableEntry, String> markUpColumn = new TableColumn<>("Sale Price");
        markUpColumn.setMinWidth(100);
        markUpColumn.setCellValueFactory(new PropertyValueFactory<>("markup_price"));
        table.getColumns().add(markUpColumn);

        //Add description column
        TableColumn<ItemTableEntry, String> unitColumn = new TableColumn<>("Qty units");
        unitColumn.setMinWidth(100);
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("qty_unit"));
        table.getColumns().add(unitColumn);

        itemTabPane.getChildren().addAll(table);
    }




    public void addStock() {}

    public void updateStock() {}

    public ObservableList<ItemTableEntry> getObservableItemTableEntryList() {
        ObservableList<ItemTableEntry> items = FXCollections.observableArrayList();
        for (UUID_Entity entity: itemList) {
            Item item = StorageAccess.instance().getItem(new Item_Ref(entity));
            items.add(new ItemTableEntry(item));
        }
        return items;
    }

    public ObservableList<StockTableEntry> getObservableStockTableEntryList() {
        ObservableList<StockTableEntry> stocks = FXCollections.observableArrayList();
        for (UUID_Entity entity: stockList) {
            StockInstance stockInstance = StorageAccess.instance().getStockInstance(new StockInstance_Ref(entity));
            Item item = StorageAccess.instance().getItem(stockInstance.getStockItem());
            stocks.add(new StockTableEntry(stockInstance, item));
        }
        return stocks;
    }

    public void searchItems() {
        //TODO Implement search functionality
    }

    public static class ItemTableEntry {
        private final Item_Ref item_ref;
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;
        private final SimpleStringProperty base_price;
        private final SimpleStringProperty markup_price;
        private final SimpleStringProperty qty_unit;

        private ItemTableEntry(Item item) {
            this.item_ref = item;
            this.name = new SimpleStringProperty(item.getName());
            this.description = new SimpleStringProperty(item.getDescription());
            this.base_price = new SimpleStringProperty(item.getBasePrice().toString());
            this.markup_price = new SimpleStringProperty(item.getMarkupPrice().toString());
            this.qty_unit = new SimpleStringProperty(item.getQtyUnit().toString());
        }

        public String getName() {
            return name.get();
        }
        public String getDescription() {
            return description.get();
        }
        public String getBase_price() {
            return base_price.get();
        }
        public String getMarkup_price() {
            return markup_price.get();
        }
        public String getQty_unit() {
            return qty_unit.get();
        }
        public Item_Ref getItem_ref() {
            return item_ref;
        }
    }

    public static class StockTableEntry {
        private final StockInstance_Ref stockInstance_ref;
        private final SimpleStringProperty name;
        private final SimpleStringProperty date_added;
        private final SimpleStringProperty date_expires;
        private final SimpleStringProperty quantity_remaining;

        private StockTableEntry(StockInstance stockInstance, Item item) {
            this.stockInstance_ref = stockInstance;
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

            this.name = new SimpleStringProperty(item.getName());
            this.date_added = new SimpleStringProperty(df.format(stockInstance.getDateAdded()));
            if (stockInstance.getDoesExpire()) {
                this.date_expires = new SimpleStringProperty(df.format(stockInstance.getDateExpired()));
            } else {
                date_expires = new SimpleStringProperty("N/A");
            }
            this.quantity_remaining = new SimpleStringProperty("" + stockInstance.getQuantityRemaining());
        }
        public String getName() {
            return name.get();
        }
        public String getDate_added() {
            return date_added.get();
        }
        public String getDate_expires() {
            return date_expires.get();
        }
        public String getQuantity_remaining() {
            return quantity_remaining.get();
        }
        public StockInstance_Ref getStockInstance_ref() {
            return stockInstance_ref;
        }
    }
}
