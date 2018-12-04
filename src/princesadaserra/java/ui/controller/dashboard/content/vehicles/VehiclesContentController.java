package princesadaserra.java.ui.controller.dashboard.content.vehicles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.usecases.vehicle.GetBusBySpecification;
import princesadaserra.java.usecases.vehicle.LoadAllModels;
import princesadaserra.java.util.context.AppContext;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;
import java.sql.Time;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

// TODO implement VehiclesContentController
public class VehiclesContentController {
    @FXML
    private VBox root;

    @FXML
    private JFXButton addVehiclesButton;

    @FXML
    private JFXTextField seatsTextField;

    @FXML
    private JFXTextField axisTextField;

    @FXML
    private JFXTextField yearTextField;

    @FXML
    private JFXComboBox<Model> modelComboBox;

    @FXML
    private JFXDatePicker maintenanceDatePicker;

    @FXML
    private JFXButton searchButton;

    @FXML
    private TableView<Bus> vehiclesTable;

    @FXML
    private TableColumn<Bus, String> seatsCol;
    @FXML
    private TableColumn<Bus, String> axisCol;
    @FXML
    private TableColumn<Bus, String> yearCol;
    @FXML
    private TableColumn<Bus, String> maintenanceCol;

    private Parent vehicleRegisterPane;
    private boolean vehiclesRegisterPaneIsShowing;
    private ConnectionPoolDataSource dataSource;

    public VehiclesContentController(ConnectionPoolDataSource dataSource, AppContext context){
        this.dataSource = dataSource;
        vehiclesRegisterPaneIsShowing = false;
        try {
            vehicleRegisterPane = context.getNavigator().loadView(View.VEHICLE_REGISTER_PANE, new VehiclesRegisterPaneController(dataSource), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        addVehiclesButton.setOnAction(this::addVehiclesButtonOnAction);
        searchButton.setOnAction(this::searchButtonOnAction);
        modelComboBox.setOnMouseClicked((event) -> updateModels());
        initTable();
    }

    private void updateModels(){

        LoadAllModels loadAllModels = new LoadAllModels(dataSource);
        loadAllModels.addOnSuccessCallback(roles -> {
            modelComboBox.getItems().removeAll(modelComboBox.getItems());
            modelComboBox.getItems().addAll(roles);

        });
        loadAllModels.start();
    }

    private void initTable(){
        seatsCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getModel().getSeatAmount())));
        axisCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getModel().getAxisAmount())));
        yearCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getModel().getYear())));
        maintenanceCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getLastMaintenance())));
    }

    private void searchButtonOnAction(ActionEvent actionEvent) {
        if(validateFields()) {
            GetBusBySpecification getBusBySpecification = new GetBusBySpecification(dataSource, getSpecifications());
            getBusBySpecification.addOnSuccessCallback(buses -> {
                vehiclesTable.getItems().removeAll(vehiclesTable.getItems());
                vehiclesTable.getItems().addAll(buses);
            });
            getBusBySpecification.start();
        } else {
            System.out.println("Invalid fields");
        }
    }

    private boolean validateFields(){
        try{
            if(!seatsTextField.getText().equals("")) Integer.parseInt(seatsTextField.getText());
            if(!axisTextField.getText().equals("")) Integer.parseInt(axisTextField.getText());
            if(!yearTextField.getText().equals("")) Integer.parseInt(yearTextField.getText());

            return true;
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    private Predicate<Bus>[] getSpecifications() {
        ArrayList<Predicate<Bus>> predicates = new ArrayList<>();

        if (!seatsTextField.getText().equals("")) {
            System.out.println("seatsTextField prenchido");
            System.out.println("Integer.parseInt(seatsTextField.getText()) = " + Integer.parseInt(seatsTextField.getText()));
            predicates.add(bus -> bus.getModel().getSeatAmount() < Integer.parseInt(seatsTextField.getText()));
        }

        if (!axisTextField.getText().equals("")) {
            System.out.println("axisTextField prenchido");
            predicates.add(bus -> bus.getModel().getAxisAmount() < Integer.parseInt(axisTextField.getText()));
        }

        if (!yearTextField.getText().equals("")) {
            System.out.println("yearTextField prenchido");
            predicates.add(bus -> bus.getModel().getYear() < Integer.parseInt(yearTextField.getText()));
        }

        if (modelComboBox.getValue() != null) {
            System.out.println("modelComboBox prenchido");
            predicates.add(bus -> !bus.getModel().equals(modelComboBox.getValue()));
        }

        if (maintenanceDatePicker.getValue() != null) {
            System.out.println("maintenanceDatePicker prenchido");
            predicates.add(bus ->
                    bus.getLastMaintenance()
                            .before(Date
                                    .from(maintenanceDatePicker.getValue()
                                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
                                    )
                            ));
        }
        Predicate<Bus>[] preds = new Predicate[predicates.size()];

        for (int x = 0; x < preds.length; x++) {
            preds[x] = predicates.get(x);
        }

        return preds;
    }


    private void addVehiclesButtonOnAction(ActionEvent actionEvent) {
        if(vehiclesRegisterPaneIsShowing){
            hideVehiclesRegisterPane();
        }else {
            showVehiclesRegisterPane();
        }

        vehiclesRegisterPaneIsShowing = !vehiclesRegisterPaneIsShowing;
    }

    private void showVehiclesRegisterPane(){
        addVehiclesButton.setText("Pronto");
        root.getChildren().add(1, vehicleRegisterPane);
    }

    private void hideVehiclesRegisterPane(){
        addVehiclesButton.setText("Adicionar modelos, e veículos");
        root.getChildren().remove(vehicleRegisterPane);
    }

}
