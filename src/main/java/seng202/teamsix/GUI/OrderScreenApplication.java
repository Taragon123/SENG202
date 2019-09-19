package seng202.teamsix.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

        FXMLLoader loadManagment = new FXMLLoader(getClass().getResource("stock_screen.fxml"));
        loadManagment.setController(stockController);
        Parent managmentParent = loadManagment.load();
        Scene managmentScene = new Scene(managmentParent, 1300, 800);

        orderController.preSet(primaryStage, managmentScene);
        stockController.preSet(primaryStage, orderScene);

        primaryStage.setTitle("FoodByte");
        primaryStage.setScene(orderScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}