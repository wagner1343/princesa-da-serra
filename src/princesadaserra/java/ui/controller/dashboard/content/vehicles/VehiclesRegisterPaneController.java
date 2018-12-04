package princesadaserra.java.ui.controller.dashboard.content.vehicles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.usecases.user.GetAllRoles;
import princesadaserra.java.usecases.vehicle.CreateNewBus;
import princesadaserra.java.usecases.vehicle.CreateNewModel;
import princesadaserra.java.usecases.vehicle.LoadAllModels;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class VehiclesRegisterPaneController {
    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField seatsTextField;

    @FXML
    private JFXTextField axisTextField;

    @FXML
    private JFXTextField yearTextField;

    @FXML
    private JFXButton addModelButton;

    @FXML
    private JFXButton addVehicleButton;

    @FXML
    private JFXComboBox<Model> modelComboBox;
    @FXML
    private Label modelInfo;
    @FXML
    private Label vehicleInfo;

    private ConnectionPoolDataSource dataSource;

    public VehiclesRegisterPaneController(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @FXML
    public void initialize(){
        this.addVehicleButton.setOnAction(this::addVehicleOnAction);
        this.addModelButton.setOnAction(this::addModelButtonOnAction);
        modelComboBox.setOnMouseClicked(event -> updateModels());
    }

    private void updateModels(){

        LoadAllModels loadAllModels = new LoadAllModels(dataSource);
        loadAllModels.addOnSuccessCallback(roles -> {
            modelComboBox.getItems().removeAll(modelComboBox.getItems());
            modelComboBox.getItems().addAll(roles);

        });
        loadAllModels.start();
    }

    private void showModelInfo(String info){
        modelInfo.setText(info);
    }

    private void showVehicleInfo(String info){
        vehicleInfo.setText(info);
    }

    // todo implement validation
    private Model getModel(){
        Model model = new Model();
        model.setName(nameTextField.getText());
        model.setSeatAmount(Integer.parseInt(seatsTextField.getText()));
        model.setAxisAmount(Integer.parseInt(axisTextField.getText()));
        model.setYear(Integer.parseInt(yearTextField.getText()));

        return model;
    }

    private Bus getBus(){
        Bus bus = new Bus();
        bus.setModel(modelComboBox.getValue());
        bus.setLastMaintenance(new Date(Calendar.getInstance().getTime().getTime()));

        return bus;
    }
    private void addModelButtonOnAction(ActionEvent actionEvent) {
        CreateNewModel newModel = new CreateNewModel(dataSource, getModel());
        newModel.addOnSuccessCallback(model -> showModelInfo("Modelo " + model.getName() + " criado com sucesso"));
        newModel.addOnFailedCallback(model -> showModelInfo("Erro ao cadastrar modelo " + model.getName()));
        newModel.start();
    }

    private void addVehicleOnAction(ActionEvent event){
        CreateNewBus newBus = new CreateNewBus(dataSource, getBus());
        newBus.addOnSuccessCallback(bus -> showVehicleInfo("Onibus " + bus.getModel().getName() + " criado com sucesso"));
        newBus.addOnFailedCallback(bus -> showVehicleInfo("Erro ao cadastrar onibus " + bus.getModel().getName()));
        newBus.start();
    }
}
