package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.Dados;
import br.edu.ifba.saj.fwads.model.Onibus;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadOnibusController {

    @FXML
    private TextField txPlaca;

    @FXML
    private Button btnSalvar;

    @FXML
    private void initialize() {
        BooleanBinding placaInvalida = Bindings.createBooleanBinding(
            () -> {
                String text = txPlaca.getText();
                return text == null || text.trim().isEmpty();
            },
            txPlaca.textProperty()
        );

        btnSalvar.disableProperty().bind(placaInvalida);
    }

    @FXML
    private void salvarOnibus() {
        String placa = txPlaca.getText().trim();
        Onibus novoOnibus = new Onibus(placa);

        new Alert(
            Alert.AlertType.INFORMATION,
            "Cadastrando Ônibus: " + novoOnibus.getPlaca()
        ).showAndWait();

        Dados.listaOnibus.add(novoOnibus);
        limparTela();
    }

    @FXML
    private void limparTela() {
        txPlaca.clear();
    }
}