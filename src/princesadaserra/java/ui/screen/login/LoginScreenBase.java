package princesadaserra.java.ui.screen.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import princesadaserra.java.core.user.Passenger;

public class LoginScreenBase extends AnchorPane {

    private final HBox hBox;
    private final VBox vBox;
    private final AnchorPane anchorPane;
    private final AnchorPane anchorPane0;
    private final Text text;
    private final Glow glow;
    private final DropShadow dropShadow;
    private final AnchorPane anchorPane1;
    private final AnchorPane anchorPane2;
    private final JFXPasswordField passwordTextField;
    private final JFXTextField userTextField;
    private final JFXButton jFXButton;
    private final DropShadow dropShadow0;
    private final Text resultText;
    private final DropShadow dropShadow1;
    private final Color dropShadowColor;
    private final Color dropShadowColor1;

    public LoginScreenBase() {

        dropShadowColor = new Color(0,0,0, 0.20);
        dropShadowColor1 = new Color(0,0,0, 0.60);
        hBox = new HBox();
        vBox = new VBox();
        anchorPane = new AnchorPane();
        anchorPane0 = new AnchorPane();
        text = new Text();
        glow = new Glow();
        dropShadow = new DropShadow();
        anchorPane1 = new AnchorPane();
        anchorPane2 = new AnchorPane();
        passwordTextField = new JFXPasswordField();
        userTextField = new JFXTextField();
        jFXButton = new JFXButton();
        dropShadow0 = new DropShadow();
        resultText = new Text();
        dropShadow1 = new DropShadow();

        setCacheHint(javafx.scene.CacheHint.SPEED);
        setId("root");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);
        getStyleClass().add("root");
        getStylesheets().add("/view/loginview/LoginScreen.css");

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setCacheHint(javafx.scene.CacheHint.SPEED);
        hBox.setPrefHeight(600.0);
        hBox.setPrefWidth(800.0);

        vBox.setCacheHint(javafx.scene.CacheHint.SPEED);

        anchorPane.setCacheHint(javafx.scene.CacheHint.SPEED);
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(412.0);

        AnchorPane.setLeftAnchor(anchorPane0, 0.0);
        AnchorPane.setRightAnchor(anchorPane0, 0.0);
        AnchorPane.setTopAnchor(anchorPane0, 0.0);
        anchorPane0.setCacheHint(javafx.scene.CacheHint.SPEED);
        anchorPane0.setPrefHeight(48.0);
        anchorPane0.setPrefWidth(400.0);
        anchorPane0.setStyle("-fx-background-color: #8AE664a6; -fx-background-radius: 0;");

        text.setCache(true);
        text.setCacheHint(javafx.scene.CacheHint.SPEED);
        text.setFill(Color.WHITE);
        text.setLayoutX(55.0);
        text.setLayoutY(41.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Princesa da Serra");
        text.setFont(new Font("Segoe UI", 40.0));

        glow.setLevel(0.47);
        text.setEffect(glow);

        dropShadow.setColor(dropShadowColor);
        dropShadow.setHeight(12.1);
        dropShadow.setOffsetY(1.5);
        dropShadow.setRadius(5.55);
        dropShadow.setWidth(12.1);
        anchorPane0.setEffect(dropShadow);

        AnchorPane.setBottomAnchor(anchorPane1, 0.0);
        AnchorPane.setLeftAnchor(anchorPane1, 0.0);
        AnchorPane.setRightAnchor(anchorPane1, 0.0);
        anchorPane1.setCacheHint(javafx.scene.CacheHint.SPEED);
        anchorPane1.setLayoutX(11.0);
        anchorPane1.setLayoutY(357.0);
        anchorPane1.setMinHeight(0.0);
        anchorPane1.setMinWidth(0.0);
        anchorPane1.setPrefHeight(240.0);
        anchorPane1.setPrefWidth(400.0);
        anchorPane1.setStyle("-fx-background-color: #8AE664a6; -fx-background-radius: 0;");

        anchorPane2.setCacheHint(javafx.scene.CacheHint.SPEED);
        anchorPane2.setLayoutX(81.0);
        anchorPane2.setLayoutY(48.0);
        anchorPane2.setMaxHeight(USE_PREF_SIZE);
        anchorPane2.setMaxWidth(USE_PREF_SIZE);
        anchorPane2.setMinHeight(USE_PREF_SIZE);
        anchorPane2.setMinWidth(USE_PREF_SIZE);
        anchorPane2.setPrefHeight(144.0);
        anchorPane2.setPrefWidth(251.0);
        anchorPane2.setStyle("-fx-background-radius: 5; -fx-background-color: white;");

        passwordTextField.setCacheHint(javafx.scene.CacheHint.SPEED);
        passwordTextField.setLayoutX(37.0);
        passwordTextField.setLayoutY(48.0);
        passwordTextField.setPrefWidth(180);
        passwordTextField.setPrefHeight(21);
        passwordTextField.setFocusColor(Color.web("#757575"));
        passwordTextField.setUnFocusColor(Color.web("#00000061"));
        passwordTextField.setPromptText("Senha");

        userTextField.setCacheHint(javafx.scene.CacheHint.SPEED);
        userTextField.setLayoutX(37.0);
        userTextField.setLayoutY(16.0);
        userTextField.setPrefWidth(180);
        userTextField.setPrefHeight(21);
        userTextField.setFocusColor(Color.web("#757575"));
        userTextField.setUnFocusColor(Color.web("#00000061"));
        userTextField.setPromptText("Usuário");

        jFXButton.setCacheHint(javafx.scene.CacheHint.SPEED);
        jFXButton.setLayoutX(20.0);
        jFXButton.setLayoutY(93.0);
        jFXButton.setPrefWidth(208);
        jFXButton.setPrefHeight(32);
        jFXButton.setStyle("-fx-background-color: #C4FF39; -fx-background-radius: 5;");
        jFXButton.setFont(new Font(14.0));
        jFXButton.setTextFill(Color.web("white"));
        jFXButton.setText("Login");

        dropShadow0.setColor(dropShadowColor);
        dropShadow0.setHeight(30.94);
        dropShadow0.setOffsetY(3.0);
        dropShadow0.setRadius(15.942499999999999);
        dropShadow0.setWidth(34.83);
        anchorPane2.setEffect(dropShadow0);

        resultText.setLayoutX(195.0);
        resultText.setLayoutY(36.0);
        resultText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        resultText.setStrokeWidth(0.0);
        anchorPane1.setOpaqueInsets(new Insets(0.0));

        dropShadow1.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
        dropShadow1.setColor(dropShadowColor1);
        dropShadow1.setHeight(25.62);
        dropShadow1.setOffsetY(2.0);
        dropShadow1.setRadius(12.58);
        dropShadow1.setSpread(0.15);
        dropShadow1.setWidth(26.7);
        anchorPane1.setEffect(dropShadow1);

        anchorPane0.getChildren().add(text);
        anchorPane.getChildren().add(anchorPane0);
        anchorPane2.getChildren().add(passwordTextField);
        anchorPane2.getChildren().add(userTextField);
        anchorPane2.getChildren().add(jFXButton);
        anchorPane1.getChildren().add(anchorPane2);
        anchorPane1.getChildren().add(resultText);
        anchorPane.getChildren().add(anchorPane1);
        vBox.getChildren().add(anchorPane);
        hBox.getChildren().add(vBox);
        getChildren().add(hBox);

    }
}
