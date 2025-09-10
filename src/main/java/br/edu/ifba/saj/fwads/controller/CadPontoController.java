package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.Dados;
import br.edu.ifba.saj.fwads.model.Ponto;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadPontoController {

    @FXML
    private TextField txEndereco;

    @FXML
    private Button btnSalvar;

    @FXML
    private void initialize() {
        BooleanBinding enderecoInvalido = Bindings.createBooleanBinding(
            () -> {
                String texto = txEndereco.getText();
                return texto == null || texto.trim().isEmpty();
            },
            txEndereco.textProperty()
        );
        btnSalvar.disableProperty().bind(enderecoInvalido);
    }

    @FXML
    private void salvarPonto(ActionEvent event) {
        String endereco = txEndereco.getText().trim();
        Ponto novoPonto = new Ponto(endereco);

        new Alert(
            AlertType.INFORMATION,
            "Cadastrando Ponto: " + novoPonto.getEndereco()
        ).showAndWait();

        Dados.listaPonto.add(novoPonto);
        limparTela();
    }

    @FXML
    private void limparTela() {
        txEndereco.clear();
        txEndereco.requestFocus();
    }
}