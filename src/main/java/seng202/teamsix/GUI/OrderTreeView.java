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
import seng202.teamsix.data.VariantItem;

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

        Item item = StorageAccess.instance().getItem(root_order.getItem());
        if(!(item instanceof VariantItem)) {
            for (OrderItem dependant_order : root_order.getDependants()) {
                root.getChildren().add(createTreeFromOrderItem(dependant_order));
            }
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
            Button addQuantityButton = new Button("+");
            addQuantityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    order_item.setQuantity(order_item.getQuantity() + 1);
                    getTreeView().refresh();
                }
            });

            Button subQuantityButton = new Button("-");
            subQuantityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if ((getTreeItem().getParent() == null && order_item.getQuantity() > 1) || getTreeItem().getParent() != null){
                        order_item.setQuantity(order_item.getQuantity() - 1);
                    }


                    if (order_item.getQuantity() == 0) {
                        if(order_item.getParent() != null) {
                            order_item.getParent().removeFromOrder(order_item);
                        }
                        if(getTreeItem().getParent() != null) {
                            getTreeItem().getParent().getChildren().remove(getTreeItem());
                        }

                    }
                    getTreeView().refresh();
                }
            });

            HBox buttonHBox = new HBox(5);
            buttonHBox.setAlignment(Pos.CENTER_RIGHT);

            Label label = null;

            Item item = StorageAccess.instance().getItem(order_item.getItem());
            if(item instanceof VariantItem) {
                OrderItem current_variant_order = order_item.getDependants().get(0);
                Item variant_item = StorageAccess.instance().getItem(current_variant_order.getItem());

                label = new Label(variant_item.getName() + " x " + order_item.getQuantity());

                Button nextVariantButton = new Button(">");
                nextVariantButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        order_item.swapWithNextVariant();
                        getTreeView().refresh();
                    }
                });
                Button prevVariantButton = new Button("<");
                prevVariantButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        order_item.swapWithPrevVariant();
                        getTreeView().refresh();
                    }
                });
                buttonHBox.getChildren().addAll(prevVariantButton, nextVariantButton);
            }else{
                label = new Label(item.getName() + " x " + order_item.getQuantity());

            }

            buttonHBox.getChildren().addAll(subQuantityButton, addQuantityButton);


            BorderPane cellPane = new BorderPane();
            cellPane.setLeft(label);
            cellPane.setRight(buttonHBox);

            setGraphic(cellPane);
            setText(null);
        }
    }
}