package princesadaserra.java.ui.controller.dashboard;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.ui.controller.login.LoginViewController;
import princesadaserra.java.usecases.user.GetUserByUsername;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.ui.components.animated.AnimatedHamburguer;
import princesadaserra.ui.components.pane.UserInfoAdapter;
import princesadaserra.ui.components.pane.UserSidePane;

import javax.sql.ConnectionPoolDataSource;

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
    @FXML
    private Text appBarTitle;

    private UserSidePane drawerPane;
    private JFXSnackbar snackbar;
    private JFXDrawer drawer;

    private ConnectionPoolDataSource dataSource;
    private GetUserByUsername getUserTask;
    private AppContext context;

    public DashboardViewController(AppContext context, ConnectionPoolDataSource dataSource, String username){
        this.context = context;
        this.dataSource = dataSource;
        getUserTask = new GetUserByUsername(dataSource, username);

        getUserTask.addOnSuccessCallback(this::setSidePaneUserInfo);
    }

    @FXML
    public void initialize(){
        getUserTask.start();

        drawer = new JFXDrawer();
        snackbar = new JFXSnackbar(dashboardRoot);
        drawerPane = new UserSidePane();
        drawerPane.getTitleText().setText("Princesa da serra");

        drawer.setSidePane(drawerPane);
        drawer.setDefaultDrawerSize(drawerPane.getPrefWidth());
        drawer.setOnDrawerClosed( event -> drawersStack.toBack() );

        drawersStack.addDrawer(drawer);
        addDrawerButtons();
        menuButton.setOnMouseClicked(this::menuButtonOnClick);

        showTrips();
    }

    private void setSidePaneUserInfo(User user) {
        System.out.println("DashboardViewController.setSidePaneUserInfo");
        System.out.println("user = " + user);

        if (user != null) {
            drawerPane.setUserInfo(new UserInfoAdapter() {
                @Override
                public Image getImage() {
                    return new Image(user.getImageUrl());
                }

                @Override
                public String getDisplayName() {
                    return user.getFirstName();
                }

                @Override
                public String getEmail() {
                    return user.getEmail();
                }
            });
        }
    }

    private void setPageName(String name){
        appBarTitle.setText(name);
    }

    private void showClients(){
        setPageName(context.getResourceBundle().getString("page.title.clients"));
    }

    private void showTrips(){
        setPageName(context.getResourceBundle().getString("page.title.trips"));
    }

    private void showHistory(){
        setPageName(context.getResourceBundle().getString("page.title.history"));
    }

    private void showOptions(){
        setPageName(context.getResourceBundle().getString("page.title.options"));
    }

    private void addDrawerButtons(){

        drawerPane.addButton("travels", context.getResourceBundle().getString("drawer.text.trips"),
                event -> {
                    showTrips();
                    drawer.close();
                });

        drawerPane.addButton("history", context.getResourceBundle().getString("drawer.text.history"),
                event -> {
                    showHistory();
                    drawer.close();
                });

        drawerPane.addButton("clients", context.getResourceBundle().getString("drawer.text.clients"),
                event -> {
                    showClients();
                    drawer.close();
                });

        drawerPane.addButton("options", context.getResourceBundle().getString("drawer.text.options"),
                event -> {
                    showOptions();
                    drawer.close();
                });

        drawerPane.addButton("logout", context.getResourceBundle().getString("drawer.text.logout"),
                event -> doLogout());

        drawerPane.addButton("exit", context.getResourceBundle().getString("drawer.text.exit"),
                event -> Platform.exit());
    }

    public void doLogout(){
        System.out.println("DashboardViewController.doLogout");
        context.getNavigator().navigateTo(ScenesTypes.LOGIN, new LoginViewController(context));
    }

    public void menuButtonOnClick(MouseEvent event){
        toggleDrawer();
    }

    public void toggleDrawer(){
        if(drawer.isClosed())
            drawersStack.toFront();

        drawer.toggle();
    }
}
