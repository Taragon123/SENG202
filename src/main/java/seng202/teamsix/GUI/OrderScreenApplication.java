package seng202.teamsix.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.teamsix.managers.OrderManager;

import java.io.IOException;

public class OrderScreenApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_order_screen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1300, 800));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}