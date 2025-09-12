package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Onibus;
import br.edu.ifba.saj.fwads.service.OnibusService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadOnibusController {

    @FXML
    private TextField txPlaca;

    @FXML
    private Button btnSalvar;

    private OnibusService serviceOnibus = new OnibusService();

    @FXML
    void salvarOnibus(ActionEvent event) {

        try {
            serviceOnibus.validarDuplicidade(txPlaca.getText());

            Onibus novoOnibus = new Onibus(txPlaca.getText());
            serviceOnibus.create(novoOnibus);
            new Alert(AlertType.INFORMATION,
                    "Ônibus cadastrado com sucesso: " + novoOnibus.getPlaca()).showAndWait();
            limparTela();
            // if (listMotoristaController != null) {
            // listMotoristaController.loadMotoristaList();
            // }
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
    private void limparTela() {
        txPlaca.clear();
    }
}