package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Rota;
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

public class ListRotaController {

    @FXML
    private TableColumn<Rota, String> clnNome;

    @FXML
    private TableColumn<Rota, String> clnPontoInicial;

    @FXML
    private TableColumn<Rota, String> clnPontoFinal;

    @FXML
    private TableColumn<Rota, String> clnParadas;

    @FXML
    private TableView<Rota> tblRota;

    @FXML
    public void initialize() {
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnPontoInicial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPontoInicial().getEndereco()));
        clnPontoFinal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPontoFinal().getEndereco()));
        clnParadas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParadas().toString()));
        loadRotaList();
    }

    public void loadRotaList(){
        tblRota.setItems(FXCollections.observableList(new Service(Rota.class).findAll()));
    }

}