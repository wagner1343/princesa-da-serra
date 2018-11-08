package princesadaserra.ui.components.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import princesadaserra.ui.components.button.MaterialIconButton;

import java.util.TreeMap;

public class SidePaneWithUser extends AnchorPane {
    private TreeMap<String, MaterialIconButton> buttonMap;

    public TreeMap<String, MaterialIconButton> getButtonMap() {
        return buttonMap;
    }

    private  ImageView userImageView;
    private  Text titleText;
    private Label userNameText;
    private  Text userEmailText;
    private  AnchorPane optionsPaneRoot;
    private  VBox optionsVBox;
    private  Node imageClip;

    public SidePaneWithUser() {
        buttonMap = new TreeMap<>();
        userImageView = new ImageView();
        titleText = new Text();
        userNameText = new Label();
        userEmailText = new Text();
        optionsPaneRoot = new AnchorPane();
        optionsVBox = new VBox();

        setPrefHeight(600.0);
        setPrefWidth(253.0);
        getStyleClass().add("-pfx-side-pane-with-user");

        userImageView.setFitHeight(64.0);
        userImageView.setFitWidth(64.0);
        userImageView.setLayoutX(16.0);
        userImageView.setLayoutY(69.0);
        userImageView.setPickOnBounds(true);
        userImageView.setPreserveRatio(false);
        userImageView.getStyleClass().add("-pfx-user-image-view");

        imageClip = new Circle(
                (userImageView.getBoundsInParent().getMaxX() - userImageView.getBoundsInParent().getMinX()) / 2,
                (userImageView.getBoundsInParent().getMaxX() - userImageView.getBoundsInParent().getMinX()) / 2,
                (userImageView.getBoundsInParent().getMaxX() - userImageView.getBoundsInParent().getMinX()) / 2
        );

        userImageView.setClip(imageClip);
        //userImageView.setImage(new Image("/img/profile/wagner.jpg"));

        titleText.setFill(javafx.scene.paint.Color.WHITE);
        titleText.setLayoutX(49.0);
        titleText.setLayoutY(36.0);
        titleText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        titleText.setStrokeWidth(0.0);
        //titleText.setText("Princesa da Serra");
        titleText.setFont(new Font(24.0));
        titleText.getStyleClass().add("-pfx-title-text");

        userNameText.setTextFill(javafx.scene.paint.Color.WHITE);
        userNameText.setLayoutX(90.0);
        userNameText.setLayoutY(84.0);
        //userNameText.setText("Wagner S. W. Martins");
        userNameText.setFont(new Font("Segoe UI", 16.0));
        userNameText.getStyleClass().add("-pfx-user-name-text");
        userNameText.setMaxWidth(150.0);

        userEmailText.setFill(javafx.scene.paint.Color.valueOf("#00000062"));
        userEmailText.setLayoutX(92.0);
        userEmailText.setLayoutY(118.0);
        userEmailText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        userEmailText.setStrokeWidth(0.0);
        //userEmailText.setText("wagner1343@outlook.com");
        userEmailText.setFont(new Font("Segoe UI", 11.0));
        userEmailText.getStyleClass().add("-pfx-user-email-text");

        AnchorPane.setBottomAnchor(optionsPaneRoot, 0.0);
        AnchorPane.setLeftAnchor(optionsPaneRoot, 0.0);
        AnchorPane.setRightAnchor(optionsPaneRoot, 0.0);
        AnchorPane.setTopAnchor(optionsPaneRoot, 156.0);
        optionsPaneRoot.setLayoutX(14.0);
        optionsPaneRoot.setLayoutY(200.0);
        optionsPaneRoot.setPrefHeight(200.0);
        optionsPaneRoot.setPrefWidth(200.0);
        optionsPaneRoot.setStyle("-fx-background-color: white;");
        optionsPaneRoot.getStyleClass().add("-pfx-options-pane");

        AnchorPane.setBottomAnchor(optionsVBox, 0.0);
        AnchorPane.setLeftAnchor(optionsVBox, 0.0);
        AnchorPane.setRightAnchor(optionsVBox, 0.0);
        AnchorPane.setTopAnchor(optionsVBox, 0.0);
        optionsVBox.setPrefHeight(200.0);
        optionsVBox.setPrefWidth(100.0);
        optionsVBox.getStyleClass().add("-pfx-options-vbox");
        optionsVBox.setFillWidth(true);

        getChildren().add(userImageView);
        getChildren().add(titleText);
        getChildren().add(userNameText);
        getChildren().add(userEmailText);

        optionsPaneRoot.getChildren().add(optionsVBox);
        getChildren().add(optionsPaneRoot);

    }

    public ImageView getUserImageView() {
        return userImageView;
    }

    public void setUserImageView(ImageView userImageView) {
        this.userImageView = userImageView;
    }

    public Text getTitleText() {
        return titleText;
    }

    public Label getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(Label userNameText) {
        this.userNameText = userNameText;
    }

    public Text getUserEmailText() {
        return userEmailText;
    }

    public void setUserEmailText(Text userEmailText) {
        this.userEmailText = userEmailText;
    }

    public AnchorPane getOptionsPaneRoot() {
        return optionsPaneRoot;
    }

    public void setOptionsPaneRoot(AnchorPane optionsPaneRoot) {
        this.optionsPaneRoot = optionsPaneRoot;
    }

    public VBox getOptionsVBox() {
        return optionsVBox;
    }

    public void setOptionsVBox(VBox optionsVBox) {
        this.optionsVBox = optionsVBox;
    }

    public Node getImageClip() {
        return imageClip;
    }

    public void setImageClip(Node imageClip) {
        this.imageClip = imageClip;
    }

    public void addButton(String buttonId, String buttonText, EventHandler<ActionEvent> handler){
        MaterialIconButton button = new MaterialIconButton();
        button.setButtonText(buttonText);
        button.getButton().setOnAction(handler);

        buttonMap.put(buttonId, button);
        optionsVBox.getChildren().add(button);
    }

    public void removeButton(String buttonId){
        MaterialIconButton button = buttonMap.get(buttonId);

        if(button != null){
            getChildren().remove(button);
        }
    }

    private void updateChildButtons(){
        for(MaterialIconButton b : buttonMap.values()){
            if(!getChildren().contains(b))
                getChildren().add(b);
        }
    }
}
