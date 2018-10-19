package princesadaserra.java.ui.screen.util;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import princesadaserra.java.ui.SharedVisualProperties;

public class UnauthorizedView extends AnchorPane {
    private Text text;

    public UnauthorizedView(){
        text = new Text("Unauthorized view");
        text.setLayoutY(getScene().getHeight()/2);
        text.setLayoutX(getScene().getWidth()/2);

        SharedVisualProperties.setAllAnchorToZero(this);
    }
}
