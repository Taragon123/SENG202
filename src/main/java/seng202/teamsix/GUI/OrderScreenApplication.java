package seng202.teamsix.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.teamsix.data.Item_Ref;
import seng202.teamsix.managers.OrderManager;

import java.io.IOException;

public class OrderScreenApplication extends Application {

    private OrderManager orderManager = new OrderManager();

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        OrderScreenController orderController = new OrderScreenController();
        StockScreenController stockController = new StockScreenController();
        PastOrderScreenController pastOrderController = new PastOrderScreenController();

        FXMLLoader loaderOrder = new FXMLLoader(getClass().getResource("main_order_screen.fxml"));
        orderController.setOrderManager(orderManager);
        loaderOrder.setController(orderController);
        Parent orderParent = loaderOrder.load();
        Scene orderScene = new Scene(orderParent, 1300, 800);
        orderScene.setFill(Color.TRANSPARENT);

        FXMLLoader loaderOptions = new FXMLLoader(getClass().getResource("options_screen.fxml"));
        loaderOptions.setController(orderController);
        Parent pop = loaderOptions.load();
        orderController.optionPopup.getContent().add(pop);
        orderController.optionPopup.setAutoHide(true);

        FXMLLoader loadManagement = new FXMLLoader(getClass().getResource("stock_screen.fxml"));
        loadManagement.setController(stockController);
        Parent managementParent = loadManagement.load();
        Scene managementScene = new Scene(managementParent, 1300, 800);

        FXMLLoader loadPastOrderManagement = new FXMLLoader(getClass().getResource("past_order_screen.fxml"));
        loadPastOrderManagement.setController(pastOrderController);
        Parent pastOrderManagement = loadPastOrderManagement.load();
        Scene pastOrderScene = new Scene(pastOrderManagement, 1300, 800);

        orderController.preSet(primaryStage, managementScene);
        stockController.preSet(primaryStage, orderScene, pastOrderScene);

        CreateItemController itemController = new CreateItemController(new Item_Ref());
        itemController.createNewWindow();

        primaryStage.setTitle("FoodByte");
        primaryStage.setScene(orderScene);
        primaryStage.getIcons().add(new Image("file:assets/icons/icon.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}