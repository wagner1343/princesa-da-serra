package princesadaserra.java.ui.views.dashboard;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.views.ScenesTypes;
import princesadaserra.java.ui.views.components.animated.AnimatedHamburguer;
import princesadaserra.java.ui.views.components.pane.SidePaneWithUser;
import princesadaserra.java.usecases.auth.Logout;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.context.ResourcesHolder;

public class DashboardViewController {
    public static final String FXML_PATH = "/view/templates/DashboardTemplate,fxml";
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
    @FXML
    private Text appBarTitle;

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
        showTrips();
        AppContext.getInstance().addOnUserChanged((user) -> loadSidePaneUserInfo(user));
    }

    private void loadSidePaneUserInfo(User user) {
        System.out.println("DashboardViewController.loadSidePaneUserInfo");
        System.out.println("user = " + user);
        drawerPane.getUserEmailText().setText(user.getEmail() == null ? "" : user.getEmail());
        drawerPane.getUserNameText().setText(user.getName() == null ? "" : user.getName());
    }

    private void setPageName(String name){
        appBarTitle.setText(name);
    }

    private void showClients(){
        setPageName(ResourcesHolder.getResourceBundle().getString("page.title.clients"));
    }

    private void showTrips(){
        setPageName(ResourcesHolder.getResourceBundle().getString("page.title.trips"));
    }

    private void showHistory(){
        setPageName(ResourcesHolder.getResourceBundle().getString("page.title.history"));
    }

    private void showOptions(){
        setPageName(ResourcesHolder.getResourceBundle().getString("page.title.options"));
    }

    private void addDrawerButtons(){

        drawerPane.addButton("travels", ResourcesHolder.getResourceBundle().getString("drawer.text.trips"),
                event -> {
                    showTrips();
                    drawer.close();
                });

        drawerPane.addButton("history", ResourcesHolder.getResourceBundle().getString("drawer.text.history"),
                event -> {
                    showHistory();
                    drawer.close();
                });

        drawerPane.addButton("clients", ResourcesHolder.getResourceBundle().getString("drawer.text.clients"),
                event -> {
                    showClients();
                    drawer.close();
                });

        drawerPane.addButton("options", ResourcesHolder.getResourceBundle().getString("drawer.text.options"),
                event -> {
                    showOptions();
                    drawer.close();
                });

        drawerPane.addButton("logout", ResourcesHolder.getResourceBundle().getString("drawer.text.logout"),
                event -> doLogout());

        drawerPane.addButton("exit", ResourcesHolder.getResourceBundle().getString("drawer.text.exit"),
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
        if(drawer.isClosed())
            drawersStack.toFront();

        drawer.toggle();
    }
}
