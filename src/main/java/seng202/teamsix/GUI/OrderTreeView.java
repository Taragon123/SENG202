package seng202.teamsix.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import seng202.teamsix.data.Item;
import seng202.teamsix.data.OrderItem;
import seng202.teamsix.data.StorageAccess;

public class OrderTreeView extends TreeView<OrderItem> {

    OrderTreeView(OrderItem root_order) {
        setCellFactory(e -> new OrderTreeCell());

        TreeItem<OrderItem> root = createTreeFromOrderItem(root_order);
        root.setExpanded(true);

        this.setRoot(root);
        this.setShowRoot(true);
    }

    private TreeItem<OrderItem> createTreeFromOrderItem(OrderItem root_order) {
        TreeItem<OrderItem> root = new TreeItem<>(root_order);
        for(OrderItem dependant_order : root_order.getDependants()) {
            root.getChildren().add(createTreeFromOrderItem(dependant_order));
        }
        return root;
    }
}

class OrderTreeCell extends TreeCell<OrderItem> {

    @Override
    protected void updateItem(OrderItem order_item, boolean empty) {
        super.updateItem(order_item, empty);


        // If the cell is empty we don't show anything.
        if (isEmpty()) {
            setGraphic(null);
            setText(null);
        } else {
            Item item = StorageAccess.instance().getItem(order_item.getItem());

            Label  label = new Label(item.getName() + " x " + item.getQuantity());

            Button addQuantityButton = new Button("+");
            addQuantityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    order_item.setQuantity(order_item.getQuantity() + 1);
                }
            });

            Button subQuantityButton = new Button("-");
            subQuantityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    order_item.setQuantity(order_item.getQuantity() - 1);
                }
            });

            HBox buttonHBox = new HBox(5);
            buttonHBox.setAlignment(Pos.CENTER_RIGHT);
            buttonHBox.getChildren().addAll(subQuantityButton, addQuantityButton);

            BorderPane cellPane = new BorderPane();
            cellPane.setLeft(label);
            cellPane.setRight(buttonHBox);

            setGraphic(cellPane);
            setText(null);
        }
    }
}