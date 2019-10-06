package seng202.teamsix.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seng202.teamsix.data.Order;
import seng202.teamsix.data.OrderItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomOrderController implements Initializable {
    private Stage controller_window;
    private EventHandler<ActionEvent> callback = null;
    private OrderItem modifying_order;

    @FXML
    private AnchorPane anchorpane_order;

    @FXML
    private Button button_cancel;

    @FXML
    private Button button_confirm;

    CustomOrderController(OrderItem order) {
        modifying_order = order;
    }

    void createNewWindow(EventHandler<ActionEvent> callback_handler) {
        FXMLLoader loaderCreateItem = new FXMLLoader(getClass().getResource("custom_order.fxml"));
        loaderCreateItem.setController(this);

        // If it cannot load fxml the function exits without creating window
        Parent parentCreateItem = null;
        try {
            parentCreateItem = loaderCreateItem.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene root = new Scene(parentCreateItem);
        controller_window = new Stage();
        callback = callback_handler;

        controller_window.setScene(root);
        controller_window.getIcons().add(new Image("file:assets/icons/icon.png"));
        controller_window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //anchorpane_order.getChildren()add();
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        controller_window.close();
    }

    @FXML
    void confirmClicked(ActionEvent event) {
        // Quit window
        controller_window.close();
        callback.handle(new ActionEvent());
    }
}
