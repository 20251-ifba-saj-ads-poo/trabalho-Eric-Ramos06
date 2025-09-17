package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.FormatoInvalidoException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.ItinerarioService;
import br.edu.ifba.saj.fwads.service.RotaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadItinerarioController {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txHoraPartida;

    @FXML
    private ChoiceBox<Rota> slRota;

    private ListItinerarioController listItinerarioController;
    private ItinerarioService serviceItinerario = new ItinerarioService();
    private RotaService serviceRota = new RotaService();

    public void setListItinerarioController(ListItinerarioController listItinerarioController) {
        this.listItinerarioController = listItinerarioController;
    }

    public void setServiceItinerario(ItinerarioService serviceItinerario) {
        this.serviceItinerario = serviceItinerario;
    }

    @FXML
    private void initialize() {
        slRota.setConverter(new StringConverter<>() {
            @Override
            public String toString(Rota rota) {
                return rota != null ? rota.getNome() : "";
            }

            @Override
            public Rota fromString(String string) {
                return null;
            }
        });

        slRota.setItems(FXCollections.observableList(serviceRota.findAll()));
    }

    @FXML
    private void salvarItinerario(ActionEvent event) {
        String nome = txNome.getText().trim();
        String hora = txHoraPartida.getText().trim();
        Rota rotaSelecionada = slRota.getSelectionModel().getSelectedItem();

        Itinerario novoItinerario = new Itinerario(nome, hora, rotaSelecionada);

        try {
            serviceItinerario.salvarComValidacao(novoItinerario);
            new Alert(AlertType.INFORMATION,
                "Itinerário: " + novoItinerario.getNome() + " cadastrado com sucesso!").showAndWait();
            limparTela();
            if (listItinerarioController != null) {
                listItinerarioController.loadItinerarioList();
            }
        } catch (CampoObrigatorioException | FormatoInvalidoException | EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        txHoraPartida.setText("");
        slRota.getSelectionModel().clearSelection();
    }
}
