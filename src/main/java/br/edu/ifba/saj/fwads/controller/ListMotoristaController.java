package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListMotoristaController {
    @FXML
    private TableView<Motorista> tblMotorista;

    @FXML
    private TableColumn<Motorista, String> columnNome;

    @FXML
    private TableColumn<Motorista, String> columncpf;


    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columncpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        loadMotoristaList();
    }

    public void loadMotoristaList() {
        tblMotorista.setItems(FXCollections.observableList(new Service(Motorista.class).findAll()));
    }

}