package princesadaserra.java.ui.controller.dashboard;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.ui.controller.dashboard.content.trips.TripsContentController;
import princesadaserra.java.ui.controller.dashboard.content.users.UsersContentController;
import princesadaserra.java.ui.controller.dashboard.content.vehicles.VehiclesContentController;
import princesadaserra.java.ui.controller.login.LoginViewController;
import princesadaserra.java.usecases.user.GetUserByUsername;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.ui.components.animated.AnimatedHamburguer;
import princesadaserra.ui.components.pane.UserInfoAdapter;
import princesadaserra.ui.components.pane.UserSidePane;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;

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

    private Pane tripsContent;
    private Pane usersContent;
    private Pane vehiclesContent;

    private UserSidePane drawerPane;
    private JFXSnackbar snackbar;
    private JFXDrawer drawer;

    private ConnectionPoolDataSource dataSource;
    private GetUserByUsername getUserTask;
    private AppContext context;
    private UserRepository userRepository;
    private String username;
    private User currentUser;

    public DashboardViewController(AppContext context, ConnectionPoolDataSource dataSource, String username) {
        this.context = context;
        this.dataSource = dataSource;
        this.username = username;
        this.userRepository = new UserRepository(dataSource);
        getUserTask = new GetUserByUsername(userRepository, username);

        getUserTask.addOnFinishCallback(user -> addSidePaneButtons(user));
        getUserTask.addOnFinishCallback(user -> setSidePaneUserInfo(user));
        getUserTask.addOnFinishCallback(user -> this.currentUser = user);

    }

    @FXML
    public void initialize() {


        drawer = new JFXDrawer();
        snackbar = new JFXSnackbar(dashboardRoot);
        drawerPane = new UserSidePane();
        drawerPane.getTitleText().setText("Princesa da serra");

        drawer.setSidePane(drawerPane);
        drawer.setDefaultDrawerSize(drawerPane.getPrefWidth());
        drawer.setOnDrawerClosed(event -> drawersStack.toBack());

        drawersStack.addDrawer(drawer);

        menuButton.setOnMouseClicked(this::menuButtonOnClick);

        getUserTask.addOnFinishCallback(() -> showTrips());
        getUserTask.start();
    }

    private void setSidePaneUserInfo(User user) {
        System.out.println("DashboardViewController.setSidePaneUserInfo");
        System.out.println("user = " + user);

        if (user != null) {
            drawerPane.setUserInfo(new UserInfoAdapter() {
                @Override
                public Image getImage() {
                    Image image = null;
                    try{
                        image = new Image(user.getImageUrl());
                    }
                    catch (IllegalArgumentException e){
                        image = new Image("/img/profile/default.jpg");
                        e.printStackTrace();
                    }
                    return image;
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

    private void setPageName(String name) {
        appBarTitle.setText(name);
    }

    private void showUsers() {
        if (usersContent == null) {
            try {
                usersContent = (Pane) context.getNavigator()
                        .loadView(View.USERS_CONTENT, new UsersContentController(context, dataSource), context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        contentRoot.getChildren().removeAll(contentRoot.getChildren());
        contentRoot.getChildren().add(usersContent);
        setPageName(context.getLocaleBundle().getString("page.title.users"));
    }

    private void showTrips() {
        if (tripsContent == null) {
            try {
                tripsContent = (Pane) context.getNavigator().loadView(View.TRIPS_CONTENT, new TripsContentController(context, dataSource, currentUser), context);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        contentRoot.getChildren().removeAll(contentRoot.getChildren());
        contentRoot.getChildren().add(tripsContent);
        setPageName(context.getLocaleBundle().getString("page.title.trips"));
    }

    private void showVehicles() {
        if (vehiclesContent == null) {
            try {
                vehiclesContent = (Pane) context.getNavigator().loadView(View.VEHICLES_CONTENT, new VehiclesContentController(dataSource, context), context);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        contentRoot.getChildren().removeAll(contentRoot.getChildren());
        contentRoot.getChildren().add(vehiclesContent);
        setPageName(context.getLocaleBundle().getString("page.title.vehicles"));
    }

    private void showOptions() {
        setPageName(context.getLocaleBundle().getString("page.title.options"));
    }

    private void addSidePaneButtons(User user) {
        System.out.println("DashboardViewController.addSidePaneButtons");
        System.out.println("user == null = " + user == null);
        System.out.println("user.getRole() == null = " + user.getRole() == null);
        System.out.println("user.getRole().getName() == null = " + user.getRole().getName() == null);

        if ( user.getRole().getName().equals("admin") || user.getRole().getName().equals("seller")) {
            drawerPane.addButton("trips", context.getLocaleBundle().getString("drawer.text.trips"),
                    event -> {
                        showTrips();
                        drawer.close();
                    });
        }

        if ( user.getRole().getName().equals("admin")) {
            drawerPane.addButton("vehicles", context.getLocaleBundle().getString("drawer.text.vehicles"),
                    event -> {
                        showVehicles();
                        drawer.close();
                    });

            drawerPane.addButton("clients", context.getLocaleBundle().getString("drawer.text.users"),
                    event -> {
                        showUsers();
                        drawer.close();
                    });
        }
        drawerPane.addButton("options", context.getLocaleBundle().getString("drawer.text.options"),
                event -> {
                    showOptions();
                    drawer.close();
                });

        drawerPane.addButton("logout", context.getLocaleBundle().getString("drawer.text.logout"),
                event -> doLogout());

        drawerPane.addButton("exit", context.getLocaleBundle().getString("drawer.text.exit"),
                event -> Platform.exit());
    }

    public void doLogout() {
        System.out.println("DashboardViewController.doLogout");
        context.getNavigator().navigateTo(View.LOGIN, new LoginViewController(context));
    }

    public void menuButtonOnClick(MouseEvent event) {
        toggleDrawer();
    }

    public void toggleDrawer() {
        if (drawer.isClosed())
            drawersStack.toFront();

        drawer.toggle();
    }
}
