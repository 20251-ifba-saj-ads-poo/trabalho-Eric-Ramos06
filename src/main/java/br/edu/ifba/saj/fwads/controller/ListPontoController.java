package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListPontoController {
    @FXML
    private TableView<Ponto> tblPonto;

    @FXML
    private TableColumn<Ponto, String> columnEndereco;


    @FXML
    public void initialize() {
        columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        loadPontoList();
    }

    public void loadPontoList() {
        tblPonto.setItems(FXCollections.observableList(new Service(Ponto.class).findAll()));
    }

    @FXML
    public void showNovoPonto() {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadPonto.fxml"), 1000, 800);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadPontoController controller = (CadPontoController) App.getController();
        controller.setListPontoController(this);
        stage.showAndWait();
    }

}