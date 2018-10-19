package princesadaserra.java.ui.screen.trips;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import princesadaserra.java.ui.SharedVisualProperties;


public class TripsViewSeller extends AnchorPane{
    private Text text;
    public TripsViewSeller(){
        text = new Text("This is tripsView for Seller user");
        SharedVisualProperties.centerIntoScene(text);
    }
}
