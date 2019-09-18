package seng202.teamsix.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import seng202.teamsix.data.StockInstance;

import java.awt.*;

public class ItemTableData {
    private GridPane root;
    public ItemTableData() {
        GridPane grid = new GridPane();
        Button editBtn = new Button("Edit");
        grid.add(editBtn, 0, 0);
        root = grid;
    }

    public void initialise() {

    }

    public GridPane getGrid() {
        return root;
    }
}
