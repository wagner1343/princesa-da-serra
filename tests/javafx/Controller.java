package javafx;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXTextField userTextField;

    @FXML
    private Text resultText;

    @FXML
    private AnchorPane loginPane;

    @FXML
    public void initialize(){
        userTextField.setVisible(false);
        passwordTextField.setVisible(false);
    }

    public void loginOnClick(){
        TestLoginTaskGetCpf task = new TestLoginTaskGetCpf(userTextField.getText(), passwordTextField.getText());
        task.addOnSuccessCallback((user) -> {resultText.setText(user.getName() + " cpf: " + user.getCpf());});
        task.addOnStatusChangedCallback((status) -> {resultText.setText(status.toString());});
        task.start();
    }
}