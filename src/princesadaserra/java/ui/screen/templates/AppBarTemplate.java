package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.AnchorPane;
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
        setPrefHeight(55.0);
        setPrefWidth(800.0);

        AnchorPane.setLeftAnchor(menuButton, 24.0);
        AnchorPane.setTopAnchor(menuButton, 23.0);
        menuButton.setLayoutX(24.0);
        menuButton.setLayoutY(23.0);

        AnchorPane.setLeftAnchor(pageNameText, 76.0);
        AnchorPane.setTopAnchor(pageNameText, 12.1015625);
        pageNameText.setLayoutX(76.0);
        pageNameText.setLayoutY(38.0);
        pageNameText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        pageNameText.setStrokeWidth(0.0);
        pageNameText.setText(pageName);
        pageNameText.setFont(new Font(24.0));

        getChildren().add(menuButton);
        getChildren().add(pageNameText);
        setStyle("-fx-background-color: green");

    }

    private void init(){
        pageNameText.setText(pageName);
    }
}
