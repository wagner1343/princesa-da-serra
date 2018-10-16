package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXDrawersStack;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainAppTemplate extends AnchorPane {

    private VBox content;
    private String pageName;
    private AppBarTemplate appBar;
    private DrawerStackExample example;
    public MainAppTemplate( VBox content, String pageName) {
        this.content = content;
        this.pageName = pageName;

        appBar = new AppBarTemplate(pageName);
        example = new DrawerStackExample();

        AnchorPane.setLeftAnchor(appBar, 0d);
        AnchorPane.setTopAnchor(appBar, 0d);
        AnchorPane.setRightAnchor(appBar, 0d);

        AnchorPane.setLeftAnchor(content, 0d);
        AnchorPane.setBottomAnchor(content, 0d);
        AnchorPane.setRightAnchor(content, 0d);
        AnchorPane.setTopAnchor(content, appBar.getPrefHeight());
        content.setLayoutY(appBar.getPrefHeight());
        System.out.println("appbarHeight: " + appBar.getPrefHeight());

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
