package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.ItinerarioService;
import br.edu.ifba.saj.fwads.service.RotaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadItinerarioController {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txHoraPartida;

    @FXML
    private ChoiceBox<Rota> slRota;

    @FXML
    private Button btnSalvar;

    private final ItinerarioService serviceItinerario = new ItinerarioService();

    private final RotaService serviceRota = new RotaService();

    private final StringConverter<Rota> rotaConverter = new StringConverter<>() {
        @Override
        public String toString(Rota r) {
            if (r != null) {
                String origem = r.getPontoInicial() != null ? r.getPontoInicial().getEndereco() : "N/D";
                String destino = r.getPontoFinal() != null ? r.getPontoFinal().getEndereco() : "N/D";
                return String.format("%s | Origem: %s | Destino: %s", r.getNome(), origem, destino);
            }
            return "";
        }

        @Override
        public Rota fromString(String text) {
            return serviceRota.buscarTodosRotas().stream()
                    .filter(rt -> text.contains(rt.getNome()))
                    .findFirst()
                    .orElse(null);
        }
    };

    @FXML
    private void initialize() {
        ObservableList<Rota> obsRotas = FXCollections.observableArrayList(serviceRota.buscarTodosRotas());
        slRota.setItems(obsRotas);
        slRota.setConverter(rotaConverter);

        btnSalvar.disableProperty().bind(
                txNome.textProperty().isEmpty()
                        .or(txHoraPartida.textProperty().isEmpty())
                        .or(slRota.valueProperty().isNull()));
    }

    @FXML
    void salvarItinerario(ActionEvent event) {
        try {
            serviceItinerario.validarDuplicidade(txNome.getText());

            Itinerario novoItinerario = new Itinerario();
            novoItinerario.setNome(txNome.getText());
            novoItinerario.setHoraPartida(txHoraPartida.getText());
            novoItinerario.setRota(slRota.getValue());

            serviceItinerario.create(novoItinerario);

            new Alert(AlertType.INFORMATION,
                    "Itinerário cadastrado com sucesso: " + novoItinerario.getNome()).showAndWait();

            limparTela();

        } catch (EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(AlertType.ERROR,
                    "Erro inesperado, favor entrar em contato com a equipe de desenvolvimento").showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        App.setRoot("controller/Menu.fxml");
    }

    @FXML
    private void limparTela() {
        txNome.clear();
        txHoraPartida.clear();
        slRota.setValue(null);
    }
}
