package princesadaserra.java.ui.screen.util;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import princesadaserra.java.ui.SharedVisualProperties;

public class UnauthorizedView extends AnchorPane {
    private Text text;
    private UnauthorizedViewController controller;

    public UnauthorizedView(){
        System.out.println("UnauthorizedView()");
        this.controller = new UnauthorizedViewController(this);

        text = new Text("Unauthorized view");

        setBackground(new Background(new BackgroundFill(Color.web("red"), null, null)));

        getChildren().add(text);
        SharedVisualProperties.centerIntoScene(text);
        SharedVisualProperties.adjustToScreenSize(this);
        SharedVisualProperties.setAllAnchorToZero(this);

        init();
    }

    public void init(){

    }
}
