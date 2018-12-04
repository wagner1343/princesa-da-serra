package princesadaserra.java.ui.controller.dashboard.content.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import princesadaserra.java.core.role.Role;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.usecases.user.GetAllRoles;
import princesadaserra.java.usecases.user.GetUserBySpecification;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// TODO finish implementing TripsRegisterPaneController
public class UsersContentController {
    @FXML
    private JFXButton addUserButton;
    @FXML
    private JFXButton searchButton;
    @FXML
    private TableView<User> usersTable;

    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField lastNameTextField;
    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXTextField cpfTextField;
    @FXML
    private JFXTextField phoneTextField;

    @FXML
    private JFXComboBox<Role> roleComboBox;
    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User, String> lastNameCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> cpfCol;

    @FXML
    private TableColumn<User, String> phoneCol;

    @FXML
    private TableColumn<User, String> roleCol;

    @FXML
    private VBox root;

    private Parent userRegisterPane;
    private boolean userRegisterPaneIsShowing;
    private ConnectionPoolDataSource dataSource;
    private AppContext context;
    private UserRepository userRepository;

    public UsersContentController(AppContext context, ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        this.context = context;
        this.userRepository = new UserRepository(dataSource);

        try {
            userRegisterPane = context.getNavigator().loadView(View.USER_REGISTER_PANE, new UserRegisterPaneController(dataSource), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRegisterPaneIsShowing = false;
    }

    @FXML
    public void initialize() {
        addUserButton.setOnAction(this::addUserButtonOnAction);
        searchButton.setOnAction(this::searchButtonOnClick);
        roleComboBox.setOnMouseClicked(event -> updateRoles());
        initTable();
    }

    private void updateRoles() {
        GetAllRoles getAllRoles = new GetAllRoles(userRepository);
        getAllRoles.addOnSuccessCallback(roles -> {
            roleComboBox.getItems().removeAll(roleComboBox.getItems());
            roleComboBox.getItems().addAll(roles);
        });
        getAllRoles.start();
    }

    private void initTable() {
        nameCol.setCellValueFactory(cellDataFeatures -> {
            StringProperty stringProperty = new SimpleStringProperty(cellDataFeatures.getValue().getFirstName());
            return stringProperty;
        });

        lastNameCol.setCellValueFactory(cellDataFeatures -> {
            StringProperty stringProperty = new SimpleStringProperty(cellDataFeatures.getValue().getLastName());
            return stringProperty;
        });
        emailCol.setCellValueFactory(cellDataFeatures -> {
            StringProperty stringProperty = new SimpleStringProperty(cellDataFeatures.getValue().getEmail());
            return stringProperty;
        });
        cpfCol.setCellValueFactory(cellDataFeatures -> {
            StringProperty stringProperty = new SimpleStringProperty(cellDataFeatures.getValue().getCpf());
            return stringProperty;
        });
        phoneCol.setCellValueFactory(cellDataFeatures -> {
            StringProperty stringProperty = new SimpleStringProperty(cellDataFeatures.getValue().getPhone());
            return stringProperty;
        });

        roleCol.setCellValueFactory(cellDataFeatures -> {
            StringProperty stringProperty = new SimpleStringProperty(cellDataFeatures.getValue().getRole().getName());
            return stringProperty;
        });
    }

    private Predicate<User>[] getSpecifications() {
        ArrayList<Predicate<User>> predicates = new ArrayList<>();

        if (!nameTextField.getText().equals("")) {
            System.out.println("nameTextField prenchido");
            predicates.add(user -> !user.getFirstName().equals(nameTextField.getText()));
        }

        if (!lastNameTextField.getText().equals("")) {
            System.out.println("lastNameTextField prenchido");
            predicates.add(user -> !user.getLastName().equals(lastNameTextField.getText()));
        }

        if (!emailTextField.getText().equals("")) {
            System.out.println("nameTextField prenchido");
            predicates.add(user -> !user.getEmail().equals(emailTextField.getText()));
        }

        if (!cpfTextField.getText().equals("")) {
            System.out.println("cpfTextField prenchido");
            predicates.add(user -> !user.getCpf().equals(cpfTextField.getText()));
        }

        if (!phoneTextField.getText().equals("")) {
            System.out.println("phoneTextField prenchido");
            predicates.add(user -> !user.getPhone().equals(phoneTextField.getText()));
        }

        if (roleComboBox.getValue() != null && !roleComboBox.getValue().getName().equals("")) {
            System.out.println("roleComboBox prenchido");
            predicates.add(user -> !user.getRole().getName().equals(roleComboBox.getValue().getName()));
        }
        Predicate<User>[] preds = new Predicate[predicates.size()];

        for (int x = 0; x < preds.length; x++) {
            preds[x] = predicates.get(x);
        }

        return preds;
    }

    private void searchButtonOnClick(ActionEvent actionEvent) {
        GetUserBySpecification getUserBySpecification = new GetUserBySpecification(userRepository, getSpecifications());
        getUserBySpecification.addOnSuccessCallback(users -> {
            usersTable.getItems().removeAll(usersTable.getItems());
            usersTable.getItems().addAll(FXCollections.observableArrayList(users));
        });

        getUserBySpecification.start();
    }

    private void addUserButtonOnAction(ActionEvent actionEvent) {
        if (userRegisterPaneIsShowing) {
            hideUserRegisterPane();
        } else {
            showUsersRegisterPane();
        }
        userRegisterPaneIsShowing = !userRegisterPaneIsShowing;
    }

    private void showUsersRegisterPane() {
        addUserButton.setText("Pronto");
        root.getChildren().remove(userRegisterPane);
        root.getChildren().add(1, userRegisterPane);
    }

    private void hideUserRegisterPane() {
        addUserButton.setText("Adicionar Usuário");
        root.getChildren().remove(userRegisterPane);
    }
}
