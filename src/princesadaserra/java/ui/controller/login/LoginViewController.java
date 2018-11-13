package princesadaserra.java.ui.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.ui.controller.dashboard.DashboardViewController;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;
import princesadaserra.java.util.context.AppContext;

public class LoginViewController {
    public static final String FXML_PATH = "/view/login/LoginScreen.fxml";
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField userTextField;
    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXButton loginButton;

    private JFXSnackbar snackbar;
    private AppContext context;

    public LoginViewController(AppContext context){
        this.context = context;
    }

    @FXML
    public void initialize(){
        snackbar = new JFXSnackbar(root);
        loginButton.setOnAction(event -> loginOnClick(event));
    }

    public void loginOnClick(ActionEvent event){
        System.out.println("LoginViewController.loginOnClick");

        String user = userTextField.getText();
        String password = passwordTextField.getText();

        LoginWithUserAndPassword loginTask = new LoginWithUserAndPassword(user, password);

        loginTask.addOnSuccessCallback(
                connectionPool -> context.getNavigator().navigateTo(ScenesTypes.DASHBOARD, new DashboardViewController(context, connectionPool, user))
        );

        loginTask.addOnFailedCallback(
                () -> snackbar.enqueue(new JFXSnackbar.SnackbarEvent("Login failed"))
        );

        loginTask.start();
    }
}
