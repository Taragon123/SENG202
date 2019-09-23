package seng202.teamsix.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.teamsix.data.Menu;
import seng202.teamsix.data.Menu_Ref;
import seng202.teamsix.data.StorageAccess;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMenu implements Initializable, CustomDialogInterface {
    private Menu menu;
    private Stage window;
    private boolean isNew;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private Label titleLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isNew) {
            titleLbl.setText("Add Menu");
        } else {
            titleLbl.setText("Edit Menu");
            nameInput.setText(menu.getName());
            descriptionInput.setText(menu.getDescription());
        }
    }

    public EditMenu(Menu_Ref menu_ref) {
        if (menu_ref == null) {
            menu = new Menu();
            isNew = true;
        } else {
            menu = StorageAccess.instance().getMenu(menu_ref);
            isNew = false;
        }
    }

    public void cancel() {
        window.close();
    }

    public void confirm() {
        if (nameInput.getText().length() > 0) {
            menu.setName(nameInput.getText());
            menu.setDescription(descriptionInput.getText());
            StorageAccess.instance().updateMenu(menu);
            window.close();
        }
    }

    public void preSet(Stage window) {
        this.window = window;
    }
}
