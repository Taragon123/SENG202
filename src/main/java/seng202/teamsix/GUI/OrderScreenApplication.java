package seng202.teamsix.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.teamsix.managers.OrderManager;

import java.io.IOException;

public class OrderScreenApplication extends Application {

    private OrderManager orderManager = new OrderManager();

    @Override
    public void start(Stage primaryStage) throws IOException {

        OrderScreenController orderController = new OrderScreenController();

        FXMLLoader loaderOrder = new FXMLLoader(getClass().getResource("main_order_screen.fxml"));
        OrderScreenController controller = new OrderScreenController();
        controller.setOrderManager(orderManager);
        loaderOrder.setController(orderController);
        Parent root = loaderOrder.load();

        FXMLLoader loaderOptions = new FXMLLoader(getClass().getResource("options_screen.fxml"));
        loaderOptions.setController(orderController);
        Parent pop = loaderOptions.load();
        orderController.optionPopup.getContent().add(pop);

        Parent managmentParent = FXMLLoader.load(getClass().getResource("stock_screen.fxml"));
        Scene managmentScene = new Scene(managmentParent, 1300, 800);

        orderController.preSet(primaryStage, managmentScene);

        primaryStage.setTitle("FoodByte");
        primaryStage.setScene(new Scene(root, 1300, 800));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}