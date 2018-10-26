package princesadaserra.java.ui.views.components.button;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MaterialIconButton extends AnchorPane {
    private Text textArea;
    private AnchorPane fadeBackground;
    private FadeTransition fadeTransition;
    private JFXButton button;
    private double topPadding = 16d;
    private double bottomPadding = 8d;
    private double leftPadding = 54d;
    private double rightPadding = 16d;

    public MaterialIconButton() {
        this.textArea = new Text();
        init();
    }

    public MaterialIconButton(String text) {
        this.textArea = new Text(text);
        init();
    }

    private void init() {
        getStyleClass().add("-pfx-material-button");

        button = new JFXButton();
        fadeBackground = new AnchorPane();
        fadeBackground.setStyle("-fx-background-color: #000");
        fadeTransition = new FadeTransition(new Duration(300), fadeBackground);

        textArea.setFont(new Font("Segoe-ui", 16));

        fadeTransition.setFromValue(0d);
        fadeTransition.setToValue(0.1);

        button.setStyle("-fx-background-color: #0000");
        AnchorPane.setRightAnchor(button, 0d);
        AnchorPane.setLeftAnchor(button, 0d);
        AnchorPane.setTopAnchor(button, 0d);
        AnchorPane.setBottomAnchor(button, 0d);
        fadeBackground.setOpacity(0d);
        AnchorPane.setRightAnchor(fadeBackground, 0d);
        AnchorPane.setLeftAnchor(fadeBackground, 0d);
        AnchorPane.setTopAnchor(fadeBackground, 0d);
        AnchorPane.setBottomAnchor(fadeBackground, 0d);

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            fadeTransition.setRate(1);
            fadeTransition.play();
        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            fadeTransition.setRate(-1);
            fadeTransition.play();
        });

        getChildren().add(fadeBackground);
        getChildren().add(textArea);
        getChildren().add(button);
        updateAnchors();
    }

    public JFXButton getButton() {
        return button;
    }

    public Text getTextArea() {
        return textArea;
    }

    public void setTextArea(Text textArea) {
        this.textArea = textArea;
        updateAnchors();
    }

    public double getTopPadding() {
        return topPadding;
    }

    public void setTopPadding(double topPadding) {
        this.topPadding = topPadding;
        updateAnchors();
    }

    public double getBottomPadding() {
        return bottomPadding;
    }

    public void setBottomPadding(double bottomPadding) {
        this.bottomPadding = bottomPadding;
        updateAnchors();
    }

    public double getLeftPadding() {
        return leftPadding;
    }

    public void setLeftPadding(double leftPadding) {
        this.leftPadding = leftPadding;
        updateAnchors();
    }

    public double getRightPadding() {
        return rightPadding;
    }

    public void setRightPadding(double rightPadding) {
        this.rightPadding = rightPadding;
        updateAnchors();
    }

    private void updateAnchors() {
        AnchorPane.setRightAnchor(textArea, rightPadding);
        AnchorPane.setLeftAnchor(textArea, leftPadding);
        AnchorPane.setTopAnchor(textArea, topPadding);
        AnchorPane.setBottomAnchor(textArea, bottomPadding);

        setPrefHeight(bottomPadding + topPadding + textArea.getFont().getSize() * 1.2);
        setPrefWidth(leftPadding + rightPadding + textArea.getBoundsInParent().getWidth());
    }
}
