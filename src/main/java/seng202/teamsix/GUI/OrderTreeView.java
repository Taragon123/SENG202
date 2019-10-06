package seng202.teamsix.GUI;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import seng202.teamsix.data.OrderItem;
import seng202.teamsix.data.StorageAccess;

public class OrderTreeView extends TreeView<String> {

    OrderTreeView(OrderItem root_order) {
        TreeItem<String> root = createTreeFromOrderItem(root_order);
        root.setExpanded(true);

        this.setRoot(root);
        this.setShowRoot(true);
    }

    private TreeItem<String> createTreeFromOrderItem(OrderItem root_order) {
        TreeItem<String> root = new TreeItem<>(StorageAccess.instance().getItem(root_order.getItem()).getName());

        for(OrderItem dependant_order : root_order.getDependants()) {
            root.getChildren().add(createTreeFromOrderItem(dependant_order));
        }

        return root;
    }
}