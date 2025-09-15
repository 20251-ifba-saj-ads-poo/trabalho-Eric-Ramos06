package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.service.PontoService;
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

    private PontoService servicePonto = new PontoService();

    @FXML
    void salvarPonto(ActionEvent event) {

        try {
            servicePonto.validarDuplicidade(txEndereco.getText());

            Ponto novoPonto = new Ponto(txEndereco.getText());
            servicePonto.create(novoPonto);
            new Alert(AlertType.INFORMATION,
                    "Ponto cadastrado com sucesso: " + novoPonto.getEndereco()).showAndWait();
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
    private void initialize() {
        BooleanBinding enderecoInvalido = Bindings.createBooleanBinding(
                () -> {
                    String texto = txEndereco.getText();
                    return texto == null || texto.trim().isEmpty();
                },
                txEndereco.textProperty());
        btnSalvar.disableProperty().bind(enderecoInvalido);
    }

    @FXML
    private void limparTela() {
        txEndereco.clear();
        txEndereco.requestFocus();
    }
}