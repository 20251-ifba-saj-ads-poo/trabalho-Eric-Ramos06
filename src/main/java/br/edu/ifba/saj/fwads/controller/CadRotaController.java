package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.Dados;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.model.Ponto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
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

    private final StringConverter<Ponto> pontoConverter = new StringConverter<>() {
        @Override
        public String toString(Ponto p) {
            return p != null ? p.getEndereco() : "";
        }
        @Override
        public Ponto fromString(String text) {
            return Dados.listaPonto.stream()
                .filter(pt -> pt.getEndereco().equals(text))
                .findFirst()
                .orElse(null);
        }
    };

    @FXML
public void initialize() {

    ObservableList<Ponto> obsPontos = 
        FXCollections.observableArrayList(Dados.listaPonto);
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
                     .lessThan(1))
    );
}
    @FXML
private void salvarRota() {
    String nome           = txNome.getText().trim();
    Ponto inicial         = slPontoInitial.getValue();
    Ponto fim             = slPontoFinal.getValue();
    ObservableList<Ponto> paradasSelecionadas = 
        lvParadas.getSelectionModel().getSelectedItems();
    Rota novaRota = new Rota(nome, inicial, fim, paradasSelecionadas);

    new Alert(AlertType.INFORMATION,
        String.format("Cadastrando Rota: %s – %s a %s, paradas em %s",
            nome,
            inicial.getEndereco(),
            fim.getEndereco(),
            paradasSelecionadas.stream()
                .map(Ponto::getEndereco)
                .collect(Collectors.joining(", "))
        )
    ).showAndWait();

    Dados.listaRota.add(novaRota);
    limparTela();
}

    @FXML
    private void limparTela() {
        txNome.clear();
        slPontoInitial.getSelectionModel().clearSelection();
        slPontoFinal.getSelectionModel().clearSelection();
        lvParadas.getSelectionModel().clearSelection();
    }

    
}