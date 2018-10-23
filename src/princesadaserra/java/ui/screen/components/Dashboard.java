package princesadaserra.java.ui.screen.components;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class Dashboard extends AnchorPane {

    private AnchorPane content;
    private String pageName;
    private JFXDrawersStack drawersStack;
    private JFXDrawer drawer;
    private StackPane drawerPane;
    private AppBar appBar;

    public Dashboard(StackPane drawerPane, AppBar appBar, AnchorPane content) {
        this.appBar = appBar;
        this.content = content;
        this.drawerPane = drawerPane;

        drawersStack = new JFXDrawersStack();
        drawer = new JFXDrawer();
        drawer.setDefaultDrawerSize(200);
        drawer.setSidePane(drawerPane);
        drawer.setOverLayVisible(true);

        drawer.setOnDrawerClosed(event -> {
            AnchorPane.clearConstraints(drawer);
            drawersStack.toBack();
        });

        drawer.setOnDrawerOpening(event -> {
            drawersStack.toFront();
            appBar.toFront();
            AnchorPane.setBottomAnchor(drawersStack, 0d);
            AnchorPane.setRightAnchor(drawersStack, 0d);
            AnchorPane.setTopAnchor(drawersStack, 0d);
            AnchorPane.setLeftAnchor(drawersStack, 0d);
        });



        AnchorPane.setLeftAnchor(appBar, 0d);
        AnchorPane.setTopAnchor(appBar, 0d);
        AnchorPane.setRightAnchor(appBar, 0d);

        AnchorPane.setLeftAnchor(content, 0d);
        AnchorPane.setBottomAnchor(content, 0d);
        AnchorPane.setRightAnchor(content, 0d);
        AnchorPane.setTopAnchor(content, appBar.getPrefHeight());
        content.setLayoutY(appBar.getPrefHeight());
        System.out.println("appbarHeight: " + appBar.getPrefHeight());



        getChildren().add(content);
        getChildren().add(drawersStack);
        getChildren().add(appBar);
        init();
    }

    public AnchorPane getContent() {
        return content;
    }

    public void setContent(AnchorPane content) {
        this.content = content;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public JFXDrawer getDrawer() {
        return drawer;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public StackPane getDrawerPane() {
        return drawerPane;
    }

    public void setDrawerPane(StackPane drawerPane) {
        this.drawerPane = drawerPane;
    }

    public AppBar getAppBar() {
        return appBar;
    }

    public void toggleDrawer(){

        drawersStack.toggle(getDrawer());

        System.out.println("Drawer " + (getDrawer().isOpened() ? "open" : "closed"));
    }

    private void init(){

    }
}
