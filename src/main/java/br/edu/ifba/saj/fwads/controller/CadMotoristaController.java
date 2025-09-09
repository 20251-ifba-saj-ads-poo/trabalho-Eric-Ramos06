
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.service.Service;
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

    private Service<Motorista> serviceMotorista = new Service<>(Motorista.class);

    @FXML
    void salvarMotorista(ActionEvent event) {
        Motorista novoMotorista = new Motorista(txNome.getText(),
        txCPF.getText());
        serviceMotorista.create(novoMotorista);
        new Alert(AlertType.INFORMATION,
                "Cadastrando Motorista: " + novoMotorista.getNome() + " - " + novoMotorista.getCpf()).showAndWait();
        limparTela();
    }

    @FXML
    private void initialize() {
    BooleanBinding camposInvalidos = Bindings.createBooleanBinding(
            () -> txNome.getText() == null
                   || txNome.getText().trim().isEmpty()
                   || txCPF.getText() == null
                   || txCPF.getText().trim().isEmpty(),
            txNome.textProperty(),
            txCPF.textProperty()
        );

        btnSalvar.disableProperty().bind(camposInvalidos);
    }


    @FXML
    private void limparTela() {
        txNome.setText("");
        txCPF.setText("");
        
    }
}

