package princesadaserra.java.ui.controller.login;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.ui.controller.dashboard.DashboardViewController;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;
import princesadaserra.java.util.context.AppContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginViewController {
    public static final String FXML_PATH = "/view/login/Login.fxml";
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField userTextField;
    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXComboBox<Label> languageComboBox;

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
        loginButton.setOnAction(this::loginOnClick);

        for(String language:context.getLanguageList()){
            languageComboBox.getItems().add(new Label(language));
        }

        languageComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("newValue.getText() = " + newValue.getText());
            context.setLocale(new Locale(newValue.getText()));
            context.getNavigator().reload();
        });

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
