package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.FormatoInvalidoException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Onibus;
import br.edu.ifba.saj.fwads.service.OnibusService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadOnibusController {

    @FXML
    private TextField txPlaca;

    private MasterController masterController;
    private ListOnibusController listOnibusController;
    private OnibusService serviceOnibus;

    public void setMasterController(MasterController masterController) {
        this.masterController = masterController;
    }

    public void setListOnibusController(ListOnibusController listOnibusController) {
        this.listOnibusController = listOnibusController;
    }

    public void setServiceOnibus(OnibusService serviceOnibus) {
        this.serviceOnibus = serviceOnibus;
    }

    @FXML
    private void salvarOnibus() {
        String placa = txPlaca.getText().trim();
        Onibus novoOnibus = new Onibus(placa);

        try {
            serviceOnibus.salvarComValidacao(novoOnibus);
            new Alert(AlertType.INFORMATION,
                "Ônibus: " + novoOnibus.getPlaca() + " cadastrado com sucesso").showAndWait();
            limparTela();
            if (listOnibusController != null) {
                listOnibusController.loadOnibusList();
            }
        } catch (CampoObrigatorioException | FormatoInvalidoException | EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void limparTela() {
        txPlaca.setText("");
        masterController.showFXMLFile("ListOnibus.fxml");
    }
}
