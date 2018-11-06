package princesadaserra.java.ui.controller.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import princesadaserra.java.ui.controller.ScenesTypes;
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

    private JFXSnackbar snackbar;

    @FXML
    public void initialize(){
        snackbar = new JFXSnackbar(root);
    }

    public void loginOnClick(){
        System.out.println("LoginViewController.loginOnClick");

        LoginWithUserAndPassword loginTask = new LoginWithUserAndPassword(userTextField.getText(), passwordTextField.getText());

        loginTask.addOnSuccessCallback( result -> AppContext.getInstance().getNavigator().navigateTo(ScenesTypes.DASHBOARD) );
        loginTask.addOnFailedCallback(
                () -> {
                    snackbar.enqueue(new JFXSnackbar.SnackbarEvent("Login failed"));
                });

        loginTask.start(AppContext.getInstance());
    }
}
