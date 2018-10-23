package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import princesadaserra.java.ui.SharedVisualProperties;

public class AppBarTemplate extends AnchorPane {
    private final double DEFAULT_PREF_HEIGHT = 56;
    private final double DEFAULT_PREF_WIDTH = 800;
    private final double DEFAULT_PAGE_FONT_SIZE = 20;


    public BurguerMenuButton getButton() {
        return button;
    }

    private final BurguerMenuButton button;
    private final Text pageNameText;

    public AppBarTemplate(String pageName, double height) {
        this.getStylesheets().add("/view/templates/AppBarTemplate.css");
        this.getStyleClass().add("AppBarRoot");

        setPrefHeight(height);

        button = new BurguerMenuButton();
        pageNameText = new Text(pageName);

        Font font = new Font(DEFAULT_PAGE_FONT_SIZE);

        pageNameText.getStyleClass().add("AppBarTitle");
        pageNameText.setLayoutX(72d);
        pageNameText.setLayoutY((getPrefHeight()/2) + DEFAULT_PAGE_FONT_SIZE/2 - 2);
        pageNameText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        pageNameText.setStrokeWidth(0.0);
        pageNameText.setText(pageName);
        pageNameText.setFont(font);
        pageNameText.setFill(SharedVisualProperties.getLightTextColor());

        button.setPrefWidth(24d);
        button.setPrefHeight(19d);
        button.setLayoutY(15);
        button.setLayoutX(8);

        getChildren().add(button);
        getChildren().add(pageNameText);

        this.setEffect(SharedVisualProperties.getDropShadow());
    }

    private void init(){
    }
}
