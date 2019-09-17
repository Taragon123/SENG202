package seng202.teamsix.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StockScreenController implements Initializable {
    @FXML
    private ScrollPane scroll_screen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void searchItems() {
        scroll_screen.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }
}
