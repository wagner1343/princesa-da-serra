package princesadaserra.java.ui.screen.login;

import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import princesadaserra.java.ui.SharedVisualProperties;
import princesadaserra.java.ui.screen.components.LoginPane;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.context.ResourcesHolder;


public class LoginView extends AnchorPane {
    private AppContext context;

    private HBox hBox;
    private VBox vBox;
    private AnchorPane anchorPane;
    private AnchorPane anchorPane0;
    private Text text;
    private Glow glow;
    private DropShadow dropShadow;
    private AnchorPane anchorPane1;
    private LoginPane loginPane;
    private DropShadow dropShadow1;
    private Color dropShadowColor;
    private Color dropShadowColor1;
    private JFXSnackbar bar;
    private LoginViewController controller;
    
    public LoginView(AppContext context) {
        this.context = context;
        controller = new LoginViewController(context, this);
        createComponents();

        bind();
    }

    private void bind(){
        loginOnClick().set(controller::loginOnClick);
    }

    public ObjectProperty<EventHandler<ActionEvent>> loginOnClick(){
        return loginPane.loginOnClick();
    }

    public String getUser(){
        return loginPane.getUser();
    }

    public String getPassword(){
        return loginPane.getPassword();
    }

    public void incorrectUserOrPassword(){
        System.out.println(ResourcesHolder.getResourceBundle().getString("login.failed.wrong.user.or.password"));

        bar.enqueue(
                new JFXSnackbar.SnackbarEvent(
                        ResourcesHolder.getResourceBundle().getString("login.failed.wrong.user.or.password")
                )
        );
    }

    private void createComponents(){
        bar = new JFXSnackbar(this);
        bar.getStylesheets().add("/view/snackbar/LoginSnackBar.css");

        hBox = new HBox();
        vBox = new VBox();

        anchorPane = new AnchorPane();
        anchorPane0 = new AnchorPane();

        text = new Text();
        glow = new Glow();
        dropShadow = new DropShadow();
        anchorPane1 = new AnchorPane();
        loginPane = new LoginPane();
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
        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setLayoutX(55.0);
        text.setLayoutY(41.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Princesa da Serra");
        text.setFont(new Font("Segoe UI", 40.0));

        glow.setLevel(0.47);
        text.setEffect(glow);

        anchorPane0.setEffect(SharedVisualProperties.getDropShadow());

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

        anchorPane1.setOpaqueInsets(new Insets(0.0));

        anchorPane1.setEffect(SharedVisualProperties.getDropShadow());

        anchorPane0.getChildren().add(text);
        anchorPane.getChildren().add(anchorPane0);


        anchorPane1.getChildren().add(loginPane);
        anchorPane.getChildren().add(anchorPane1);

        vBox.getChildren().add(anchorPane);
        hBox.getChildren().add(vBox);
        getChildren().add(hBox);
    }
}
