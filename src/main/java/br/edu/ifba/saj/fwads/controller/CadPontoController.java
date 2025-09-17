package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.service.PontoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadPontoController {
    
    @FXML
    private TextField txEndereço;

    private MasterController masterController;
    private ListPontoController listPontoController;
    private PontoService servicePonto = new PontoService();

    public void setListPontoController(ListPontoController listPontoController) {
        this.listPontoController = listPontoController;
    }

    @FXML
    private void salvarPonto(ActionEvent event) {
        String endereco = txEndereço.getText().trim();
        Ponto novoPonto = new Ponto(endereco);

        try {
            servicePonto.salvarComValidacao(novoPonto);
            new Alert(AlertType.INFORMATION,
                "Ponto: " + novoPonto.getEndereco() + " cadastrado com sucesso").showAndWait();
            limparTela();
            if (listPontoController != null) {
                listPontoController.loadPontoList();
            }
        } catch (CampoObrigatorioException | EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void limparTela() {
        txEndereço.setText("");
        masterController.showFXMLFile("ListPonto.fxml");
    }
}
