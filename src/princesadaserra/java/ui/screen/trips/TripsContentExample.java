package princesadaserra.java.ui.screen.trips;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TripsContentExample extends VBox {
    private final Rectangle rectangle;

    public TripsContentExample() {

        rectangle = new Rectangle();

        setAlignment(javafx.geometry.Pos.TOP_CENTER);

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setHeight(301.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(420.0);

        getChildren().add(rectangle);

        setStyle("-fx-background-color: purple");
    }
}
