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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.teamsix.data.*;

import java.net.URL;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class StockScreenController implements Initializable {
    private List<UUID_Entity> stockList;
    private List<UUID_Entity> itemList;
    private List<UUID_Entity> orderList;
    private List<UUID_Entity> menuList;

    private Stage window;
    private Scene orderScene;

    private TableView<StockTableEntry> stockTable = new TableView<>();
    private TableView<ItemTableEntry> itemTable = new TableView<>();
    private TableView<OrderTableEntry> orderTable = new TableView<>();
    private TableView<MenuItemTableEntry> menuTable = new TableView<>();

    private ObservableList<StockTableEntry> stockEntries = FXCollections.observableArrayList();
    private ObservableList<ItemTableEntry> itemEntries = FXCollections.observableArrayList();
    private ObservableList<OrderTableEntry> observableOrders = FXCollections.observableArrayList();


    @FXML
    private StackPane stockTabPane;
    @FXML
    private StackPane itemTabPane;
    @FXML
    private StackPane menuTabPane;
    @FXML
    private StackPane orderTabPane;

    private FXMLLoader loader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshData();
        createPanes();
        // Add tables to panes in tabs
        itemTabPane.getChildren().addAll(itemTable);
        stockTabPane.getChildren().addAll(stockTable);
        orderTabPane.getChildren().addAll(orderTable);
        menuTabPane.getChildren().addAll(menuTable);
    }

    @FXML
    public void addItemAction(ActionEvent event ) {
        CreateItemController itemController = new CreateItemController(null);
        itemController.createNewWindow();
    }

    public void createAddStockInstanceDialog(Item_Ref item_ref) {
        try {
            StockInstanceDialogController controller = new StockInstanceDialogController(item_ref, this);
            FXMLLoader stockDialogLoader = new FXMLLoader(getClass().getResource("create_stock_instance.fxml"));
            stockDialogLoader.setController(controller);
            Stage stage = new Stage();
            controller.preSet(stage);
            Parent root = stockDialogLoader.load();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Stock");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (java.io.IOException e) {
            System.out.println("Failed to launch dialog: " + e);
        }
    }

    public void addStockInstance(StockInstance stockInstance) {
        StorageAccess.instance().updateStockInstance(stockInstance);
        refreshData();
    }

