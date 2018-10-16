package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXDrawersStack;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainAppTemplate extends VBox {

    private VBox content;
    private String pageName;
    private AppBarTemplate appBar;
    private DrawerStackExample example;
    public MainAppTemplate( VBox content, String pageName) {
        this.content = content;
        this.pageName = pageName;

        appBar = new AppBarTemplate(pageName);
        example = new DrawerStackExample();

        setAlignment(Pos.TOP_CENTER);

        setOnMouseClicked((event -> {
            example.getDrawer().toggle();
            System.out.println("Toggle");
        }));
        getChildren().add(appBar);
        getChildren().add(content);
        init();
    }

    private void init(){

    }
}
