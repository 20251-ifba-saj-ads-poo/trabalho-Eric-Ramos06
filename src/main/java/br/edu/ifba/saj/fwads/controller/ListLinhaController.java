package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Linha;
import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.model.Onibus;
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

public class ListLinhaController {

    @FXML
    private TableColumn<Linha, String> clnNome;

    @FXML
    private TableColumn<Linha, String> clnMotorista;

    @FXML
    private TableColumn<Linha, String> clnOnibus;

    @FXML
    private TableColumn<Linha, String> clnItinerario;

    @FXML
    private TableView<Linha> tblLinha;

    @FXML
    public void initialize() {
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnMotorista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotorista().getNome()));
        clnOnibus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOnibus().getPlaca()));
        clnItinerario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItinerario().getNome()));
        loadLinhaList();
    }

    public void loadLinhaList(){
        tblLinha.setItems(FXCollections.observableList(new Service(Linha.class).findAll()));
    }

    @FXML
    void showNovoLinha(ActionEvent event) {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadLinha.fxml"), 800, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadLinhaController controller = (CadLinhaController) App.getController();
        controller.setListLinhaController(this);

        stage.showAndWait();

    }

}