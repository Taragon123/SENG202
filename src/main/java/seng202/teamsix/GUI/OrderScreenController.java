package seng202.teamsix.GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Name: OrderScreenController.java
 * Authors: Taran Jennison
 * Date: 07/09/2019
 */


public class OrderScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image logo = new Image("https://i.imgur.com/GzCtLGK.png");
        System.out.println("Image loading error? " + logo.isError());
        logo_view.setImage(logo);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
            date_time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private Label date_time;

    @FXML
    private GridPane main_grid;
    @FXML
    private ImageView logo_view;

    public void add_to_order() {
        System.out.println("Added");
        date_time.setText("9/09/2019 6:03PM");
    }

    public void confirm_order() {
        System.out.println("Confirmed");
    }

    public void cancel_order() {
        System.out.println("Canceled");
    }

    public void open_options() { System.out.println("options"); }

    public void open_filters() { System.out.println("filter"); }

}