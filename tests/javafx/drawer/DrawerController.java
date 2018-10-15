package javafx.drawer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

public class DrawerController {

    @FXML
    private JFXDrawer drawer;

    @FXML
    public void initialize(){
        AnchorPane anchorPane = new AnchorPane();
        JFXButton button = new JFXButton();
        button.setText("Close");
        button.setOnAction(this::closeDrawer);
        anchorPane.getChildren().add(button);
        drawer.setSidePane(anchorPane);
    }

    @FXML
    void closeDrawer(ActionEvent event) {
        drawer.close();
    }

    @FXML
    void openDrawer(ActionEvent event) {
        drawer.open();
    }
}
