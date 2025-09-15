package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListItinerarioController {

    @FXML
    private TableColumn<Itinerario, String> clnNome;

    @FXML
    private TableColumn<Itinerario, String> clnHoraPartida;

    @FXML
    private TableColumn<Itinerario, String> clnRota;

    @FXML
    private TableView<Itinerario> tblItinerario;

    @FXML
    public void initialize() {
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnHoraPartida.setCellValueFactory(new PropertyValueFactory<>("horaPartida"));
        clnRota.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRota().getNome()));
        loadItinerarioList();
    }

    public void loadItinerarioList(){
        tblItinerario.setItems(FXCollections.observableList(new Service(Itinerario.class).findAll()));
    }

    @FXML
    void showNovoItinerario(ActionEvent event) {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadItinerario.fxml"), 800, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadItinerarioController controller = (CadItinerarioController) App.getController();
        controller.setListItinerarioController(this);

        stage.showAndWait();

    }

}