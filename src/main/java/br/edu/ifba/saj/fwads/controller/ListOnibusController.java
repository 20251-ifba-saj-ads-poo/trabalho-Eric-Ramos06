package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Onibus;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListOnibusController {
    @FXML
    private TableView<Onibus> tblOnibus;

    @FXML
    private TableColumn<Onibus, String> columnPlaca;


    @FXML
    public void initialize() {
        columnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        loadOnibusList();
    }

    public void loadOnibusList() {
        tblOnibus.setItems(FXCollections.observableList(new Service(Onibus.class).findAll()));
    }

    @FXML
    public void showNovoOnibus() {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadOnibus.fxml"), 1000, 800);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadOnibusController controller = (CadOnibusController) App.getController();
        controller.setListOnibusController(this);
        stage.showAndWait();
    }

}