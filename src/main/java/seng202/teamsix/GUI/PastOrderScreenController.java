package seng202.teamsix.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import seng202.teamsix.data.*;

import java.net.URL;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class PastOrderScreenController implements Initializable {
    private List<UUID_Entity> orderUUIDList;
    private TableView<OrderTableEntry> orderTable;
    private ObservableList<OrderTableEntry> observableOrders = FXCollections.observableArrayList();

    @FXML
    private StackPane orderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshData();
        createPanes();
        orderPane.getChildren().addAll(orderTable);
    }

    /**
     * Refresh data for table
     */
    public void refreshData() {
        DataQuery<Order> orderDataQuery = new DataQuery<>(Order.class);
        orderUUIDList = orderDataQuery.runQuery();
        getObservableOrderTableEntryList(observableOrders);
    }

    /**
     * Refreshes table
     */
    public void createPanes() {
        createTable();
    }

    /**
     * Creates table columns
     */
    public void createTable() {
        orderTable = new TableView<>();
        orderTable.setItems(observableOrders);

        TableColumn<OrderTableEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderTable.getColumns().add(dateColumn);


    }

    /**
     * Creates observable list of OrderTableEntry items for table
     */
    private void getObservableOrderTableEntryList(ObservableList<OrderTableEntry> orderEntries) {
        orderEntries.removeAll();
        for (UUID_Entity entity: orderUUIDList) {
            Order order = StorageAccess.instance().getOrder(new Order_Ref(entity));
            orderEntries.add(new OrderTableEntry(order));
        }
    }

    /**
     * Class for Order table entries
     */
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
}