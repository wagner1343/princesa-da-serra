package tasks.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXTextField userTextField;

    @FXML
    private Text resultText;

    public void loginOnClick(){
        TestLoginTaskGetCpf task = new TestLoginTaskGetCpf(userTextField.getText(), passwordTextField.getText());
        task.addOnSuccessCallback((user) -> {resultText.setText(user.getName() + " cpf: " + user.getCpf());});
        task.addOnStatusChangedCallback((status) -> {resultText.setText(status.toString());});
        task.start();
    }

}