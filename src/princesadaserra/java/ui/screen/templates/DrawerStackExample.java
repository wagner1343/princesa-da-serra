package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.scene.layout.StackPane;

public class DrawerStackExample extends JFXDrawersStack {
    public JFXDrawer getDrawer() {
        return drawer;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    private JFXDrawer drawer;
    private StackPane pane;

    public DrawerStackExample(){
        pane = new StackPane();
        pane.setPrefWidth(100);
        pane.setPrefHeight(800);
        pane.setMinHeight(800);
        pane.setStyle("-fx-background-color: red");
        drawer = new JFXDrawer();
        drawer.setSidePane(pane);
        drawer.setDefaultDrawerSize(200);
        drawer.setOverLayVisible(true);


        addDrawer(drawer);
    }
}
