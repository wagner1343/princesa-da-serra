package princesadaserra.java.ui.controller.dashboard.content.trips;

// TODO implement TripsContentController

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;

public class TripsContentController {
    @FXML
    private JFXButton manageTripsButton;

    @FXML
    private VBox root;

    @FXML
    private JFXTextField originTextField;

    @FXML
    private JFXTextField destinyTextField;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTextField timeTextField;

    @FXML
    private JFXButton searchButton;

    @FXML
    private TableView<Trip> tripTableView;

    @FXML
    private TableColumn<Trip, String> originCol;

    @FXML
    private TableColumn<Trip, String> destnyCol;

    @FXML
    private TableColumn<Trip, String> priceCol;

    @FXML
    private TableColumn<Trip, String> freeSeatsCol;

    private Parent tripsRegisterPane;
    private boolean tripsRegisterPaneIsShowing;
    private ConnectionPoolDataSource dataSource;
    private User user;
    private AppContext context;

    public TripsContentController(AppContext context, ConnectionPoolDataSource dataSource, User user) {
        this.dataSource = dataSource;
        this.user = user;
        this.context = context;
        tripsRegisterPaneIsShowing = false;

        System.out.println("user.getRole() = " + user.getRole());
        if(user.getRole().getName().equals("admin")) {
            try {
                tripsRegisterPane = context.getNavigator().loadView(View.TRIPS_REGISTER_PANE, new TripsRegisterPaneController(dataSource), context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize(){
        if(!user.getRole().getName().equals("admin")) manageTripsButton.setVisible(false);
        else {
            manageTripsButton.setOnAction(this::manageTripsButtonOnAction);
        }
    }

    private void manageTripsButtonOnAction(ActionEvent event) {
        if(tripsRegisterPaneIsShowing){
            manageTripsButton.setText("Gerenciar Viagens, Rotas, e Cidades");
            hideTripsRegisterPane();
        }
        else {
            manageTripsButton.setText("Pronto");
            showTripsRegisterPane();
        }
        tripsRegisterPaneIsShowing = !tripsRegisterPaneIsShowing;
    }

    private void showTripsRegisterPane(){
        root.getChildren().remove(tripsRegisterPane);
        root.getChildren().add(1, tripsRegisterPane);

    }
    private void hideTripsRegisterPane(){
        root.getChildren().remove(tripsRegisterPane);
    }
}
