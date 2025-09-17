package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.PontoService;
import br.edu.ifba.saj.fwads.service.RotaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadRotaController {
    @FXML
    private TextField txNome;
    @FXML
    private ChoiceBox<Ponto> slPontoInicial;
    @FXML
    private ChoiceBox<Ponto> slPontoFinal;
    @FXML
    private ListView<Ponto> lvparadas;

    private ListRotaController listRotaController;
    private RotaService serviceRota = new RotaService();
    private PontoService servicePonto = new PontoService();

    public void setListRotaController(ListRotaController listRotaController) {
        this.listRotaController = listRotaController;
    }

    @FXML
    void salvarRota(ActionEvent event) {
        Rota novaRota = new Rota(
            txNome.getText().trim(),
            slPontoInicial.getSelectionModel().getSelectedItem(),
            slPontoFinal.getSelectionModel().getSelectedItem(),
            lvparadas.getSelectionModel().getSelectedItems()
        );

        try {
            serviceRota.salvarComValidacao(novaRota);
            new Alert(AlertType.INFORMATION,
                "Rota: " + novaRota.getNome() + " cadastrada com sucesso!").showAndWait();
            limparTela();
            if (listRotaController != null) {
                listRotaController.loadRotaList();
            }
        } catch (CampoObrigatorioException | EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void initialize() {
        StringConverter<Ponto> converter = new StringConverter<>() {
            @Override
            public String toString(Ponto obj) {
                return obj != null ? obj.getEndereco() : "";
            }

            @Override
            public Ponto fromString(String string) {
                return null;
            }
        };

        slPontoInicial.setConverter(converter);
        slPontoFinal.setConverter(converter);
        lvparadas.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        carregarListaPontos();
    }

    private void carregarListaPontos() {
        var pontos = FXCollections.observableList(servicePonto.findAll());
        slPontoInicial.setItems(pontos);
        slPontoFinal.setItems(pontos);
        lvparadas.setItems(pontos);
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        slPontoInicial.getSelectionModel().clearSelection();
        slPontoFinal.getSelectionModel().clearSelection();
        lvparadas.getSelectionModel().clearSelection();
    }
}
