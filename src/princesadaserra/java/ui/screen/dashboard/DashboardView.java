package princesadaserra.java.ui.screen.dashboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import princesadaserra.java.ui.screen.templates.AppBarTemplate;
import princesadaserra.java.ui.screen.templates.MainAppTemplate;

public class DashboardView extends MainAppTemplate {
    private DashboardViewController controller;

    public DashboardView(StackPane drawerPane,  AnchorPane content) {
        super(drawerPane, new AppBarTemplate("Dashboard", 50), content);
        controller = new DashboardViewController(this);

        AnchorPane.setBottomAnchor(getDrawerPane(), 0d);
        AnchorPane.setTopAnchor(getDrawerPane(), 0d);

        getAppBar().getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("MenuButton mouse clicked");
            toggleDrawer();
        });
    }
}