//    public void closePopup(ActionEvent event) throws IOException {
//        dialogPopup.hide();
//    }

    public void exportXML() {
        //TODO implement this
        System.out.println("EXPORT");
    }

    /**
     * Switches to order view
     */
    public void openOrderView() {
        System.out.println("Ordering");
        window.setScene(orderScene);
    }

    /**
     * Sets references to other scenes
     * @param primaryStage The root screen
     * @param orderScene Reference to order screen
     */
    public void preSet(Stage primaryStage, Scene orderScene) {
        this.window = primaryStage;
        this.orderScene = orderScene;
    }

    /**
     * Refreshes data for tables
     */
    public void refreshData() {
        DataQuery<StockInstance> stockQuery = new DataQuery<>(StockInstance.class);
        DataQuery<Item> itemQuery = new DataQuery<>(Item.class);
        stockList = stockQuery.runQuery();
        itemList = itemQuery.runQuery();

        DataQuery<Order> orderDataQuery = new DataQuery<>(Order.class);
        orderList = orderDataQuery.runQuery();
        System.out.print(orderList.size());
        getObservableOrderTableEntryList(observableOrders);
        getObservableStockTableEntryList(stockEntries);
        getObservableItemTableEntryList(itemEntries);
    }

    public void searchItems() {
        //TODO Implement search functionality
        refreshData();
    }

    /**
     * Runs table initialisation
     */
    private void createPanes() {
        createStockTable();
        createItemTable();
        createOrderTable();
    }

    /**
     * Creates stock table
     */
    private void createStockTable() {
        stockTable = new TableView<>();
        stockTable.setItems(stockEntries);

        //Add name column
        TableColumn<StockTableEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockTable.getColumns().add(nameColumn);

        //Add date added column
        TableColumn<StockTableEntry, String> dateAddedColumn = new TableColumn<>("Date Added");
        dateAddedColumn.setMinWidth(100);
        dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("date_added"));
        stockTable.getColumns().add(dateAddedColumn);

        //Add expiry date column
        TableColumn<StockTableEntry, String> dateExpiredColumn = new TableColumn<>("Expiry Date");
        dateExpiredColumn.setMinWidth(100);
        dateExpiredColumn.setCellValueFactory(new PropertyValueFactory<>("date_expires"));
        stockTable.getColumns().add(dateExpiredColumn);

        //Add quantity column
        TableColumn<StockTableEntry, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity_remaining"));
        stockTable.getColumns().add(quantityColumn);
    }

    /**
     * Creates item table
     */
    private void createItemTable() {
        itemTable = new TableView<>();
        itemTable.setItems(itemEntries);

        //Add name column
        TableColumn<ItemTableEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemTable.getColumns().add(nameColumn);

        //Add description column
        TableColumn<ItemTableEntry, String> descColumn = new TableColumn<>("Description");
        descColumn.setMinWidth(100);
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        itemTable.getColumns().add(descColumn);

        //Add base price column
        TableColumn<ItemTableEntry, String> basePriceColumn = new TableColumn<>("Cost Price");
        basePriceColumn.setMinWidth(100);
        basePriceColumn.setCellValueFactory(new PropertyValueFactory<>("base_price"));
        itemTable.getColumns().add(basePriceColumn);

        //Add markup price column
        TableColumn<ItemTableEntry, String> markUpColumn = new TableColumn<>("Sale Price");
        markUpColumn.setMinWidth(100);
        markUpColumn.setCellValueFactory(new PropertyValueFactory<>("markup_price"));
        itemTable.getColumns().add(markUpColumn);

        //Add unit column
        TableColumn<ItemTableEntry, String> unitColumn = new TableColumn<>("Qty units");
        unitColumn.setMinWidth(100);
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("qty_unit"));
        itemTable.getColumns().add(unitColumn);

        //Add button column
        TableColumn<ItemTableEntry, Button> addStockButtonColumn = new TableColumn<>();
        addStockButtonColumn.setMinWidth(100);
        addStockButtonColumn.setCellValueFactory(new PropertyValueFactory<>("addStockInstance"));
        itemTable.getColumns().add(addStockButtonColumn);

        //Add edit button column
        TableColumn<ItemTableEntry, Button> addEditButtonColumn = new TableColumn<>();
        addEditButtonColumn.setMinWidth(100);
        addEditButtonColumn.setCellValueFactory(new PropertyValueFactory<>("editItem"));
        itemTable.getColumns().add(addEditButtonColumn);
    }

    public void createOrderTable() {
        orderTable = new TableView<>();
        orderTable.setItems(observableOrders);

        TableColumn<OrderTableEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderTable.getColumns().add(dateColumn);


    }

    private void getObservableItemTableEntryList(ObservableList<ItemTableEntry> items) {
        items.clear();
        for (UUID_Entity entity: itemList) {
            Item item = StorageAccess.instance().getItem(new Item_Ref(entity));
            items.add(new ItemTableEntry(item, this));
        }
    }

    private void getObservableStockTableEntryList(ObservableList<StockTableEntry> stocks) {
        stocks.clear();
        for (UUID_Entity entity: stockList) {
            StockInstance stockInstance = StorageAccess.instance().getStockInstance(new StockInstance_Ref(entity));
            Item item = StorageAccess.instance().getItem(stockInstance.getStockItem());
            stocks.add(new StockTableEntry(stockInstance, item));
        }
    }

    private void getObservableOrderTableEntryList(ObservableList<OrderTableEntry> orderEntries) {
        orderEntries.clear();
        for (UUID_Entity entity: orderList) {
            Order order = StorageAccess.instance().getOrder(new Order_Ref(entity));
            orderEntries.add(new OrderTableEntry(order));
        }
    }

    private void getObservableMenuTableEntryList(ObservableList<MenuItem> menuEntries) {
        menuEntries.clear();
        for (UUID_Entity entity: menuList) {
            Menu menu = StorageAccess.instance().getMenu(new Menu_Ref(entity));
//            menuEntries.add(new MenuItemTableEntry(menu));
        }
    }

    public static class ItemTableEntry {
        private final Item_Ref item_ref;
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;
        private final SimpleStringProperty base_price;
        private final SimpleStringProperty markup_price;
        private final SimpleStringProperty qty_unit;
        private final Button addStockInstance;
        private final Button editItem;

        private ItemTableEntry(Item item, StockScreenController parent) {
            this.item_ref = item;
            this.name = new SimpleStringProperty(item.getName());
            this.description = new SimpleStringProperty(item.getDescription());
            this.base_price = new SimpleStringProperty(item.getBasePrice().toString());
            this.markup_price = new SimpleStringProperty(item.getMarkupPrice().toString());
            this.qty_unit = new SimpleStringProperty(item.getQtyUnit().toString());
            this.addStockInstance = new Button("Add Stock");
            this.addStockInstance.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    parent.createAddStockInstanceDialog(item_ref);
                }
            });
            this.editItem = new Button("Edit Item");
            this.editItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    CreateItemController itemController = new CreateItemController(item_ref);
                    itemController.createNewWindow();
                }
            });
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
        public Button getAddStockInstance() {
            return addStockInstance;
        }
        public Button getEditItem() {
            return editItem;
        }
    }

    public static class OrderTableEntry {
        private final SimpleStringProperty date;

        private OrderTableEntry(Order order) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            this.date = new SimpleStringProperty(df.format(order.getTimestamp()));
        }

        public String getDate() {
            return date.get();
        }
    }

    public static class MenuItemTableEntry {
        private final SimpleStringProperty name;
        private final SimpleStringProperty desc;

        private MenuItemTableEntry(Menu menu){
            name = new SimpleStringProperty(menu.getName());
            desc = new SimpleStringProperty(menu.getDescription());
        }

        public String getPrice() {
            return name.get();
        }

        public String getname() {
            return desc.get();
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
