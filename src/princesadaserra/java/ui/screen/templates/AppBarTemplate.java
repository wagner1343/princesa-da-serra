package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppBarTemplate extends AnchorPane {

    private final JFXHamburger menuButton;
    private final Text pageNameText;
    private String pageName;

    public AppBarTemplate(String pageName) {

        this.pageName = pageName;

        menuButton = new JFXHamburger();
        pageNameText = new Text();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(38.0);
        setPrefWidth(800.0);

        pageNameText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        pageNameText.setStrokeWidth(0.0);
        pageNameText.setText(pageName);
        pageNameText.setFont(new Font(18.0));

        menuButton.setPrefWidth(18d);
        menuButton.setPrefHeight(14d);
        menuButton.setLayoutX(12d);
        menuButton.setLayoutY(12d);
        pageNameText.setLayoutX(48d);
        pageNameText.setLayoutY(26d);

        getChildren().add(menuButton);
        getChildren().add(pageNameText);

        setStyle("-fx-background-color: green");

    }

    private void init(){
        pageNameText.setText(pageName);
    }
}
