package princesadaserra.java.ui.controller.dashboard.content.users;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.ui.controller.popup.RegisterUserPopupController;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;

// TODO finish implementing TripsRegisterPaneController
public class UsersContentController {
    @FXML
    private JFXButton addUserButton;

    private ConnectionPoolDataSource dataSource;
    private AppContext context;
    public UsersContentController(AppContext context, ConnectionPoolDataSource dataSource){
        this.dataSource = dataSource;
        this.context = context;
    }

    @FXML
    public void initialize(){
        addUserButton.setOnAction(this::addUserButtonOnAction);
    }

    private void addUserButtonOnAction(ActionEvent actionEvent) {
        try {
            context.getNavigator().popup(View.REGISTER_USER_POPUP, new RegisterUserPopupController(context, dataSource), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
