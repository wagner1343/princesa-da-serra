package princesadaserra.java.ui.controller.dashboard.content.trips;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.route.Segment;

import javax.sql.ConnectionPoolDataSource;
import java.util.List;

// TODO implement TripsRegisterPaneController
public class TripsRegisterPaneController {
    @FXML
    private JFXComboBox<City> originComboBox;
    @FXML
    private JFXComboBox<City> destinyComboBox;

    @FXML
    private JFXTextField distanceTextField;

    @FXML
    private JFXTextField priceTextField;

    @FXML
    private JFXButton addSegmentButton;

    @FXML
    private TableView<Segment> segmentsTable;

    @FXML
    private JFXButton createRouteButton;

    @FXML
    private JFXComboBox<Route> routeComboBox;
    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXComboBox driverComboBox;

    @FXML
    private JFXComboBox busComboBox;

    @FXML
    private JFXButton createTripButton;

    @FXML
    private JFXTextField cityNameTextField;

    @FXML
    private JFXTextField createCityButton;

    @FXML
    private TableColumn<Segment, String> destinyCol;

    @FXML
    private TableColumn<Segment, String> originCol;

    @FXML
    private JFXButton removeLastSegmentButton;

    @FXML
    private JFXTextField routeNameTextField;

    private ConnectionPoolDataSource dataSource;

    private City getCity(){
        City city = new City();
        city.setName(cityNameTextField.getText());
        return city;
    }

    private Route getRoute(){
        Route route = new Route();
        route.setName(routeNameTextField.getText());
        List<Segment> segmentList = segmentsTable.getItems();

        for(Segment s : segmentList){
            route.addSegment(s);
        }

        return route;
    }

    public TripsRegisterPaneController(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
    }

}
