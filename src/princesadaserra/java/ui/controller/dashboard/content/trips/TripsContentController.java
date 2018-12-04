package princesadaserra.java.ui.controller.dashboard.content.trips;

// TODO implement TripsContentController

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.usecases.trip.LoadTripsBySpecification;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
    private TableView<Trip> tripsTable;

    @FXML
    private TableColumn<Trip, String> originCol;

    @FXML
    private TableColumn<Trip, String> destinyCol;

    @FXML
    private TableColumn<Trip, String> priceCol;

    @FXML
    private TableColumn<Trip, String> freeSeatsCol;

    @FXML
    private JFXTextField priceTextField;

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

        searchButton.setOnAction(this::searchButtonOnAction);

        initTable();
    }

    private void searchButtonOnAction(ActionEvent event) {
        LoadTripsBySpecification bySpecification = new LoadTripsBySpecification(dataSource, getPredicates());
        bySpecification.addOnSuccessCallback(trips -> {
            tripsTable.getItems().removeAll(tripsTable.getItems());
            tripsTable.getItems().addAll(trips);
        });

        bySpecification.start();
    }

    public Predicate<Trip>[] getPredicates(){
        List<Predicate<Trip>> predicates = new ArrayList<>();

        if(!originTextField.getText().equals("")){
            predicates.add(trip -> !trip.getRoute().getFirstCity().getName().equals(originTextField.getText()));
        }

        if(!destinyTextField.getText().equals("")){
            predicates.add(trip -> !trip.getRoute().getLastCity().getName().equals(destinyTextField.getText()));
        }

        if(datePicker.getValue() != null){
            predicates.add(trip -> !trip.getTimeStart().before(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }

        if(!priceTextField.getText().equals("")){
            predicates.add(trip -> trip.getRoute().getValue() <= Double.parseDouble(priceTextField.getText()));
        }

        Predicate<Trip>[] preds = new Predicate[predicates.size()];

        for(int x = 0; x < preds.length; x++){
            preds[x] = predicates.get(x);
        }

        return preds;
    }

    private void initTable(){
        System.out.println("originCol == null = " + originCol == null);
        originCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoute().getFirstCity().getName()));
        destinyCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoute().getLastCity().getName()));
        priceCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getRoute().getValue())));
        freeSeatsCol.setCellValueFactory(
                data -> new SimpleStringProperty(String.valueOf(
                        data.getValue().emptySeats(data.getValue().getRoute().getFirstCity(), data.getValue().getRoute().getLastCity())
                )));
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
