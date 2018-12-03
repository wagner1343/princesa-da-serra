package princesadaserra.java.ui.controller.popup;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;

public class RegisterUserPopupController {
    private AppContext context;
    private ConnectionPoolDataSource dataSource;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXButton addPhotoButton;

    public RegisterUserPopupController(AppContext context, ConnectionPoolDataSource dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @FXML
    public void initialize(){
        registerButton.setOnAction(this::registerButtonOnAction);
    }

    public void registerButtonOnAction(ActionEvent actionEvent){
        System.out.println("RegisterUserPopupController.registerButtonOnAction");
    }
}
