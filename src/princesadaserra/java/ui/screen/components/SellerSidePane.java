package princesadaserra.java.ui.screen.components;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class SellerSidePane extends StackPane {
    public SellerSidePane(){
        setPrefHeight(200);
        setPrefWidth(200);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);

        getChildren().add(new Text("Seller side pane"));
    }
}
