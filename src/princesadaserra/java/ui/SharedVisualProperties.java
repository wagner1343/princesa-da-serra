package princesadaserra.java.ui;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import princesadaserra.java.util.config.Config;
import princesadaserra.java.util.config.ConfigKeys;
import princesadaserra.java.util.context.AppContext;

public class SharedVisualProperties {

    public static void setAllAnchorToZero(Node anchor){
        AnchorPane.setLeftAnchor(anchor, 0d);
        AnchorPane.setRightAnchor(anchor, 0d);
        AnchorPane.setTopAnchor(anchor, 0d);
        AnchorPane.setBottomAnchor(anchor, 0d);

    }

    public static void relativeCenter(Node parent, Node child){
        child.setLayoutX(parent.getBoundsInLocal().getWidth() / 2 - child.getBoundsInLocal().getWidth()/2);
        child.setLayoutY(parent.getBoundsInLocal().getHeight() / 2 - child.getBoundsInLocal().getHeight()/2);
    }

    public static double getVerticalCenter(Node node){
        return node.getBoundsInLocal().getHeight() / 2;
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
