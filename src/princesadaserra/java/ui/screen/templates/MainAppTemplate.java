package princesadaserra.java.ui.screen.templates;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainAppTemplate extends AnchorPane {

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

    private AnchorPane content;
    private String pageName;
    private JFXDrawersStack drawersStack;

    public JFXDrawer getDrawer() {
        return drawer;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    private JFXDrawer drawer;

    public StackPane getDrawerPane() {
        return drawerPane;
    }

    public void setDrawerPane(StackPane drawerPane) {
        this.drawerPane = drawerPane;
    }

    private StackPane drawerPane;

    public AppBarTemplate getAppBar() {
        return appBar;
    }

    public void toggleDrawer(){

        drawersStack.toggle(getDrawer());

        System.out.println("Drawer " + (getDrawer().isOpened() ? "open" : "closed"));
    }

    private AppBarTemplate appBar;

    public MainAppTemplate(StackPane drawerPane, AppBarTemplate appBar, AnchorPane content) {
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
        });

        drawer.setOnDrawerOpening(event -> {
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

    private void init(){

    }
}
