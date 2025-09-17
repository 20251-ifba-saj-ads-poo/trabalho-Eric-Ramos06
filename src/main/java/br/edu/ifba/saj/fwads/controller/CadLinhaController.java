package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Linha;
import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.model.Onibus;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.service.LinhaService;
import br.edu.ifba.saj.fwads.service.MotoristaService;
import br.edu.ifba.saj.fwads.service.OnibusService;
import br.edu.ifba.saj.fwads.service.ItinerarioService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadLinhaController {

    @FXML
    private TextField txNome;

    @FXML
    private ChoiceBox<Onibus> slOnibus;

    @FXML
    private ChoiceBox<Motorista> slMotorista;

    @FXML
    private ChoiceBox<Itinerario> slItinerario;

    private ListLinhaController listLinhaController;
    private LinhaService serviceLinha = new LinhaService();
    private MotoristaService serviceMotorista = new MotoristaService();
    private OnibusService serviceOnibus = new OnibusService();
    private ItinerarioService serviceItinerario = new ItinerarioService();

    public void setListLinhaController(ListLinhaController listLinhaController) {
        this.listLinhaController = listLinhaController;
    }

    @FXML
    private void initialize() {
        slMotorista.setConverter(new StringConverter<>() {
            @Override
            public String toString(Motorista obj) {
                return obj != null ? obj.getNome() + " (" + obj.getCpf() + ")" : "";
            }

            @Override
            public Motorista fromString(String string) {
                return null;
            }
        });

        slOnibus.setConverter(new StringConverter<>() {
            @Override
            public String toString(Onibus obj) {
                return obj != null ? obj.getPlaca() : "";
            }

            @Override
            public Onibus fromString(String string) {
                return null;
            }
        });

        slItinerario.setConverter(new StringConverter<>() {
            @Override
            public String toString(Itinerario obj) {
                return obj != null ? obj.getNome() + " | " + obj.getHoraPartida() + " | " + obj.getRota().getNome() : "";
            }

            @Override
            public Itinerario fromString(String string) {
                return null;
            }
        });

        slMotorista.setItems(FXCollections.observableList(serviceMotorista.findAll()));
        slOnibus.setItems(FXCollections.observableList(serviceOnibus.findAll()));
        slItinerario.setItems(FXCollections.observableList(serviceItinerario.findAll()));
    }

    @FXML
    void salvarLinha(ActionEvent event) {
        Linha novaLinha = new Linha(
            txNome.getText().trim(),
            slOnibus.getSelectionModel().getSelectedItem(),
            slMotorista.getSelectionModel().getSelectedItem(),
            slItinerario.getSelectionModel().getSelectedItem()
        );

        try {
            serviceLinha.salvarComValidacao(novaLinha);
            new Alert(AlertType.INFORMATION,
                "Linha: " + novaLinha.getNome() + " cadastrada com sucesso!").showAndWait();
            limparTela();
            if (listLinhaController != null) {
                listLinhaController.loadLinhaList();
            }
        } catch (CampoObrigatorioException | EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        slOnibus.getSelectionModel().clearSelection();
        slMotorista.getSelectionModel().clearSelection();
        slItinerario.getSelectionModel().clearSelection();
    }
}
