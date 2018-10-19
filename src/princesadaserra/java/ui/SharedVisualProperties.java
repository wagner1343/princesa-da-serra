package princesadaserra.java.ui;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class SharedVisualProperties {
    public static void setAllAnchorToZero(Node anchor){
        AnchorPane.setLeftAnchor(anchor, 0d);
        AnchorPane.setRightAnchor(anchor, 0d);
        AnchorPane.setTopAnchor(anchor, 0d);
        AnchorPane.setBottomAnchor(anchor, 0d);

    }

    public static void centerIntoScene(Node node){
        double centerX = node.getScene().getWidth()/2;
        double centerY = node.getScene().getHeight()/2;

        node.setLayoutX(centerX);
        node.setLayoutY(centerY);
    }
    public static DropShadow getDropShadow() {
        return dropShadow;
    }

    public static Color getDropShadowColor() {
        return dropShadowColor;
    }

    private static Color dropShadowColor = Color.web("#00000036");

    public static Color getLightTextColor() {
        return lightTextColor;
    }

    private static Color lightTextColor = Color.web("white");
    private static DropShadow dropShadow = new DropShadow(5.55, 1.5, 0, dropShadowColor);

    public static Color getPrimaryColor() {
        return PrimaryColor;
    }

    public static void setPrimaryColor(Color primaryColor) {
        PrimaryColor = primaryColor;
    }

    private static Color PrimaryColor = Color.web("#8AE664");

}
