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
    static Config config = AppContext.getInstance().getConfig();

    public static void setAllAnchorToZero(Node anchor){
        AnchorPane.setLeftAnchor(anchor, 0d);
        AnchorPane.setRightAnchor(anchor, 0d);
        AnchorPane.setTopAnchor(anchor, 0d);
        AnchorPane.setBottomAnchor(anchor, 0d);

    }

    public static void adjustToScreenSize(Region node){
        node.setPrefWidth(Double.parseDouble(AppContext.getInstance().getConfig().getValue(ConfigKeys.WINDOW_WIDTH)));
        node.setPrefHeight(Double.parseDouble(AppContext.getInstance().getConfig().getValue(ConfigKeys.WINDOW_HEIGHT)));
        node.setMinWidth(Double.parseDouble(AppContext.getInstance().getConfig().getValue(ConfigKeys.WINDOW_WIDTH)));
        node.setMinHeight(Double.parseDouble(AppContext.getInstance().getConfig().getValue(ConfigKeys.WINDOW_HEIGHT)));
        node.setMaxWidth(Double.parseDouble(AppContext.getInstance().getConfig().getValue(ConfigKeys.WINDOW_WIDTH)));
        node.setPrefHeight(Double.parseDouble(AppContext.getInstance().getConfig().getValue(ConfigKeys.WINDOW_HEIGHT)));

    }

    public static double getVerticalCenter(Node node){
        return node.getBoundsInLocal().getHeight() / 2;
    }

    public static void centerIntoScene(Node node){
        double centerX = Double.parseDouble(config.getValue(ConfigKeys.WINDOW_WIDTH))/2 - (node.getBoundsInLocal().getWidth()/2);
        double centerY = Double.parseDouble(config.getValue(ConfigKeys.WINDOW_HEIGHT))/2 - (node.getBoundsInLocal().getHeight()/2);

        node.setLayoutX(centerX);
        node.setLayoutY(centerY);

        System.out.println("Centering " + node + " to " + centerX + " " + centerY);
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
