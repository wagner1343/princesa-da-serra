package princesadaserra.ui.components.pane;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;

public class TripSearchBox extends AnchorPane {

    private final JFXTextField OriginTextField;
    private final JFXTextField DestinyTextField;
    private final JFXTextField DateTextField;
    private final JFXTextField TimeTextField;

    public String getOriginTextFieldPrompt() {
        return originTextFieldPrompt;
    }

    public void setOriginTextFieldPrompt(String originTextFieldPrompt) {
        this.originTextFieldPrompt = originTextFieldPrompt;
    }

    private String originTextFieldPrompt;

    public TripSearchBox() {
        //getStylesheets().add("/view/templates/TripSearchBoxTemplate.css");
        OriginTextField = new JFXTextField();
        DestinyTextField = new JFXTextField();
        DateTextField = new JFXTextField();
        TimeTextField = new JFXTextField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(87.0);
        setPrefWidth(625.0);
        setStyle("-fx-border-radius: 5; -fx-background-radius: 5; -fx-background-color: #8AE664;");

        OriginTextField.setLayoutX(40.0);
        OriginTextField.setLayoutY(14.0);
        OriginTextField.setPrefWidth(414);
        //OriginTextField.setPromptText(ResourcesHolder.getResourceBundle().getString("textfield.origin.prompt"));

        DestinyTextField.setLayoutX(40.0);
        DestinyTextField.setLayoutY(44.0);
        DestinyTextField.setPrefWidth(414);
        //DestinyTextField.setPromptText(ResourcesHolder.getResourceBundle().getString("textfield.destiny.prompt"));

        DateTextField.setLayoutX(512.0);
        DateTextField.setLayoutY(14.0);
        DateTextField.setPrefWidth(85);
        //DateTextField.setPromptText(ResourcesHolder.getResourceBundle().getString("textfield.date.prompt"));

        TimeTextField.setLayoutX(512.0);
        TimeTextField.setLayoutY(44.0);
        TimeTextField.setPrefWidth(85);
        //TimeTextField.setPromptText(ResourcesHolder.getResourceBundle().getString("textfield.time.prompt"));

        getChildren().add(OriginTextField);
        getChildren().add(DestinyTextField);
        getChildren().add(DateTextField);
        getChildren().add(TimeTextField);

    }
}
