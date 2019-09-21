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
    private List<UUID_Entity> orders;
    private TableView<OrderTableEntry> orderTable;
    @FXML
    private StackPane orderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshData();
        refreshPanes();
        orderPane.getChildren().addAll(orderTable);
    }

    /**
     * Refresh data for table
     */
    public void refreshData() {
        DataQuery<Order> orderDataQuery = new DataQuery<>(Order.class);
        orders = orderDataQuery.runQuery();
    }

    /**
     * Refreshes table
     */
    public void refreshPanes() {
        createTable();
    }

    /**
     * Creates table columns
     */
    public void createTable() {
        orderTable = new TableView<>();
        orderTable.setItems(getObservableOrderTableEntryList());

        TableColumn<OrderTableEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderTable.getColumns().add(dateColumn);


    }

    /**
     * Creates observable list of OrderTableEntry items for table
     * @return observable list of type OrderTableEntry
     */
    public ObservableList<OrderTableEntry> getObservableOrderTableEntryList() {
        ObservableList<OrderTableEntry> observableOrders = FXCollections.observableArrayList();
        for (UUID_Entity entity: orders) {
            Order order = StorageAccess.instance().getOrder(new Order_Ref(entity));
            observableOrders.add(new OrderTableEntry(order));
        }
        return observableOrders;
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
