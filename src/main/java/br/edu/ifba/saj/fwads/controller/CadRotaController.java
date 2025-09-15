package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.PontoService;
import br.edu.ifba.saj.fwads.service.RotaService;
import br.edu.ifba.saj.fwads.model.Ponto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;

public class CadRotaController {
    @FXML
    private TextField txNome;

    @FXML
    private ChoiceBox<Ponto> slPontoInitial;

    @FXML
    private ChoiceBox<Ponto> slPontoFinal;

    @FXML
    private ListView<Ponto> lvParadas;

    @FXML
    private Button btnSalvar;

    private RotaService serviceRota = new RotaService();

    private final StringConverter<Ponto> pontoConverter = new StringConverter<>() {

        @Override
        public String toString(Ponto p) {
            return p != null ? p.getEndereco() : "";
        }

        @Override
        public Ponto fromString(String text) {
            PontoService pontoService = new PontoService();
            return pontoService.buscarTodosPontos().stream()
                    .filter(pt -> pt.getEndereco().equals(text))
                    .findFirst()
                    .orElse(null);
        }
    };

    @FXML
    private void initialize() {
        PontoService pontoService = new PontoService();

        ObservableList<Ponto> obsPontos = pontoService.buscarTodosPontos().stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        slPontoInitial.setItems(obsPontos);
        slPontoFinal.setItems(obsPontos);
        slPontoInitial.setConverter(pontoConverter);
        slPontoFinal.setConverter(pontoConverter);

        lvParadas.setItems(obsPontos);
        lvParadas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lvParadas.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Ponto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getEndereco());
            }
        });

        btnSalvar.disableProperty().bind(
                txNome.textProperty().isEmpty()
                        .or(slPontoInitial.valueProperty().isNull())
                        .or(slPontoFinal.valueProperty().isNull())
                        .or(Bindings.size(lvParadas.getSelectionModel().getSelectedItems())
                                .lessThan(1)));
    }

    @FXML
    void salvarRota(ActionEvent event) {
        try {
            serviceRota.validarDuplicidade(txNome.getText());

            Rota novaRota = new Rota(txNome.getText(), slPontoInitial.getValue(), slPontoFinal.getValue(),
                    new ArrayList<>(lvParadas.getSelectionModel().getSelectedItems()));

            serviceRota.create(novaRota);

            new Alert(AlertType.INFORMATION,
                    "Rota cadastrada com sucesso: " + novaRota.getNome()).showAndWait();

            limparTela();

        } catch (EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(AlertType.ERROR, "Erro inesperado, favor entrar em contato com a equipe de desenvolvimento")
                    .showAndWait();
        }
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        slPontoInitial.setValue(null);
        slPontoFinal.setValue(null);
        lvParadas.getSelectionModel().clearSelection();
    }
}
