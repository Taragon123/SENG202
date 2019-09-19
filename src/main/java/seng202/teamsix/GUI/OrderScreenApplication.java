package seng202.teamsix.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderScreenApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loaderOrder = new FXMLLoader(getClass().getResource("main_order_screen.fxml"));
        OrderScreenController controller = new OrderScreenController();
        loaderOrder.setController(controller);
        Parent root = loaderOrder.load();

        FXMLLoader loaderOptions = new FXMLLoader(getClass().getResource("options_screen.fxml"));
        loaderOptions.setController(controller);
        Parent pop = loaderOptions.load();
        controller.optionPopup.getContent().add(pop);

        primaryStage.setTitle("FoodByte");
        primaryStage.setScene(new Scene(root, 1300, 800));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}