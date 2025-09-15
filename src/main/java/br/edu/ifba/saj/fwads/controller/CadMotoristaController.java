package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.service.MotoristaService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadMotoristaController {
    @FXML
    private TextField txNome;

    @FXML
    private TextField txCPF;

    @FXML
    private Button btnSalvar;

    private MotoristaService serviceMotorista = new MotoristaService();

    @FXML
    void salvarMotorista(ActionEvent event) {

        try {
            serviceMotorista.validarDuplicidade(txCPF.getText());

            Motorista novoMotorista = new Motorista(txNome.getText(), txCPF.getText());
            serviceMotorista.create(novoMotorista);
            new Alert(AlertType.INFORMATION,
                    "Motorista cadastrado com sucesso: " + novoMotorista.getNome()).showAndWait();
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
        BooleanBinding camposInvalidos = Bindings.createBooleanBinding(
                () -> txNome.getText() == null
                        || txNome.getText().trim().isEmpty()
                        || txCPF.getText() == null
                        || txCPF.getText().trim().isEmpty(),
                txNome.textProperty(),
                txCPF.textProperty());

        btnSalvar.disableProperty().bind(camposInvalidos);
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        txCPF.setText("");

    }
}
