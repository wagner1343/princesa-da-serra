package princesadaserra.java.ui.screen.trips;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import princesadaserra.java.ui.SharedVisualProperties;
import princesadaserra.java.ui.screen.components.TripSearchBox;


public class TripsViewSales extends AnchorPane{
    private Text text;
    private TripSearchBox tripSearchBox;
    private VBox page;

    public TripsViewSales(){
        setStyle("-fx-background-color: white");
        page = new VBox();

        setPrefHeight(544);
        setPrefWidth(800);

        page.setPrefHeight(544);
        page.setPrefWidth(800);
        AnchorPane.setLeftAnchor(page, 0d);
        AnchorPane.setRightAnchor(page, 0d);

        page.setAlignment(Pos.TOP_CENTER);

        tripSearchBox = new TripSearchBox();
        tripSearchBox.setLayoutX(88d);
        tripSearchBox.setLayoutY(88d);
        tripSearchBox.setEffect(SharedVisualProperties.getDropShadow());

        text = new Text("This is tripsView for Seller user");
        SharedVisualProperties.centerIntoScene(text);

        page.getChildren().add(text);
        page.getChildren().add(tripSearchBox);

        getChildren().add(page);
    }
}
