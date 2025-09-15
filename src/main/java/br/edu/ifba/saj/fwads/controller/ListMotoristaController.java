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
    private TableColumn<Motorista, String> columnCPF;


    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        loadMotoristaList();
    }

    public void loadMotoristaList() {
        tblMotorista.setItems(FXCollections.observableList(new Service(Motorista.class).findAll()));
    }

    @FXML
    public void showNovoMotorista() {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadMotorista.fxml"), 1000, 800);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadMotoristaController controller = (CadMotoristaController) App.getController();
        controller.setListMotoristaController(this);
        stage.showAndWait();
    }

}