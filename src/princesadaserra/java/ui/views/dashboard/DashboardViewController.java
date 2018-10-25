package princesadaserra.java.ui.views.dashboard;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.views.ScenesTypes;
import princesadaserra.java.ui.views.components.animated.AnimatedHamburguer;
import princesadaserra.java.ui.views.components.pane.SidePaneWithUser;
import princesadaserra.java.usecases.auth.Logout;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.context.ResourcesHolder;

public class DashboardViewController {
    @FXML
    private JFXDrawersStack drawersStack;
    @FXML
    private AnimatedHamburguer menuButton;
    @FXML
    private AnchorPane dashboardRoot;
    @FXML
    private AnchorPane contentRoot;
    @FXML
    private AnchorPane AppBarRoot;

    private SidePaneWithUser drawerPane;
    private JFXSnackbar snackbar;
    private JFXDrawer drawer;

    @FXML
    public void initialize(){
        drawer = new JFXDrawer();
        snackbar = new JFXSnackbar(dashboardRoot);
        drawerPane = new SidePaneWithUser();

        drawer.setSidePane(drawerPane);
        drawer.setDefaultDrawerSize(drawerPane.getPrefWidth());
        drawer.setOnDrawerClosed( event -> drawersStack.toBack() );

        drawersStack.addDrawer(drawer);

        addDrawerButtons();

        loadSidePaneUserInfo();
    }

    private void loadSidePaneUserInfo() {
        User user = AppContext.getInstance().getCurrentUser();
        drawerPane.getUserEmailText().setText(user.getEmail() == null ? "" : user.getEmail());
        drawerPane.getUserNameText().setText(user.getName() == null ? "" : user.getName());
    }


    private void addDrawerButtons(){

        drawerPane.addButton("travels", ResourcesHolder.getResourceBundle().getString("drawer.trips.text"),
                event -> {});

        drawerPane.addButton("history", ResourcesHolder.getResourceBundle().getString("drawer.history.text"),
                event -> {});

        drawerPane.addButton("clients", ResourcesHolder.getResourceBundle().getString("drawer.clients.text"),
                event -> {});

        drawerPane.addButton("options", ResourcesHolder.getResourceBundle().getString("drawer.options.text"),
                event -> {});

        drawerPane.addButton("logout", ResourcesHolder.getResourceBundle().getString("drawer.logout.text"),
                event -> doLogout());

        drawerPane.addButton("exit", ResourcesHolder.getResourceBundle().getString("drawer.exit.text"),
                event -> Platform.exit());
    }

    public void doLogout(){
        System.out.println("DashboardViewController.doLogout");

        Logout logout = new Logout();
        logout.addOnFinishCallback( (result) -> snackbar.enqueue(new JFXSnackbar.SnackbarEvent(result.getValue())) );
        logout.addOnSuccessCallback( () -> AppContext.getInstance().getNavigator().navigateTo(ScenesTypes.LOGIN) );
        logout.start(AppContext.getInstance());
    }

    public void toggleDrawer(){
        System.out.println("DashboardViewController.toggleDrawer");

        if(drawer.isClosed())
            drawersStack.toFront();

        drawer.toggle();
    }
}
