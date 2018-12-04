package princesadaserra.java.ui.controller.dashboard.content.users;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;

// TODO finish implementing TripsRegisterPaneController
public class UsersContentController {
    @FXML
    private JFXButton addUserButton;
    @FXML
    private VBox root;

    private Parent userRegisterPane;

    private boolean userRegisterPaneIsShowing;
    private ConnectionPoolDataSource dataSource;
    private AppContext context;
    public UsersContentController(AppContext context, ConnectionPoolDataSource dataSource){
        this.dataSource = dataSource;
        this.context = context;
        try {
            userRegisterPane = context.getNavigator().loadView(View.USER_REGISTER_PANE, new UserRegisterPaneController(dataSource), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRegisterPaneIsShowing = false;
    }

    @FXML
    public void initialize(){
        addUserButton.setOnAction(this::addUserButtonOnAction);
    }

    private void addUserButtonOnAction(ActionEvent actionEvent) {
       if(userRegisterPaneIsShowing){
           hideUserRegisterPane();
       } else{
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
