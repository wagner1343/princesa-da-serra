package princesadaserra.java.ui.controller.dashboard.content.trips;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.route.Segment;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.usecases.route.*;
import princesadaserra.java.usecases.trip.CreateNewTrip;
import princesadaserra.java.usecases.user.GetUserBySpecification;
import princesadaserra.java.usecases.vehicle.LoadAllBuses;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

// TODO implement TripsRegisterPaneController
public class TripsRegisterPaneController {
    @FXML
    private JFXComboBox<City> originComboBox;
    @FXML
    private JFXComboBox<City> destinyComboBox;

    @FXML
    private JFXTextField timeTextField;

    @FXML
    private JFXTextField priceTextField;

    @FXML
    private JFXButton createSegmentButton;

    @FXML
    private JFXButton addSegmentButton;

    @FXML
    private JFXComboBox<Segment> segmentComboBox;

    @FXML
    private TableView<Segment> segmentsTable;

    @FXML
    private JFXButton createRouteButton;

    @FXML
    private JFXComboBox<Route> routeComboBox;
    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXComboBox<User> driverComboBox;

    @FXML
    private JFXComboBox<Bus> busComboBox;

    @FXML
    private JFXButton createTripButton;

    @FXML
    private JFXTextField cityNameTextField;

    @FXML
    private JFXButton createCityButton;

    @FXML
    private TableColumn<Segment, String> destinyCol;

    @FXML
    private TableColumn<Segment, String> originCol;

    @FXML
    private JFXButton removeLastSegmentButton;

    @FXML
    private JFXTextField routeNameTextField;

    private ConnectionPoolDataSource dataSource;

    public TripsRegisterPaneController(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @FXML
    public void initialize(){
        routeComboBox.setOnMouseClicked(event -> updateRoutes());
        driverComboBox.setOnMouseClicked(event -> updateDrivers());
        busComboBox.setOnMouseClicked(event -> updateBuses());
        originComboBox.setOnMouseClicked(event -> updateCitiesOrigin());
        destinyComboBox.setOnMouseClicked(event -> updateCitiesDestiny());
        segmentComboBox.setOnMouseClicked(event -> updateSegments());

        createCityButton.setOnAction(this::createCityButtonOnAction);
        createSegmentButton.setOnAction(this::createSegmentButtonOnAction);
        createTripButton.setOnAction(this::createTripButtonOnAction);
        createRouteButton.setOnAction(this::createRouteButtonOnAction);
        addSegmentButton.setOnAction(this::addSegmentButtonOnAction);

        initSegmentTable();
    }

    private void initSegmentTable(){
        originCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCityOrigin())));
        destinyCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCityDestination())));

    }

    private void addSegmentButtonOnAction(ActionEvent event) {
        segmentsTable.getItems().add(segmentComboBox.getValue());
    }

    private void updateSegments() {
        LoadAllSegments allSegments = new LoadAllSegments(dataSource);
        allSegments.addOnSuccessCallback(segments -> {
            segmentComboBox.getItems().removeAll(segmentComboBox.getItems());
            segmentComboBox.getItems().addAll(segments);
        });
        allSegments.start();
    }

    private void createRouteButtonOnAction(ActionEvent event) {
        CreateNewRoute newRoute = new CreateNewRoute(dataSource, getRoute());
        newRoute.addOnSuccessCallback(() -> {
            System.out.println("Rota criada com sucesso");
        });
        newRoute.start();
    }

    private void createTripButtonOnAction(ActionEvent event) {
        CreateNewTrip newTrip = new CreateNewTrip(dataSource, getTrip());
        newTrip.addOnSuccessCallback(() ->{
            System.out.println("viagme adicionada com sucesso");
        });
        newTrip.start();
    }

    private void createSegmentButtonOnAction(ActionEvent event) {
        CreateNewSegment newSegment = new CreateNewSegment(dataSource, getSegment());
        newSegment.addOnSuccessCallback(() -> {
            System.out.println("Segmento adicionado com sucesso");
        });
        newSegment.start();
    }

    private void createCityButtonOnAction(ActionEvent event) {
        CreateNewCity newCity = new CreateNewCity(dataSource, getCity());
        newCity.addOnSuccessCallback(() -> System.out.println("Cidade criada com sucesso"));
        newCity.start();
    }

    private void updateBuses() {
        LoadAllBuses loadAllBuses = new LoadAllBuses(dataSource);
        loadAllBuses.addOnSuccessCallback(buses -> {
            busComboBox.getItems().removeAll(busComboBox.getItems());
            busComboBox.getItems().addAll(buses);
        });
        loadAllBuses.start();
    }

    private void updateDrivers() {
        Predicate<User>[] predicates = new Predicate[1];
        predicates[0] = user -> !user.getRole().getName().equals("driver");
        GetUserBySpecification getUserBySpecification = new GetUserBySpecification(new UserRepository(dataSource), predicates);
        getUserBySpecification.addOnSuccessCallback(users -> {
            driverComboBox.getItems().removeAll(driverComboBox.getItems());
            driverComboBox.getItems().addAll(users);
        });
        getUserBySpecification.start();
    }

    private void updateCitiesOrigin() {
        LoadAllCities loadAllCities = new LoadAllCities(dataSource);
        loadAllCities.addOnSuccessCallback(cities -> {
            originComboBox.getItems().removeAll(originComboBox.getItems());
            originComboBox.getItems().addAll(cities);
        });
        loadAllCities.start();
    }

    private void updateCitiesDestiny() {
        LoadAllCities loadAllCities = new LoadAllCities(dataSource);
        loadAllCities.addOnSuccessCallback(cities -> {
            destinyComboBox.getItems().removeAll(destinyComboBox.getItems());
            destinyComboBox.getItems().addAll(cities);
        });
        loadAllCities.start();
    }

    private void updateRoutes() {
        LoadAllRoutes loadAllRoutes = new LoadAllRoutes(dataSource);
        loadAllRoutes.addOnSuccessCallback(routes -> {
            routeComboBox.getItems().removeAll(routeComboBox.getItems());
            routeComboBox.getItems().addAll(routes);
        });
        loadAllRoutes.start();
    }

    private Trip getTrip() {
        Trip trip = new Trip();
        Timestamp timestamp = new Timestamp(new Date(Calendar.getInstance().getTime().getTime()).getTime());
        trip.setTimeStart(timestamp);
        trip.setRoute(routeComboBox.getValue());
        trip.setDriver(driverComboBox.getValue());
        trip.setBus(busComboBox.getValue());

        return trip;
    }

    private City getCity() {
        City city = new City();
        city.setName(cityNameTextField.getText());
        return city;
    }

    private Route getRoute() {
        Route route = new Route();
        route.setName(routeNameTextField.getText());
        List<Segment> segmentList = segmentsTable.getItems();

        for (Segment s : segmentList) {
            route.addSegment(s);
        }

        return route;
    }

    private Segment getSegment(){
        Segment segment = new Segment();

        segment.setCityOrigin(originComboBox.getValue());
        segment.setCityDestination(destinyComboBox.getValue());
        segment.setTime(new Time(Long.parseLong(timeTextField.getText())));
        segment.setValue(Double.parseDouble(priceTextField.getText()));

        return segment;
    }

}
