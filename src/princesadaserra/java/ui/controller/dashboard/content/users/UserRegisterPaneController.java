package princesadaserra.java.ui.controller.dashboard.content.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import princesadaserra.java.core.role.Role;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.usecases.user.CreateNewUser;
import princesadaserra.java.usecases.user.GetAllRoles;

import javax.sql.ConnectionPoolDataSource;

// TODO implement UserRegisterPaneController
public class UserRegisterPaneController {
    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField lastNameTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXTextField phoneTextField;

    @FXML
    private JFXTextField cpfTextField;

    @FXML
    private JFXComboBox<Role> roleComboBox;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField repeatPasswordField;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXTextField imageUrlTextField;

    @FXML
    private Label infoLabel;

    private UserRepository userRepository;
    private GetAllRoles getAllRoles;
    public UserRegisterPaneController(ConnectionPoolDataSource dataSource){
        this.userRepository = new UserRepository(dataSource);
        getAllRoles = new GetAllRoles(userRepository);
        getAllRoles.addOnSuccessCallback(roles -> roleComboBox.getItems().addAll(roles));
    }

    @FXML
    public void initialize() {
        getAllRoles.start();
        registerButton.setOnAction(this::registerButtonOnAction);
        clearButton.setOnAction(this::clearButtonOnAction);
        // todo implement roles list
    }

    private void showInfo(String info){
        infoLabel.setText(info);
    }

    private void clearButtonOnAction(ActionEvent actionEvent) {
        // TODO: 03/12/2018 clearbuttononaction
    }

    private void validateFields(){
        // TODO: 03/12/2018 validatefields()
    }

    private void registerButtonOnAction(ActionEvent actionEvent) {
        CreateNewUser createNewUser = new CreateNewUser(userRepository, getUser());
        createNewUser.addOnSuccessCallback(user -> showInfo("Usuário criado com sucesso!"));
        createNewUser.addOnFailedCallback(user -> showInfo("Erro durante o cadastro o usuário"));
    }

    private User getUser(){
        User newUser = new User();
        System.out.println("imageUrlTextField.getText() == null = " + imageUrlTextField.getText() == null);
        newUser.setImageUrl(imageUrlTextField.getText());
        newUser.setUsername(emailTextField.getText());
        newUser.setFirstName(nameTextField.getText());
        newUser.setLastName(lastNameTextField.getText());
        newUser.setEmail(emailTextField.getText());
        newUser.setPhone(phoneTextField.getText());
        newUser.setRole(roleComboBox.valueProperty().getValue());
        newUser.setCpf(cpfTextField.getText());
        newUser.setPassword(passwordField.getText());

        return newUser;
    }

}
