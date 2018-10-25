package princesadaserra.java.ui.views.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import princesadaserra.java.ui.views.ScenesTypes;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;
import princesadaserra.java.util.context.AppContext;

public class LoginViewController {
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

        loginTask.addOnSuccessCallback( user -> AppContext.getInstance().getNavigator().navigateTo(ScenesTypes.DASHBOARD) );
        loginTask.addOnFinishCallback(
                user -> {snackbar.enqueue(new JFXSnackbar.SnackbarEvent((user == null) ? "Login failed" : "Login Success"));});

        loginTask.start(AppContext.getInstance());
    }
}
