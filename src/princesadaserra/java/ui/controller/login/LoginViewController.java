package princesadaserra.java.ui.controller.login;

import com.jfoenix.controls.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import princesadaserra.java.ui.controller.Views;
import princesadaserra.java.ui.controller.dashboard.DashboardViewController;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;
import princesadaserra.java.util.context.AppContext;

import java.util.*;

public class LoginViewController {
    public static final String FXML_PATH = "/view/login/Login.fxml";
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField userTextField;
    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXComboBox<String> languageComboBox;

    @FXML
    private JFXButton loginButton;

    private JFXSnackbar snackbar;
    private AppContext context;
    private Map<String, Object> savedState;

    public LoginViewController(AppContext context, Map<String, Object> savedState){
        this(context);
        this.savedState = savedState;
    }
    public LoginViewController(AppContext context){
        this.context = context;
    }

    @FXML
    public void initialize(){
        snackbar = new JFXSnackbar(root);

        languageComboBox.getItems().addAll(context.getLanguageList());
        languageComboBox.valueProperty().addListener(this::languageComboBoxValueChanged);

        loginButton.setOnAction(this::loginOnClick);

        if(this.savedState != null)
            loadState(savedState);
    }

    public void loadState(Map<String, Object> savedState){
        System.out.println("LoginViewController.loadState");
        this.userTextField.setText(((JFXTextField) savedState.get("userTextField")).getText());
        this.passwordTextField.setText(((JFXPasswordField) savedState.get("passwordTextField")).getText());
    }

    public Map<String, Object> getState(){
        Map<String, Object> map = new TreeMap<>();
        map.put("userTextField", userTextField);
        map.put("passwordTextField", passwordTextField);
        return map;
    }

    public void languageComboBoxValueChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        System.out.println("newValue.getText() = " + newValue);
        context.setLocale(new Locale(newValue));
        context.getNavigator().navigateTo(Views.LOGIN, new LoginViewController(context, getState()));
    }

    public void loginOnClick(ActionEvent event){
        System.out.println("LoginViewController.loginOnClick");

        String user = userTextField.getText();
        String password = passwordTextField.getText();

        LoginWithUserAndPassword loginTask = new LoginWithUserAndPassword(user, password);

        loginTask.addOnSuccessCallback(
                connectionPool -> context.getNavigator().navigateTo(Views.DASHBOARD, new DashboardViewController(context, connectionPool, user))
        );

        loginTask.addOnFailedCallback(
                () -> snackbar.enqueue(new JFXSnackbar.SnackbarEvent("Login failed"))
        );

        loginTask.start();
    }
}
